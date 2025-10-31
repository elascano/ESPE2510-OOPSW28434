import { Egg } from "./Egg.js";
import { Poop } from "./Poop.js";

class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  layEgg(nextEggId) {
    if (this.isMolting) return null;
    return new Egg(nextEggId, this.id, new Date());
  }

  poop(nextPoopId) {
    return new Poop(nextPoopId, this.id);
  }

  toString() {
    return `id: ${this.id}, name: ${this.name}, color: ${this.color}, age: ${this.age}, isMolting: ${this.isMolting}`;
  }
}

export { Chicken };
