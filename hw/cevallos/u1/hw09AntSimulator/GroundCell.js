const FoodPile = require('./FoodPile');
const Pheromone = require('./Pheromone');



class GroundCell {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.foodPile = null;
        this.pheromone = null;
        this.ants = [];
        this.antEaters = [];
    }

    dropPheromone(level) {
        if (this.pheromone) {
            this.pheromone.level = Math.min(100, this.pheromone.level + level);
        } else {
            this.pheromone = new Pheromone(level);
        }
    }

    addFood(amount) {
        if (this.foodPile) {
            this.foodPile.amount += amount;
        } else {
            this.foodPile = new FoodPile(amount);
        }
    }

    removeFood(amount) {
        if (this.foodPile) {
            return this.foodPile.takeFood(amount);
        }
        return new Food(0);
    }

    addAnt(ant) {
        this.ants.push(ant);
    }

    removeAnt(ant) {
        const index = this.ants.indexOf(ant);
        if (index > -1) {
            this.ants.splice(index, 1);
        }
    }

    addAntEater(antEater) {
        this.antEaters.push(antEater);
    }

    removeAntEater(antEater) {
        const index = this.antEaters.indexOf(antEater);
        if (index > -1) {
            this.antEaters.splice(index, 1);
        }
    }

    getPheromoneLevel() {
        return this.pheromone ? this.pheromone.level : 0;
    }

    getFoodAmount() {
        return this.foodPile ? this.foodPile.amount : 0;
    }
}

module.exports = GroundCell;