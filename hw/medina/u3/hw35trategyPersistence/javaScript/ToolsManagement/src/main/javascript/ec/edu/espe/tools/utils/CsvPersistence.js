const fs = require("fs");
const path = require("path");
const Persistence = require("./Persistence");
const Tool = require("../model/Tool");

class CsvPersistence extends Persistence {
  static FILE_NAME = "tools.csv";

  async create(tool) {
    const allTools = await this.read();
    if (allTools.some(t => t.getId() === tool.getId())) return false;
    allTools.push(tool);
    return this._saveAll(allTools);
  }

  async read() {
    const filePath = path.resolve(CsvPersistence.FILE_NAME);
    if (!fs.existsSync(filePath)) return [];
    const content = fs.readFileSync(filePath, "utf-8");
    const lines = content.split(/\r?\n/).filter(l => l.trim().length > 0);

    const tools = [];
    for (const line of lines) {
      const parts = line.split(",");
      if (parts.length < 5) continue;

      const id = parts[0];
      const name = parts[1];
      const price = Number(parts[2]);
      const priceWithIva = Number(parts[3]);
      const materialsString = parts.slice(4).join(",");
      const materials = materialsString.split(";").map(s => s.trim()).filter(Boolean);

      tools.push(new Tool(id, name, price, materials, priceWithIva));
    }
    return tools;
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
      const filePath = path.resolve(CsvPersistence.FILE_NAME);
      const lines = tools.map(t => {
        const materialsString = (t.getMaterials() || []).join(";");
        return `${t.getId()},${t.getName()},${t.getPrice()},${t.getPriceWithIva()},${materialsString}`;
      });
      fs.writeFileSync(filePath, lines.join("\n") + (lines.length ? "\n" : ""), "utf-8");
      return true;
    } catch {
      return false;
    }
  }
}

module.exports = CsvPersistence;
