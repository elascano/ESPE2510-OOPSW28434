import { Food } from './Food_model.js';

const MAX_CARRY_CAPACITY = 5;
const INITIAL_WEIGHT = 3;
const MIN_WEIGHT_TO_LEAVE_NEST = 3;

export class Ant {
    constructor(initialPosition) {
        this.position = initialPosition;
        this.weightMg = INITIAL_WEIGHT;
        this.foodCarried = null;
        this.direction = null;
        this.ticksInNest = 0; 
        this.ticksOutsideNest = 0; 
    }

    eat(food) {
        if (food instanceof Food) {
            let amount = food.getAmount();      
            if (this.weightMg < 5) {
                this.weightMg = Math.min(5, this.weightMg + amount);
                return true;
            }
        }
        return false;
    }
    
    pickupFood(foodPile) {
        if (this.foodCarried) return false; 
        const capacity = MAX_CARRY_CAPACITY;
        const food = foodPile.requestFood(capacity);

        if (food && food.getAmount() > 0) {
            this.foodCarried = food;
            return true;
        }
        return false;
    }

    dropFood() {
        if (this.foodCarried) {
            const food = this.foodCarried;
            this.foodCarried = null;
            return food;
        }
        return null;
    }

    decreaseWeight() {
        this.weightMg = Math.max(0, this.weightMg - 1);
        return this.weightMg > 0; 
    }

    canLeaveNest() {
        return this.weightMg >= MIN_WEIGHT_TO_LEAVE_NEST;
    }

    move(newPosition) {
        this.position = newPosition;
    }
}