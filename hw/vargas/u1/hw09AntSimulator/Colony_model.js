import { Nest } from './Nest_model.js';
import { Ant } from './Ant_model.js';

export class Colony {
    constructor(colonyId, nestPosition) {
        this.id = colonyId;
        this.nest = new Nest(nestPosition);
        this.ants = [];
    }

    addAnt(ant) {
        this.ants.push(ant);
    }

    getNestPosition() {
        return this.nest.getPosition();
    }

depositFood(food) {
    this.nest.addFood(food.getAmount());
    if (this.nest.getAmount() >= 10) {
        this.nest.consumeFood(10);
        const newAnt = new Ant(this.getNestPosition());
        this.ants.push(newAnt);
        return newAnt;
    }
    return null;
}

    removeAnt(ant) {
        this.ants = this.ants.filter(a => a !== ant);
    }
}
