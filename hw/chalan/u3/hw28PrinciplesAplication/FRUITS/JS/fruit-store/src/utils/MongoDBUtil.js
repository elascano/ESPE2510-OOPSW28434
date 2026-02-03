import { MongoClient } from 'mongodb'

class MongoDBUtil {
  static client
  static db

  static CONNECTION_STRING = 'mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contac'
  static DATABASE_NAME = 'fruit_store'

  static async getDatabase() {
    if (!this.db) {
      this.client = new MongoClient(this.CONNECTION_STRING)
      await this.client.connect()
      this.db = this.client.db(this.DATABASE_NAME)
      await this.init()
    }
    return this.db
  }

  static async init() {
    const collections = await this.db.listCollections().toArray()
    if (!collections.find(c => c.name === 'fruits')) {
      await this.db.collection('fruits').insertMany([
        { name: 'Apple', price: 1.5, stock: 20 },
        { name: 'Banana', price: 0.8, stock: 30 },
        { name: 'Orange', price: 1.2, stock: 25 },
        { name: 'Strawberry', price: 2.0, stock: 15 }
      ])
    }
  }
}

export default MongoDBUtil
