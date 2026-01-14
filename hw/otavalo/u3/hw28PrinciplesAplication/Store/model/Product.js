const db = require('./Database');
const IProductStorage = require('./IProductStorage');

class ProductModel extends IProductStorage {
    async insert(productData) {
        try {
            // Asegúrate de que la colección sea 'products'
            const collection = await db.getCollection("products");
            return await collection.insertOne(productData);
        } catch (error) {
            console.error("Insert error:", error);
            throw error;
        }
    }

    async getAll() {
        try {
            const collection = await db.getCollection("products");
            return await collection.find({}).toArray();
        } catch (error) {
            return [];
        }
    }
}

module.exports = ProductModel;