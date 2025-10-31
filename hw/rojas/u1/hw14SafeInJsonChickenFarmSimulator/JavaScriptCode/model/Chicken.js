class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.is_molting = isMolting; 
  }

  cluck() { return `${this.name} says: cluck!`; }
  wander() { return `${this.name} is wandering around the coop.`; }
  eat() { return `${this.name} is eating grains.`; }
  drink() { return `${this.name} is drinking water.`; }

  doStuff(forTime) {
    const actions = [this.cluck(), this.wander(), this.eat(), this.drink()];
    return `${this.name} did ${actions.length} actions in ${forTime} minutes.`;
  }
}

module.exports = Chicken;
