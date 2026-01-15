const { MongoClient } = require('mongodb');

class Database {
    constructor() {
  
        this.uri = "mongodb+srv://Adrian:1234@cluster0.o8impqy.mongodb.net/";
        this.client = new MongoClient(this.uri);
        this.dbName = "PatientRegist"; 
    }

    async getCollection(collectionName) {
        try {
            await this.client.connect();
            return this.client.db(this.dbName).collection(collectionName);
        } catch (error) {
            console.error("Error conectando a Mongo:", error);
        }
    }
}
module.exports = new Database();