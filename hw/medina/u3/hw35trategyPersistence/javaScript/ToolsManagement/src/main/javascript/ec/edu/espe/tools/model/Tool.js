class Tool {
  constructor(id, name, price, materials, priceWithIva) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.materials = materials;
    this.priceWithIva = priceWithIva;
  }

  getId() { return this.id; }
  setId(id) { this.id = id; }

  getName() { return this.name; }
  setName(name) { this.name = name; }

  getPrice() { return this.price; }
  setPrice(price) { this.price = price; }

  getMaterials() { return this.materials; }
  setMaterials(materials) { this.materials = materials; }

  getPriceWithIva() { return this.priceWithIva; }
  setPriceWithIva(priceWithIva) { this.priceWithIva = priceWithIva; }

  toString() {
    return `${this.name} (Mat: ${JSON.stringify(this.materials)})`;
  }
}

module.exports = Tool;
