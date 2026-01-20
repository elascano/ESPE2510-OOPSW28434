const MongoCrud = require("./MongoCrud");

class ToyController {
  constructor() {
    this.mongoCrud = new MongoCrud();
    this.IVA = 0.15;
  }

  calculateIva(price) {
    return +(price * this.IVA).toFixed(2);
  }

  async createToy(toy) {
    toy.priceIva = this.calculateIva(toy.price);
    return await this.mongoCrud.create(toy);
  }

  async getAllToys() {
    const toys = await this.mongoCrud.readAll();
    return toys.map(t => ({
      ...t.toObject(),
      priceIva: this.calculateIva(t.price)
    }));
  }

  async getToyById(id) {
    const toy = await this.mongoCrud.readById(id);
    if (!toy) return null;

    return {
      ...toy.toObject(),
      priceIva: this.calculateIva(toy.price)
    };
  }

  async updateToy(toy) {
    toy.priceIva = this.calculateIva(toy.price);
    return await this.mongoCrud.update(toy);
  }

  async deleteToy(id) {
    return await this.mongoCrud.delete(id);
  }
}

module.exports = ToyController;
