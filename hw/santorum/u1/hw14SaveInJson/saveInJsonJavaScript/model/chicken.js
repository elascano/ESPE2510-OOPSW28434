export class Chicken {
  constructor(id, name, color, age, molting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.molting = molting;
  }

  getId() { return this.id; }
  getName() { return this.name; }
  getColor() { return this.color; }
  getAge() { return this.age; }
  isMolting() { return this.molting; }

  setName(name) { this.name = name; }
  setColor(color) { this.color = color; }
  setAge(age) { this.age = age; }
  setMolting(molting) { this.molting = molting; }

  doStuff() {
    console.log(`${this.name} is clucking... `);
    console.log(`${this.name} is eating grain.`);
    console.log(`${this.name} is sleeping...`);
  }
}
