const { MongoClient } = require("mongodb");

class MongoConnection {
  static instance;

  constructor() {
    this.mongoClient = null;
    this.database = null;
    this.uri = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/";
    this.dbName = "InstrumentsDB";
  }

  static getInstance() {
    if (!MongoConnection.instance) {
      MongoConnection.instance = new MongoConnection();
    }
    return MongoConnection.instance;
  }

  async connect() {
    if (this.database) return this.database;

    try {
      this.mongoClient = new MongoClient(this.uri);
      await this.mongoClient.connect();
      this.database = this.mongoClient.db(this.dbName);
      console.log("Successfully connected to MongoDB");
      return this.database;
    } catch (error) {
      console.error("Error to conect to MongoDB", error);
      return null;
    }
  }

  async getCollection() {
    const db = await this.connect();
    return db.collection("instruments");
  }

  async close() {
    if (!this.mongoClient) return;
    await this.mongoClient.close();
    this.mongoClient = null;
    this.database = null;
  }
}

module.exports = MongoConnection;