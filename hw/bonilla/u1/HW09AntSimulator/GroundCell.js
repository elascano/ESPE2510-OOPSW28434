import { Pheromone } from './Pheromone.js';

export class GroundCell {
  constructor(x, y) {
    this.x = x;
    this.y = y;
    this.pheromone = null;
    this.foodPile = null;
    this.ants = new Set();
    this.antEaters = new Set();
    this.nest = null;
  }

  addPheromone(p) {
    if (!p) return;
    if (!this.pheromone) this.pheromone = new Pheromone(p.level);
    else this.pheromone.add(p.level);
  }

  addFoodPile(pile) {
    this.foodPile = pile;
  }

  getPheromoneLevel() {
    return this.pheromone ? this.pheromone.level : 0;
  }
}