const { MongoClient } = require("mongodb");

class MongoConnection {
  static instance;

  constructor() {
    this.mongoClient = null;
    this.database = null;
  }

  static getInstance() {
    if (!MongoConnection.instance) MongoConnection.instance = new MongoConnection();
    return MongoConnection.instance;
  }

  async connect() {
    if (this.database) return this.database;
    const uri = process.env.MONGO_URI || "mongodb://127mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/.0.0.1:27017";
    const dbName = process.env.MONGO_DB || "ResourcesDB";
    this.mongoClient = new MongoClient(uri);
    await this.mongoClient.connect();
    this.database = this.mongoClient.db(dbName);
    return this.database;
  }

  async close() {
    if (!this.mongoClient) return;
    await this.mongoClient.close();
    this.mongoClient = null;
    this.database = null;
  }
}

module.exports = MongoConnection;
