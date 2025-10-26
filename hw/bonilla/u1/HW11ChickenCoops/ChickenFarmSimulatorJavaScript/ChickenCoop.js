class ChickenCoop {
  static idCounter = 1;

  constructor(name) {
    this.id = ChickenCoop.idCounter++;
    this.name = name;
    this.chickens = [];
  }

  addChicken(chicken) {
    this.chickens.push(chicken);
  }

  getChickens() {
    return this.chickens;
  }

  toString() {
    return (
      `\nChickenCoop {\n` +
      `  id -> ${this.id},\n` +
      `  name -> ${this.name},\n` +
      `  number of chickens -> ${this.chickens.length}\n` +
      `}`
    );
  }
}

module.exports = { ChickenCoop };