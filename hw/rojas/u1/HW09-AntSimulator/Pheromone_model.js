// Owner/Author: Josue Rojas
export class Pheromone {
  constructor(initialLevel = 100) { this.level = initialLevel; }
  getLevel() { return this.level; }
  decreaseLevel() { this.level -= 1; return this.level <= 0; }
  addLevel(amount) { this.level = Math.min(100, this.level + amount); }
}
