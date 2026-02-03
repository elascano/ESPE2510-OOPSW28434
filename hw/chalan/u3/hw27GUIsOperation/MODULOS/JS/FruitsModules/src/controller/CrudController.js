import MongoDBUtil from '../utils/MongoDBUtil.js'
import Entity from '../model/Entity.js'

class CrudController {
  async init(collectionName) {
    const db = await MongoDBUtil.getDatabase()
    this.collection = db.collection(collectionName)
  }

  async add(data) {
    await this.collection.insertOne(new Entity(data))
  }

  async removeByName(name) {
    await this.collection.deleteOne({ name })
  }

  async getAll() {
    return await this.collection.find().toArray()
  }

  // ✅ NUEVO: comprar según stock
  async buy(name, quantity) {
    const item = await this.collection.findOne({ name })

    if (!item) {
      throw new Error('Item not found')
    }

    if (item.stock < quantity) {
      throw new Error('Not enough stock')
    }

    const total = item.price * quantity

    // Actualiza stock
    await this.collection.updateOne(
      { name },
      { $inc: { stock: -quantity } }
    )

    return total
  }
}

export default CrudController
