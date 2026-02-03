const { MongoClient } = require('mongodb');

class MongoStrategy {
    constructor() {
        const uri = "mongodb+srv://Paulo:paulo2004@cluster0.9uxqgih.mongodb.net/";
        this.client = new MongoClient(uri);
        this.db = this.client.db("Store");
        this.collection = this.db.collection("Store");
    }

    async create(s) { await this.collection.insertOne(s); }
    async find(id) { return await this.collection.findOne({ id: Number(id) }); }
    async delete(id) { await this.collection.deleteOne({ id: Number(id) }); }
    async loadAll() { return await this.collection.find().toArray(); }
}

module.exports = MongoStrategy;