const fs = require("fs");
const path = require("path");
const Persistence = require("./Persistence");
const Instrument = require("../model/Instrument");

class CsvPersistence extends Persistence {
  static FILE_NAME = "InstrumentsJS.csv";

  async create(instrument) {
    const allInstruments = await this.read();
    if (allInstruments.some(i => i.getId() === instrument.getId())) return false;
    allInstruments.push(instrument);
    return this._saveAll(allInstruments);
  }

  async read() {
    const filePath = path.resolve(CsvPersistence.FILE_NAME);
    if (!fs.existsSync(filePath)) return [];
    const content = fs.readFileSync(filePath, "utf-8");
    const lines = content.split(/\r?\n/).filter(l => l.trim().length > 0);

    const instruments = [];
    for (const line of lines) {
      const parts = line.split(",");
      if (parts.length < 5) continue;

      const id = parts[0];
      const name = parts[1];
      const price = Number(parts[2]);
      const priceWithIva = Number(parts[3]);
      const materialsString = parts.slice(4).join(",");
      const materials = parts[4].split("-").map(s => s.strip()).filter(Boolean);

      instruments.push(new Instrument(id, name, price, materials, priceWithIva));
    }
    return instruments;
  }

  async update(id, instrument) {
    const instruments = await this.read();
    const idx = instruments.findIndex(i => i.getId() === id);
    if (idx === -1) return false;
    instruments[idx] = instrument;
    return this._saveAll(instruments);
  }

  async delete(id) {
    const instruments = await this.read();
    const filtered = instruments.filter(i => i.getId() !== id);
    if (filtered.length === instruments.length) return false;
    return this._saveAll(filtered);
  }

  async find(id) {
    const instruments = await this.read();
    return instruments.find(i => i.getId() === id) || null;
  }

  _saveAll(instruments) {
    try {
      const filePath = path.resolve(CsvPersistence.FILE_NAME);
      const lines = instruments.map(i => {
        const materialsString = (i.getMaterials() || []).join(";");
        return `${i.getId()};${i.getName()};${i.getPrice()};${i.getPriceWithIva()};${materialsString}`;
      });
      fs.writeFileSync(filePath, lines.join("\n") + (lines.length ? "\n" : ""), "utf-8");
      return true;
    } catch {
      return false;
    }
  }
}

module.exports = CsvPersistence;
