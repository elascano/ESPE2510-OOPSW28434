

const { MongoClient, ObjectId } = require('mongodb');

const MONGO_URI = 'mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/ProfessorJs?retryWrites=true&w=majority'; 
const DB_NAME = 'ProfessorDBJs';
const IVA_RATE = 0.15; 

class StoreManager {
    constructor() {
        this.client = null;
        this.db = null;
    }
    
    async connect() {
        if (this.client) {
             return;
        }
        try {
            this.client = new MongoClient(MONGO_URI, { 
                serverSelectionTimeoutMS: 3000
            });
            await this.client.connect();
            this.db = this.client.db(DB_NAME);
            
            this.professorCollection = this.db.collection('Products');
            this.customersCollection = this.db.collection('Customers');
            this.salesCollection = this.db.collection('Sales');
            
            console.log(" Successful connection to MongoDB for ProfessorJs.");
        } catch (error) {
            this.client = null;
            console.error(` ERROR: Could not connect to MongoDB: ${error.message}`);
            throw new Error("Database connection failed.");
        }
    }

    _checkConnection() {
        if (this.client === null) {
            throw new Error("No database connection. Call connect() first.");
        }
    }
    async saveProfessor(data) {
        this._checkConnection();
        const { _id, ...updateData } = data;
        updateData.price = parseFloat(updateData.price);
        updateData.stock = parseInt(updateData.stock);

        try {
            if (_id) {
                const objectId = new ObjectId(_id);
                const result = await this.professorCollection.updateOne(
                    { "_id": objectId },
                    { "$set": updateData }
                );
                if (result.matchedCount === 0) throw new Error("Professor not found for update.");
                return objectId;
            } else {
                const result = await this.professorCollection.insertOne({ 
                    ...updateData,
                    created_at: new Date()
                });
                return result.insertedId;
            }
        } catch (e) {
            throw new Error(`Error saving/updating professor: ${e.message}`);
        }
    }

   


    async processSale(customerId, cartItems, paymentMethod) {
        this._checkConnection();
        const session = this.client.startSession();
        
        try {
            session.startTransaction();

            if (!cartItems || cartItems.length === 0) {
                throw new Error("The sales cart is empty.");
            }

            let subtotalAmount = 0; // Usado para calcular el total sin IVA
            const saleItems = [];
            
            // 1. Check stock and prepare the sale
            for (const item of cartItems) {
                const productId = new ObjectId(item.productId);
                const quantity = parseInt(item.quantity);

                // Find the product and check stock (transactional lock)
                const product = await this.professorCollection.findOne(
                    { "_id": productId }, 
                    { session, projection: { name: 1, stock: 1, price: 1 } }
                );

                if (!product) {
                    throw new Error(`Product with ID ${item.productId} not found.`);
                }
                if (product.stock < quantity) {
                    throw new Error(`Insufficient stock for '${product.name}'. Current stock: ${product.stock}.`);
                }

                const subtotal = product.price * quantity;
                subtotalAmount += subtotal;

                // 2. Decrement product stock
                await this.professorCollection.updateOne(
                    { "_id": productId },
                    { "$inc": { "stock": -quantity } },
                    { session }
                );
                
                saleItems.push({
                    product_id: productId,
                    product_name: product.name,
                    quantity: quantity,
                    unit_price: product.price,
                    subtotal: subtotal,
                });
            }

            // ⚡ IVA CALCULATION ⚡
            const ivaAmount = subtotalAmount * IVA_RATE;
            const totalAmount = subtotalAmount + ivaAmount;

            // 3. Create the Sale record
            const saleRecord = {
                customer_id: customerId ? parseInt(customerId) : null,
                sale_date: new Date(),
                subtotal_amount: subtotalAmount,
                iva_rate: IVA_RATE,
                iva_amount: ivaAmount,
                total_amount: totalAmount, // TOTAL FINAL (Subtotal + IVA)
                payment_method: paymentMethod,
                items: saleItems,
            };
            const result = await this.salesCollection.insertOne(saleRecord, { session });
            
            await session.commitTransaction();
            
            return { saleId: result.insertedId, total: totalAmount };

        } catch (e) {
            await session.abortTransaction();
            throw new Error(`Sale processing failed: ${e.message}`);
        } finally {
            session.endSession();
        }
    }

    async findSales(searchTerm = "") {
        this._checkConnection();

        let matchQuery = {};
        if (searchTerm) {
            const customerId = parseInt(searchTerm);
            const regexPattern = new RegExp(searchTerm.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&'), "i");
            
            if (!isNaN(customerId) && searchTerm.length < 5) {
                matchQuery = { "customer_id": customerId };
            } else {
                matchQuery = { "items.product_name": regexPattern };
            }
        }
        
        const pipeline = [
            { "$match": matchQuery }, 
            { "$sort": { "sale_date": -1 } },
            { "$limit": 50 },

            { "$lookup": {
                "from": "Customers",
                "localField": "customer_id",
                "foreignField": "customer_id",
                "as": "customer_details"
            }},
            { "$addFields": {
                "customer_name": { "$ifNull": [ { "$arrayElemAt": [ "$customer_details.name", 0 ] }, "Guest/N/A" ] }
            }},
            
            { "$project": {
                "_id": 1,
                "sale_date": 1,
                "customer_id": 1,
                "customer_name": 1,
                "total_amount": 1,
                "payment_method": 1,
                "total_items": { "$sum": "$items.quantity" }
            }}
        ];
        
        return await this.salesCollection.aggregate(pipeline).toArray();
    }
}

module.exports = StoreManager;