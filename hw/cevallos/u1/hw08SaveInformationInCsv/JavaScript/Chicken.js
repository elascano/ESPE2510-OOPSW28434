const fs = require('fs');
const Egg = require('./Egg');
const Poop = require('./Poop');

class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  poop(amount) {
    const poop = new Poop(amount);
    console.log(`Chicken ${this.name} is pooping a ${poop}`);
    return poop;
  }

  layAnEgg(size) {
    const egg = new Egg(size);
    console.log(`The chicken ${this.name} is laying a ${egg.size} size egg`);
    return egg;
  }

  cluck() {
    console.log(`The chicken ${this.name} is clucking: cluck, cluck, cluck`);
  }

  eat() {
    console.log(`The chicken ${this.name} is eating grains`);
  }

  wander() {
    console.log(`Chicken ${this.name} is wandering`);
  }

  drink() {
    console.log(`Chicken ${this.name} is drinking water`);
  }

  doStuff() {
    const actions = [
      () => this.cluck(),
      () => this.eat(),
      () => this.wander(),
      () => this.drink(),
      () => this.poop(Math.floor(Math.random() * 5) + 1),
      () => this.layAnEgg(['S', 'M', 'L'][Math.floor(Math.random() * 3)])
    ];
    const repetitions = Math.floor(Math.random() * 4) + 3;
    for (let i = 0; i < repetitions; i++) {
      const action = actions[Math.floor(Math.random() * actions.length)];
      action();
    }
  }

  saveToCSV(filename = 'chickens.csv') {
    const data = `${this.id},${this.name},${this.color},${this.age},${this.isMolting}\n`;
    fs.appendFileSync(filename, data);
    console.log(`Chicken ${this.name} saved to ${filename}`);
  }

  toString() {
    return `\nChicken{\nid -> ${this.id},\nname -> ${this.name},\ncolor -> ${this.color},\nage -> ${this.age},\nisMolting -> ${this.isMolting}\n}`;
  }
}

module.exports = Chicken;
