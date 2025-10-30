// model/Chicken.js
export class Chicken {
  constructor(id, name, age, color, molting) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.color = color;
    this.molting = molting;
  }

  // 🔹 Método que se usa al guardar en JSON
  toJSON() {
    return {
      id: this.id,
      name: this.name,
      age: this.age,
      color: this.color,
      molting: this.molting
    };
  }

  // 🔹 Método que se usa al cargar desde JSON
  static fromJSON(obj) {
    return new Chicken(obj.id, obj.name, obj.age, obj.color, obj.molting);
  }

  // 🔹 Método opcional para mostrar info en consola
  toString() {
    return `🐔 ${this.name} | Edad: ${this.age} | Color: ${this.color} | Muda: ${this.molting}`;
  }
}