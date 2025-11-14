const FoodPile = require('./FoodPile');
const Pheromone = require('./Pheromone');
const Food = require('./Food');

class GroundCell {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.foodPile = null;
        this.pheromone = null;
        this.ants = [];
        this.antEaters = [];
    }

    dropPheromone(amount) {
        if (this.pheromone) {
            this.pheromone.level = Math.min(100, this.pheromone.level + amount);
        } else {
            this.pheromone = new Pheromone(amount);
        }
    }

    addFood(quantity) {
        if (this.foodPile) {
            this.foodPile.quantity += quantity;
        } else {
            this.foodPile = new FoodPile(quantity);
        }
    }

    removeFood(quantity) {
        if (this.foodPile) {
            return this.foodPile.takeFood(quantity);
        }
        return new Food(0);
    }

    addAnt(ant) {
        this.ants.push(ant);
    }

    removeAnt(ant) {
        const idx = this.ants.indexOf(ant);
        if (idx !== -1) {
            this.ants.splice(idx, 1);
        }
    }

    addAntEater(antEater) {
        this.antEaters.push(antEater);
    }

    removeAntEater(antEater) {
        const idx = this.antEaters.indexOf(antEater);
        if (idx !== -1) {
            this.antEaters.splice(idx, 1);
        }
    }

    getPheromoneLevel() {
        return this.pheromone ? this.pheromone.level : 0;
    }

    getFoodAmount() {
        return this.foodPile ? this.foodPile.quantity : 0;
    }
}

module.exports = GroundCell;
