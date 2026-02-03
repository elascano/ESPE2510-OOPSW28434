const fs = require("fs");
const path = require("path");
const Persistence = require("./Persistence");
const Instrument = require("../model/Instrument");

class JsonPersistence extends Persistence {
  static FILE_NAME = "Instruments.json";

  async create(instrument) {
    const instruments = await this.read();
    if (instruments.some(t => t.getId() === instrument.getId())) return false;
    instruments.push(instrument);
    return this._saveAll(instruments);
  }

  async read() {
    const filePath = path.resolve(JsonPersistence.FILE_NAME);
    if (!fs.existsSync(filePath)) return [];
    const raw = fs.readFileSync(filePath, "utf-8");
    if (!raw.trim()) return [];
    try {
      const arr = JSON.parse(raw);
      return (arr || []).map(o => new Instrument(o.id, o.name, o.price, o.materials || [], o.priceWithIva));
    } catch {
      return [];
    }
  }

  async update(id, instrument) {
    const instruments = await this.read();
    const idx = instruments.findIndex(t => t.getId() === id);
    if (idx === -1) return false;
    instruments[idx] = instrument;
    return this._saveAll(instruments);
  }

  async delete(id) {
    const instruments = await this.read();
    const filtered = instruments.filter(t => t.getId() !== id);
    if (filtered.length === instruments.length) return false;
    return this._saveAll(filtered);
  }

  async find(id) {
    const instruments = await this.read();
    return instruments.find(t => t.getId() === id) || null;
  }

  _saveAll(instruments) {
    try {
      const filePath = path.resolve(JsonPersistence.FILE_NAME);
      const plain = instruments.map(t => ({
        id: t.getId(),
        name: t.getName(),
        price: t.getPrice(),
        materials: t.getMaterials(),
        priceWithIva: t.getPriceWithIva()
      }));
      fs.writeFileSync(filePath, JSON.stringify(plain, null, 2), "utf-8");
      return true;
    } catch {
      return false;
    }
  }
}

module.exports = JsonPersistence;
