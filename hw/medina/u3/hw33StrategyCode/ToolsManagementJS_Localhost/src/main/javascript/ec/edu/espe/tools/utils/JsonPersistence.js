const fs = require("fs");
const path = require("path");
const Persistence = require("./Persistence");
const Tool = require("../model/Tool");

class JsonPersistence extends Persistence {
  static FILE_NAME = "tools.json";

  async create(tool) {
    const tools = await this.read();
    if (tools.some(t => t.getId() === tool.getId())) return false;
    tools.push(tool);
    return this._saveAll(tools);
  }

  async read() {
    const filePath = path.resolve(JsonPersistence.FILE_NAME);
    if (!fs.existsSync(filePath)) return [];
    const raw = fs.readFileSync(filePath, "utf-8");
    if (!raw.trim()) return [];
    try {
      const arr = JSON.parse(raw);
      return (arr || []).map(o => new Tool(o.id, o.name, o.price, o.materials || [], o.priceWithIva));
    } catch {
      return [];
    }
  }

  async update(id, tool) {
    const tools = await this.read();
    const idx = tools.findIndex(t => t.getId() === id);
    if (idx === -1) return false;
    tools[idx] = tool;
    return this._saveAll(tools);
  }

  async delete(id) {
    const tools = await this.read();
    const filtered = tools.filter(t => t.getId() !== id);
    if (filtered.length === tools.length) return false;
    return this._saveAll(filtered);
  }

  async find(id) {
    const tools = await this.read();
    return tools.find(t => t.getId() === id) || null;
  }

  _saveAll(tools) {
    try {
      const filePath = path.resolve(JsonPersistence.FILE_NAME);
      const plain = tools.map(t => ({
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
