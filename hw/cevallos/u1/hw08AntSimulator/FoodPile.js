const Food = require('./Food');


class FoodPile {
    constructor(amount) {
        this.amount = amount;
    }

    takeFood(requestedAmount) {
        const actualAmount = Math.min(requestedAmount, this.amount);
        this.amount -= actualAmount;
        
        const food = new Food(actualAmount);
        
        if (this.amount <= 0) {
            this.amount = 0;
        }
        
        return food;
    }

    getAmount() {
        return this.amount;
    }
}

module.exports = FoodPile;