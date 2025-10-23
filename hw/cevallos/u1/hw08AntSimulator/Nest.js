class Nest {
    constructor(x, y, colony) {
        this.x = x;
        this.y = y;
        this.colony = colony;
        this.foodAmount = 0;
    }

    addFood(amount) {
        this.foodAmount += amount;
        
        
        while (this.foodAmount >= 5) {
            this.foodAmount -= 5;
            this.colony.createAnt();
        }
    }

    takeFood(amount) {
        const actualAmount = Math.min(amount, this.foodAmount);
        this.foodAmount -= actualAmount;
        return new Food(actualAmount);
    }

    getFoodAmount() {
        return this.foodAmount;
    }
}

module.exports = Nest;