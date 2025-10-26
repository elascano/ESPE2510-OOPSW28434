import { Egg } from "./Egg.js";
import { Poop } from "./Poop.js";

class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id; this.name = name; this.color = color; this.age = age; this.isMolting = isMolting;
  }
  layEgg(nextEggId) { if (this.isMolting) return null; return new Egg(nextEggId, this.id, new Date()); }
  poop(nextPoopId) { return new Poop(nextPoopId, this.id); return new Poop(nextPoopId, this.id, grams); }
  toString() { return `#${this.id} ${this.name} (${this.color}) age:${this.age} molting:${this.isMolting}`; }
}
export { Chicken };
