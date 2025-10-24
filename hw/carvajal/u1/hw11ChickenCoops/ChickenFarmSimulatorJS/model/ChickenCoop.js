import { Chicken } from './Chicken.js';

export class ChickenCoop {
  constructor(id, chickens = []) {
    this.id = id;
    this.chickens = chickens;
  }

  addChicken(chicken) {
    this.chickens.push(chicken);
    console.log(` Chicken '${chicken.getName()}' added to Coop ${this.id}`);
  }

  showChickens() {
    console.log(`\n Chicken Coop ${this.id} has ${this.chickens.length} chickens:`);
    for (const chicken of this.chickens) {
      console.log(`   - ${chicken.getName()} (${chicken.getColor()}, ${chicken.getAge()} years old)`);
    }
  }

  toString() {
    return `ChickenCoop { id: ${this.id}, totalChickens: ${this.chickens.length} }`;
  }
}