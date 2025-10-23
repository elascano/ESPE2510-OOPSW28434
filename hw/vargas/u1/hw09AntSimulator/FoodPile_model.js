import { Food } from './Food_model.js';
export class FoodPile {
    constructor(amount, position) {
        this.amount = amount;
        this.position = position;
    }

    getPosition() {
        return this.position;
    }
    getAmount() {
        return this.amount;
    }

    requestFood(requestedAmount) {
        let amountToGive;        
        if (this.amount >= requestedAmount) {
            amountToGive = requestedAmount;
            this.amount -= requestedAmount;
        } else {
            amountToGive = this.amount;
            this.amount = 0;
        }
        return new Food(amountToGive);
    }

    addFood(foodObject) {
        if (foodObject instanceof Food) {
            this.amount += foodObject.getAmount();
        }
    }
}