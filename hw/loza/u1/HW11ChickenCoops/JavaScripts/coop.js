// coop.js
import { Chicken } from "./chicken.js";

export class Coop {
  constructor(coopName) {
    // Stores the coop name and creates an empty list of chickens
    this.coopName = coopName;
    this.chickens = [];
  }

  addChicken(chicken) {
    // Adds a chicken to the coop
    this.chickens.push(chicken);
  }

  getCoopName() {
    // Returns the coop name
    return this.coopName;
  }

  getChickens() {
    // Returns all chickens in this coop
    return this.chickens;
  }

  toString() {
    // How to display the coop with its chickens
    const chickenNames = this.chickens.map(c => c.toString()).join(", ");
    return `Coop: ${this.coopName} -> Chickens: [${chickenNames}]`;
  }
}
