import { PHEROMONE_MAX_STRENGTH, PHEROMONE_DECAY_RATE } from './constants.js';

export class Food {
    constructor(amount = 1) {
        this.amount = amount;
    }
    isEmpty() {
        return this.amount <= 0;
    }
}

export class FoodPile {
    constructor(amount) {
        this.amount = amount;
        this.position = null;
    }
    requestFood(x) {
        const deliveredAmount = Math.min(x, this.amount);
        this.amount -= deliveredAmount;
        return { food: new Food(deliveredAmount), empty: this.amount === 0 };
    }
    getAmount() {
        return this.amount;
    }
}

export class Pheromone {
    constructor(level = PHEROMONE_MAX_STRENGTH) {
        this.level = level;
    }
    decay() {
        this.level = Math.max(0, this.level - PHEROMONE_DECAY_RATE);
        return this.level === 0;
    }
    getLevel() {
        return this.level;
    }
    strengthen(level) {
        this.level = Math.min(PHEROMONE_MAX_STRENGTH, this.level + level);
    }
}

export class Cell {
    constructor(x, y) {
        this.position = [x, y];
        this.foodPile = null;
        this.pheromone = null;
        this.ants = [];
        this.antEaters = [];
        this.nest = null;
    }
    dropPheromone(level = PHEROMONE_MAX_STRENGTH) {
        if (!this.pheromone) this.pheromone = new Pheromone(level);
        else this.pheromone.strengthen(level);
    }
    update() {
        if (this.pheromone && this.pheromone.decay()) this.pheromone = null;
    }
    addAnt(ant) {
        this.ants.push(ant);
    }
    removeAnt(ant) {
        const index = this.ants.indexOf(ant);
        if (index !== -1) this.ants.splice(index, 1);
    }
}

export class Nest {
    constructor(area, location) {
        this.area = area;
        this.location = location;
        this.foodStock = 0;
        this.colony = null;
        this.area.getCell(location).nest = this;
    }
    addFood(food) {
        this.foodStock += food.amount;
        console.log(`Comida depositada en el nido en ${this.location}. Total: ${this.foodStock}mg.`);
    }
    retrieveFood(mg = 1) {
        const retrieved = Math.min(mg, this.foodStock);
        this.foodStock -= retrieved;
        return retrieved;
    }
}
