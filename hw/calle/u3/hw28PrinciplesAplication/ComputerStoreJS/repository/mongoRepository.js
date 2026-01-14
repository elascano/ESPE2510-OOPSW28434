const { MongoClient } = require('mongodb');
const ProductRepository = require('./baseRepository');

class MongoProductRepository extends ProductRepository {
    constructor() {
        super(); 
        const uri = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/";
        this.client = new MongoClient(uri);
        this.dbName = 'ComputerStoreDB';
    }

    async save(product) {
        try {
            await this.client.connect();
            const database = this.client.db(this.dbName);
            const collection = database.collection('products');

            const doc = {
                name: product.getName(),
                basePrice: product.getBasePrice(),
                totalPrice: product.getTotalPrice(),
                date: new Date()
            };

            return await collection.insertOne(doc);
        } catch (error) {
            console.error("Error saving to MongoDB:", error);
            throw error;
        } finally {
            await this.client.close();
        }
    }
}

module.exports = MongoProductRepository;