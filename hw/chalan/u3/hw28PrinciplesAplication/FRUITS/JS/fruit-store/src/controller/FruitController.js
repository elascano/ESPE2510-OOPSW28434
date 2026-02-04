import MongoDBUtil from '../utils/MongoDBUtil.js'
import Fruit from '../model/Fruit.js'

class FruitController {
  async init() {
    const db = await MongoDBUtil.getDatabase()
    this.collection = db.collection('fruits')
  }

  async addFruit(name, price, stock) {
    await this.collection.insertOne(new Fruit(name, price, stock))
  }

  async deleteFruit(name) {
    await this.collection.deleteOne({ name })
  }

  async buyFruit(name, quantity) {
    const fruit = await this.collection.findOne({ name })
    if (!fruit) return -1
    if (quantity > fruit.stock) return -2
    await this.collection.updateOne(
      { name },
      { $set: { stock: fruit.stock - quantity } }
    )
    return fruit.price * quantity
  }

  async getFruitNames() {
    return (await this.collection.find().toArray()).map(f => f.name)
  }

  async getPriceByName(name) {
    const fruit = await this.collection.findOne({ name })
    return fruit ? fruit.price : 0
  }

  async existsFruit(name) {
    return !!(await this.collection.findOne({ name }))
  }
}

export default FruitController
