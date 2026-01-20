const { MongoClient } = require('mongodb');
const config = require('../config.json');

class Database {
    constructor() {
        this.uri = "mongodb+srv://Psblo:Pablo2006@cluster0.cadn1kx.mongodb.net/";
        this.client = new MongoClient(this.uri);
        this.dbName = config.db_name; 
    }

    async getCollection(collectionName) {
        await this.client.connect();
        return this.client.db(this.dbName).collection(collectionName);
    }
}
module.exports = new Database();