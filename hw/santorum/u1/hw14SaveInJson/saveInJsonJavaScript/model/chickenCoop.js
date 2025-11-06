export class ChickenCoop {
  constructor(id, farmerName) {
    this.id = id;
    this.farmerName = farmerName;
    this.chickens = [];
  }

  addChicken(chicken) {
    this.chickens.push(chicken);
  }

  removeChicken(chickenId) {
    this.chickens = this.chickens.filter(c => c.getId() !== chickenId);
  }

  getChickens() { return this.chickens; }
}
