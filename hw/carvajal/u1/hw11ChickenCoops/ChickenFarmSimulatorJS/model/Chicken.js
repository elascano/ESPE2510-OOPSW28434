export class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  getName() {
    return this.name;
  }

  getColor() {
    return this.color;
  }

  getAge() {
    return this.age;
  }

  toString() {
    return `Chicken { id: ${this.id}, name: '${this.name}', color: '${this.color}', age: ${this.age}, isMolting: ${this.isMolting} }`;
  }
}
