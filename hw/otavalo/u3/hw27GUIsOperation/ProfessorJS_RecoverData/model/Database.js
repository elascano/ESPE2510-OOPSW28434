const { MongoClient } = require('mongodb');

class Database {
    constructor() {
        this.uri = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/";
        this.client = new MongoClient(this.uri);
        this.dbName = "RecoverData"; 
    }

    async getCollection(collectionName) {
        await this.client.connect();
        return this.client.db(this.dbName).collection(collectionName);
    }
}
module.exports = new Database();