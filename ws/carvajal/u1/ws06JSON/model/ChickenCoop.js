import { Chicken } from './Chicken.js';

export class ChickenCoop {
  constructor(id, chickens = []) {
    this.id = id;
    this.chickens = chickens; // Lista de objetos Chicken
  }

  addChicken(chicken) {
    this.chickens.push(chicken);
  }

  listChickens() {
    return this.chickens.map(c => c.toJSON());
  }

  getTotalChickens() {
    return this.chickens.length;
  }
}