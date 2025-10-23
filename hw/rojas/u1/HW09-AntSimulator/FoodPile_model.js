// Owner/Author: Josue Rojas
import { Food } from './Food_model.js';

export class FoodPile {
  constructor(amount, position = null) {
    this.amount = Math.max(0, Math.floor(amount));
    this.position = position;
  }
  getPosition() { return this.position; }
  getAmount() { return this.amount; }

  requestFood(requestedAmount) {
    const need = Math.max(0, Math.floor(requestedAmount));
    const take = Math.min(need, this.amount);
    this.amount -= take;
    return new Food(take);
  }

  addFood(foodObject) {
    if (foodObject instanceof Food) this.amount += foodObject.getAmount();
  }
}
