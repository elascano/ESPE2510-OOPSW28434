const Persistence = require("./Persistence");
const Tool = require("../model/Tool");
const MongoConnection = require("./MongoConnection");

class MongoPersistence extends Persistence {
  constructor() {
    super();
    this.connection = MongoConnection.getInstance();
  }

  async #collection() {
    const db = await this.connection.connect();
    const name = process.env.MONGO_COLLECTION || "tools";
    return db.collection(name);
  }

  async create(tool) {
    const col = await this.#collection();
    const existing = await col.findOne({ id: tool.getId() });
    if (existing) return false;
    await col.insertOne({
      id: tool.getId(),
      name: tool.getName(),
      price: tool.getPrice(),
      materials: tool.getMaterials(),
      priceWithIva: tool.getPriceWithIva()
    });
    return true;
  }

  async read() {
    const col = await this.#collection();
    const docs = await col.find({}).toArray();
    return docs.map(d => new Tool(d.id, d.name, d.price, d.materials || [], d.priceWithIva));
  }

  async update(id, tool) {
    const col = await this.#collection();
    const res = await col.updateOne(
      { id },
      { $set: {
        id: tool.getId(),
        name: tool.getName(),
        price: tool.getPrice(),
        materials: tool.getMaterials(),
        priceWithIva: tool.getPriceWithIva()
      } }
    );
    return res.matchedCount > 0;
  }

  async delete(id) {
    const col = await this.#collection();
    const res = await col.deleteOne({ id });
    return res.deletedCount > 0;
  }

  async find(id) {
    const col = await this.#collection();
    const d = await col.findOne({ id });
    return d ? new Tool(d.id, d.name, d.price, d.materials || [], d.priceWithIva) : null;
  }
}

module.exports = MongoPersistence;
