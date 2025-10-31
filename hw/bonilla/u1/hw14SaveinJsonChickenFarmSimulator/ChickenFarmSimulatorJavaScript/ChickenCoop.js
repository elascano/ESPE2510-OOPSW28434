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

  getNextChickenId() {
    return this.chickens.length + 1;
  }

  toString() {
    return `ChickenCoop { id=${this.id}, name=${this.name}, numberOfChickens=${this.chickens.length} }`;
  }
}

module.exports = { ChickenCoop };