export class ChickenCoop {
  constructor(id) {
    this.id = id;
    this.chickens = [];
  }

  addChicken(chicken) {
    if (this.chickens.length < 5) {
      this.chickens.push(chicken);
      return true;
    } else {
      console.log(`Coop ${this.id} is full.`);
      return false;
    }
  }

  removeChicken(id) {
    this.chickens = this.chickens.filter(chicken => chicken.id !== id);
  }
}