import { Egg } from "./egg.js";
import { Poop } from "./poop.js";

export class Chicken {
  constructor(id, name, featherColor, age, molting) {
    this.id = id;
    this.name = name;
    this.featherColor = featherColor;
    this.age = age;
    this.molting = molting;
  }

  // acciones
  makeSound() {
    console.log(`Chicken ${this.name} goes cluck-cluck!`);
  }

  eatFood() {
    console.log(`Chicken ${this.name} is eating grains.`);
  }

  moveAround() {
    console.log(`Chicken ${this.name} is walking around.`);
  }

  drinkWater() {
    console.log(`Chicken ${this.name} is drinking water.`);
  }

  performActivities() {
    this.makeSound();
    this.eatFood();
    this.releasePoop(2);
    this.moveAround();
    this.drinkWater();
    this.produceEgg("M");
  }

  releasePoop(quantity) {
    const poop = new Poop(quantity);
    console.log(`Chicken ${this.name} just pooped ${poop.toString()}`);
    return poop;
  }

  produceEgg(size) {
    const egg = new Egg(size);
    console.log(`Chicken ${this.name} laid a ${egg.getSize()} sized egg.`);
    return egg;
  }

  toJSON() {
    return {
      id: this.id,
      name: this.name,
      featherColor: this.featherColor,
      age: this.age,
      molting: this.molting
    };
  }
}

