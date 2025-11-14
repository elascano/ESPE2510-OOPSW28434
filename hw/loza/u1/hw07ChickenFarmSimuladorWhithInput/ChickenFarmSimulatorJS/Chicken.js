import { Poop } from "./Poop.js";
import { Egg } from "./Egg.js";

export class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  cluck() {
    console.log(`🐔 Chicken ${this.name} is clucking... cluck cluck cluck!`);
  }

  eat() {
    console.log(`🍽️ Chicken ${this.name} is eating grains.`);
  }

  wander() {
    console.log(`🚶 Chicken ${this.name} is wandering around.`);
  }

  drink() {
    console.log(`💧 Chicken ${this.name} is drinking water.`);
  }

  poop(amount) {
    const poop = new Poop(amount);
    console.log(`💩 Chicken ${this.name} is pooping: ${poop.toString()}`);
    return poop;
  }

  layAnEgg(size) {
    const egg = new Egg(size);
    console.log(`🥚 Chicken ${this.name} laid a ${egg.size}-size egg!`);
    return egg;
  }

  doStuff() {
    this.cluck();
    this.eat();
    this.cluck();

    // Valores randomicos
    const poop1 = Math.floor(Math.random() * 3) + 1;
    const poop2 = Math.floor(Math.random() * 3) + 1;
    const eggSizes = ["S", "M", "L"];

    this.poop(poop1);
    this.poop(poop2);
    this.eat();
    this.wander();
    this.drink();
    this.layAnEgg(eggSizes[Math.floor(Math.random() * eggSizes.length)]);
    this.layAnEgg(eggSizes[Math.floor(Math.random() * eggSizes.length)]);
  }

  toString() {
    return `\nChicken {
  id -> ${this.id},
  name -> ${this.name},
  color -> ${this.color},
  age -> ${this.age},
  isMolting -> ${this.isMolting}
}`;
  }
}
