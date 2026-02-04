import { MongoClient } from 'mongodb'

class MongoDBUtil {
  static client
  static db

  static CONNECTION_STRING = 'mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contac'
  static DATABASE_NAME = 'fruits_modules'

  static async getDatabase() {
    if (!this.db) {
      this.client = new MongoClient(this.CONNECTION_STRING)
      await this.client.connect()
      this.db = this.client.db(this.DATABASE_NAME)
    }
    return this.db
  }
}

export default MongoDBUtil
