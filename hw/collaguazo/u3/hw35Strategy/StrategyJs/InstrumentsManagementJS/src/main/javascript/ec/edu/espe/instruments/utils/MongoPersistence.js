const Persistence = require("./Persistence");
const Instrument = require("../model/Instrument");
const MongoConnection = require("./MongoConnection");

class MongoPersistence extends Persistence {
  constructor() {
    super();
    this.connection = MongoConnection.getInstance();
  }

  async #collection() {
    const db = await this.connection.connect();
    const name = process.env.MONGO_COLLECTION || "instruments";
    return db.collection(name);
  }

  async create(instrument) {
    const col = await this.#collection();
    const existing = await col.findOne({ id: instrument.getId() });
    if (existing) return false;
    await col.insertOne({
      id: instrument.getId(),
      name: instrument.getName(),
      price: instrument.getPrice(),
      materials: instrument.getMaterials(),
      priceWithIva: instrument.getPriceWithIva()
    });
    return true;
  }

  async read() {
    const col = await this.#collection();
    const docs = await col.find({}).toArray();
    return docs.map(d => new Instrument(d.id, d.name, d.price, d.materials || [], d.priceWithIva));
  }

  async update(id, instrument) {
    const col = await this.#collection();
    const res = await col.updateOne(
      { id },
      { $set: {
        id: instrument.getId(),
        name: instrument.getName(),
        price: instrument.getPrice(),
        materials: instrument.getMaterials(),
        priceWithIva: instrument.getPriceWithIva()
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
    return d ? new Instrument(d.id, d.name, d.price, d.materials || [], d.priceWithIva) : null;
  }
}

module.exports = MongoPersistence;
