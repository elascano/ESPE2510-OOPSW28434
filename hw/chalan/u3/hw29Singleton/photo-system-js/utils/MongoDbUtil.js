const { MongoClient } = require("mongodb");

class MongoDbUtil {

    static instance = null; // 

    constructor() {
        if (MongoDbUtil.instance) {
            return MongoDbUtil.instance;
        }
        this.uri = "mongodb://localhost:27017/";
        this.client = new MongoClient(this.uri);
        this.dbName = "contact";
        
        MongoDbUtil.instance = this; //
    }

    static getInstance() { // Get Instance
        if (!MongoDbUtil.instance) {
            MongoDbUtil.instance = new MongoDbUtil();
        }
        return MongoDbUtil.instance;
    }

    async connect() {
        if (!this.db) {
            try {
                await this.client.connect();
                this.db = this.client.db(this.dbName);
                this.collection = this.db.collection("photographers");
            } catch (e) {
                console.error("Error connecting:", e);
            }
        }
    }

    async save(photographer) {
        await this.connect();
        if (this.collection) {
            await this.collection.insertOne(photographer);
        }
    }

    async getAll() {
        await this.connect();
        return this.collection ? await this.collection.find({}).toArray() : [];
    }

    async getOne(name) {
        await this.connect();
     
        return this.collection ? await this.collection.findOne({ name: name }) : null;
    }
}

module.exports = MongoDbUtil;