const Food = require('./Food');

class FoodPile {
    constructor(quantity) {
        this.quantity = quantity;
    }

    takeFood(requested) {
        const givenAmount = Math.min(requested, this.quantity);
        this.quantity -= givenAmount;

        const foodItem = new Food(givenAmount);

        if (this.quantity < 0) {
            this.quantity = 0;
        }

        return foodItem;
    }

    getAmount() {
        return this.quantity;
    }
}

module.exports = FoodPile;
