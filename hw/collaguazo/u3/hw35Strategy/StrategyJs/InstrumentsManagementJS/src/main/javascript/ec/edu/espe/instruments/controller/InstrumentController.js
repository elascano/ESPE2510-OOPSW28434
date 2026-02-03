const Instrument = require("../model/Instrument");

class InstrumentController {
  constructor(strategy) {
    this.strategy = strategy;
  }

  setStrategy(strategy) {
    this.strategy = strategy;
  }

  static IVA_RATE = 0.15;

  calculateIva(price) {
    const result = price * (1 + InstrumentController.IVA_RATE);
    return Math.round(result * 100) / 100;
  }

  async createSculpture(id, name, price, materials) {
    const finalPrice = this.calculateIva(price);
    const newInstrument = new Instrument(id, name, price, materials, finalPrice);
    return await this.strategy.create(newInstrument);
  }

  async getAllInstruments() {
    return await this.strategy.read();
  }

  async findSculptureById(id) {
    return await this.strategy.find(id);
  }

  async updateSculpture(id, name, price, materials) {
    const newPriceWithIva = this.calculateIva(price);
    const updatedInstrument = new Instrument(id, name, price, materials, newPriceWithIva);
    return await this.strategy.update(id, updatedInstrument);
  }

  async deleteSculpture(id) {
    return await this.strategy.delete(id);
  }
}

module.exports = InstrumentController;
