const { MongoClient } = require('mongodb');

class Database {
    constructor() {
        this.uri = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/?appName=Cluster0";
        this.client = new MongoClient(this.uri);
        this.db = null;
    }

    async connect() {
        if (!this.db) {
            await this.client.connect();
            this.db = this.client.db("HWs"); 
            console.log("Successfully connected to MongoDB");
        }
        return this.db;
    }
}

module.exports = new Database();