// Autor: Josue Carvajal
// Versión: 0.1

export class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  // Métodos getters y setters
  getId() {
    return this.id;
  }

  setId(id) {
    this.id = id;
  }

  getName() {
    return this.name;
  }

  setName(name) {
    this.name = name;
  }

  getColor() {
    return this.color;
  }

  setColor(color) {
    this.color = color;
  }

  getAge() {
    return this.age;
  }

  setAge(age) {
    this.age = age;
  }

  getIsMolting() {
    return this.isMolting;
  }

  setIsMolting(isMolting) {
    this.isMolting = isMolting;
  }

  toString() {
    return `Chicken{
            id -->        ${this.id},
            name -->      ${this.name},
            color -->     ${this.color},
            age -->       ${this.age},
            isMolting --> ${this.isMolting}
        }`;
  }
}
