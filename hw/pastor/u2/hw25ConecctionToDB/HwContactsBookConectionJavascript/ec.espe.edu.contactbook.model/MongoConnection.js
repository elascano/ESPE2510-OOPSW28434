const { MongoClient } = require("mongodb");

class MongoConnection {
    constructor() {
        this.uri = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/";
        this.client = new MongoClient(this.uri);
        this.dbName = "ContactBook";
    }

    async getDatabase() {
        try {
            await this.client.connect();
            console.log("Conectado a MongoDB Atlas");
            return this.client.db(this.dbName);
        } catch (error) {
            console.error("Error conectando a Mongo:", error);
            throw error;
        }
    }

    async close() {
        await this.client.close();
    }
}

module.exports = new MongoConnection();