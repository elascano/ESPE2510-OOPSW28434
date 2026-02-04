const Tool = require("../model/Tool");

class ToolController {
  constructor(strategy) {
    this.strategy = strategy;
  }

  setStrategy(strategy) {
    this.strategy = strategy;
  }

  static IVA_RATE = 0.15;

  calculateIva(price) {
    const result = price * (1 + ToolController.IVA_RATE);
    return Math.round(result * 100) / 100;
  }

  async createSculpture(id, name, price, materials) {
    const finalPrice = this.calculateIva(price);
    const newTool = new Tool(id, name, price, materials, finalPrice);
    return await this.strategy.create(newTool);
  }

  async getAllTools() {
    return await this.strategy.read();
  }

  async findSculptureById(id) {
    return await this.strategy.find(id);
  }

  async updateSculpture(id, name, price, materials) {
    const newPriceWithIva = this.calculateIva(price);
    const updatedTool = new Tool(id, name, price, materials, newPriceWithIva);
    return await this.strategy.update(id, updatedTool);
  }

  async deleteSculpture(id) {
    return await this.strategy.delete(id);
  }
}

module.exports = ToolController;
