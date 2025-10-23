export class Pheromone {
  constructor(level = 100) {
    this.level = Math.min(level, 100);
  }

  decay() {
    if (this.level > 0) this.level--;
  }

  isActive() {
    return this.level > 0;
  }

  add(level) {
    this.level = Math.min(100, this.level + level);
  }
}