class Chicken {
  static idCounter = 1;

  constructor(name, color, age, isMolting) {
    this.id = Chicken.idCounter++;
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  toString() {
    const moltingStatus = this.isMolting ? "True" : "False";
    return `Chicken{ id -> ${this.id}, name -> ${this.name}, color -> ${this.color}, age -> ${this.age}, isMolting -> ${moltingStatus} }`;
  }
}

module.exports = { Chicken };