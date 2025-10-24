// Owner/Author: Josue Rojas
import { Food } from './Food_model.js';
import { choice, DIRS } from './utils.js';

const MAX_CARRY_CAPACITY = 5;
const INITIAL_WEIGHT = 3;

export class Ant {
  constructor(initialPosition) {
    this.position = { ...initialPosition };
    this.weightMg = INITIAL_WEIGHT;
    this.foodCarried = null;
    this.direction = choice(DIRS);
  }

  eat(food) {
    if (food instanceof Food) {
      const amount = food.getAmount();
      if (this.weightMg < 5) {
        this.weightMg = Math.min(5, this.weightMg + amount);
        return true;
      }
    }
    return false;
  }

  pickupFood(foodPile) {
    if (this.foodCarried || !foodPile) return false;
    const food = foodPile.requestFood(MAX_CARRY_CAPACITY);
    if (food && food.getAmount() > 0) { this.foodCarried = food; return true; }
    return false;
  }

  dropFood() { const f = this.foodCarried; this.foodCarried = null; return f; }
  decreaseWeight() { this.weightMg = Math.max(0, this.weightMg - 1); }
  move(newPosition) { this.position = { ...newPosition }; }
}
