import { Food } from './Food.js';
import { Ant } from './Ant.js';

export class Nest {
  constructor(cell) {
    this.position = { x: cell.x, y: cell.y };
    this.cell = cell;
    this.foodStock = 0;
    cell.nest = this;
  }

  addFood(food) {
    this.foodStock += food.amount;
  }

  removeFood(amount) {
    const used = Math.min(amount, this.foodStock);
    this.foodStock -= used;
    return new Food(used);
  }

  canCreateAnt() {
    return this.foodStock >= 5;
  }

  createAnt(colony) {
    if (!this.canCreateAnt()) return null;
    this.foodStock -= 5;
    const ant = new Ant(this.cell, colony);
    colony.addAnt(ant);
    return ant;
  }
}