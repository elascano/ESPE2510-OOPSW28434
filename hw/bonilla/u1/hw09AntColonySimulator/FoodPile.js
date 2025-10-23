import { Food } from './Food.js';

export class FoodPile {
  constructor(amount) {
    this.amount = amount;
  }

  takeFood(requestAmount) {
    const taken = Math.min(requestAmount, this.amount);
    this.amount -= taken;
    return new Food(taken);
  }

  isEmpty() {
    return this.amount <= 0;
  }
}