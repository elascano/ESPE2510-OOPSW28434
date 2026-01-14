const { MongoClient } = require('mongodb');

class ProductRepository {
    constructor(uri, dbName, collectionName) {
        this.client = new MongoClient(uri);
        this.dbName = dbName;
        this.collectionName = collectionName;
    }

    async #getCollection() {
        await this.client.connect();
        return this.client.db(this.dbName).collection(this.collectionName);
    }

    async create(product) {
        const col = await this.#getCollection();
        await col.insertOne(product);
    }

    async readAll() {
        const col = await this.#getCollection();
        return await col.find({}).toArray();
    }

    async findById(id) {
        const col = await this.#getCollection();
        return await col.findOne({ id: id });
    }

    async update(product) {
        const col = await this.#getCollection();
        await col.updateOne({ id: product.id }, { $set: product });
    }

    async delete(id) {
        const col = await this.#getCollection();
        await col.deleteOne({ id: id });
    }
}
module.exports = ProductRepository;