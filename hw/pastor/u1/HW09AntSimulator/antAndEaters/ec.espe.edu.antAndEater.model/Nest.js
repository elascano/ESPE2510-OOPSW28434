import { Ant } from './Ant.js'; 
import { Food } from './Food.js';

export class Nest {
    static FOOD_TO_CREATE_ANT = 5; 

    constructor(position, colony) {
        this.position = position;
        this.colony = colony;
        this.foodStock = 0;
    }

    depositFood(food) {
        this.foodStock += food.getAmount();
    }

    tryCreateAnt() {
        while (this.foodStock >= Nest.FOOD_TO_CREATE_ANT) {
            this.foodStock -= Nest.FOOD_TO_CREATE_ANT;
            const newAnt = new Ant(this.colony, this.position, 5);
            this.colony.addAnt(newAnt);
            this.position.addAgent(newAnt); 
        }
    }

    requestFoodForAnt() {
        if (this.foodStock >= 1) {
            this.foodStock -= 1;
            return new Food(1);
        }
        return null;
    }
}