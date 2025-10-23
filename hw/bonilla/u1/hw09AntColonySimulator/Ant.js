import { Pheromone } from './Pheromone.js';
import { Food } from './Food.js';

export class Ant {
  constructor(startCell, colony) {
    this.position = startCell;
    this.direction = Ant.randomDirection();
    this.weight = 1;
    this.carriedFood = 0;
    this.colony = colony;
    this.inNest = !!startCell.nest;
    this.ticksAlive = 0;
    this.lastWeightLossTick = 0;
    startCell.ants.add(this);
  }

  static randomDirection() {
    const dirs = [
      [0, 1], [1, 0], [0, -1], [-1, 0],
      [1, 1], [1, -1], [-1, 1], [-1, -1],
    ];
    return dirs[Math.floor(Math.random() * dirs.length)];
  }

  eatFromNest() {
    const nest = this.position.nest;
    if (!nest) return;
    if (nest.foodStock >= 1 && this.weight < 5) {
      const food = nest.removeFood(1);
      this.weight = Math.min(5, this.weight + food.amount);
    }
  }

  pickFoodFromCell() {
    const cell = this.position;
    if (!cell.foodPile || this.carriedFood >= 5) return;
    const canTake = 5 - this.carriedFood;
    const food = cell.foodPile.takeFood(canTake);
    this.carriedFood += food.amount;
    if (cell.foodPile.isEmpty()) cell.foodPile = null;
  }

  dropFoodToNest() {
    const nest = this.position.nest;
    if (!nest || this.carriedFood <= 0) return;
    nest.addFood(new Food(this.carriedFood));
    this.carriedFood = 0;
  }

  createPheromoneOnCell() {
    const p = new Pheromone(100);
    this.position.addPheromone(p);
  }

  moveTo(cell) {
    if (!cell) return;
    this.position.ants.delete(this);
    this.position = cell;
    cell.ants.add(this);
    this.inNest = !!cell.nest;
  }

  tick(sim) {
    this.ticksAlive++;

    if ((this.ticksAlive - this.lastWeightLossTick) >= 50) {
      this.weight = Math.max(0, this.weight - 1);
      this.lastWeightLossTick = this.ticksAlive;
    }

    if (this.inNest) {
      this.eatFromNest();
      if (this.weight >= 3 && Math.random() < 0.6) {
        const neighbors = sim.getNeighbors(this.position.x, this.position.y);
        neighbors.sort((a, b) => b.getPheromoneLevel() - a.getPheromoneLevel());
        const chosen = neighbors[0];
        if (chosen) this.moveTo(chosen);
      }
      if (this.carriedFood > 0) this.dropFoodToNest();
      return;
    }

    const cell = this.position;

    if (cell.foodPile && this.carriedFood < 5) {
      this.pickFoodFromCell();
      return;
    }

    if (this.carriedFood === 0) {
      const neighbors = sim.getNeighbors(cell.x, cell.y);
      const pherCells = neighbors.filter(n => n.pheromone);
      const chosen = pherCells.length
        ? pherCells[Math.floor(Math.random() * pherCells.length)]
        : neighbors[Math.floor(Math.random() * neighbors.length)];
      if (chosen) this.moveTo(chosen);
      return;
    }

    if (this.carriedFood > 0) {
      this.createPheromoneOnCell();
      const neighbors = sim.getNeighbors(cell.x, cell.y);
      const chosen = neighbors[Math.floor(Math.random() * neighbors.length)];
      if (chosen) this.moveTo(chosen);
    }
  }
}