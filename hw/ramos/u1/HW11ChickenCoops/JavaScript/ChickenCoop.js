import Chicken  from "./Chicken.js";

export default class ChickenCoop {
  constructor(id) {
    this.id = id;
    this.chickens = [];
  }

  addChicken(chicken) {
    this.chickens.push(chicken);
  }

  toString() {
    const chickensInfo = this.chickens.map(chicken => "  " + chicken.toString()).join("\n");
    return `ChickenCoop(id=${this.id})\n${chickensInfo}`;
  }
}
