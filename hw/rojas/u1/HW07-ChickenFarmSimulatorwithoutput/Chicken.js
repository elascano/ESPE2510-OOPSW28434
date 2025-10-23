// ============================================
// Chicken Class
// Author: Josue Rojas
// Owner: Josue Rojas
// ============================================

const Poop = require("./Poop");
const Egg = require("./Egg");

class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  cluck() { console.log(`Chicken ${this.name} is clucking.`); }
  eat() { console.log(`Chicken ${this.name} is eating grains.`); }
  drink() { console.log(`Chicken ${this.name} is drinking water.`); }
  wander() { console.log(`Chicken ${this.name} is wandering around.`); }

  poop() {
    const amount = Math.floor(Math.random() * 5) + 1;
    const poop = new Poop(amount);
    console.log(`Chicken ${this.name} is pooping amount --> ${amount} (${poop})`);
    return poop;
  }

  layEgg() {
    const size = ["S", "M", "L"][Math.floor(Math.random() * 3)];
    const egg = new Egg(size);
    console.log(`Chicken ${this.name} laid a ${egg}`);
    return egg;
  }

  async doRandomActions(ask) {
    const actions = [this.cluck, this.eat, this.drink, this.wander, this.poop, this.layEgg];
    const n = Math.floor(Math.random() * 4) + 3;
    console.log(`\n--- Chicken ${this.name} is about to perform ${n} random actions ---`);
    // shuffle
    for (let i = actions.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [actions[i], actions[j]] = [actions[j], actions[i]];
    }
    for (let i = 0; i < n; i++) {
      const see = (await ask("Do you want to know what the chicken is doing? (y/n): ")).toLowerCase();
      if (see === "y" || see === "yes") actions[i].call(this);
      else console.log(`You chose not to see what ${this.name} is doing right now.`);
    }
  }

  toString() {
    return `\nChicken:
id -->\t\t${this.id}
name -->\t${this.name}
color -->\t${this.color}
age -->\t\t${this.age}
isMolting -->\t${this.isMolting}\n`;
  }
}

module.exports = Chicken;
