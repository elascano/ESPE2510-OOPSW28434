import { Food } from './Food.js';

export class FoodPile {
    constructor(amount) {
        this.amount = amount;
    }

    requestFood(amountRequested) {
        const amountToGive = Math.min(amountRequested, this.amount);
        this.amount -= amountToGive;
        
        if (this.amount <= 0) {
            this.amount = 0;
        }
        
        return new Food(amountToGive);
    }

    getAmount() {
        return this.amount;
    }
}