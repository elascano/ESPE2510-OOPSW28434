import Chicken  from "./Chicken.js";

export default class ChickenCoop {
  constructor(id) {
    this.id = id;
    this.chickens = [];
  }

  addChicken(chicken) {
    this.chickens.push(chicken);
  }

    listChickens() {
    if (this.chickens.length === 0) {
      console.log(` ChickenCoop ${this.id} is empty.`);
    } else {
      console.log(` ChickenCoop ${this.id} contains:`);
      this.chickens.forEach(chicken => console.log("  " + chicken.toString()));
    }
  }
  toString() {
    const chickensInfo = this.chickens.map(chicken => "  " + chicken.toString()).join("\n");
    return `ChickenCoop(id=${this.id})\n${chickensInfo}`;
  }
}
