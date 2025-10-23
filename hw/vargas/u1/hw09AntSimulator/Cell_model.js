import { Pheromone } from './Pheromone_model.js';
import { FoodPile } from './FoodPile_model.js';

export class Cell {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.pheromone = null; 
        this.foodPile = null; 
        this.ants = []; 
        this.antEaters = []; 
        this.isNestLocation = false; 
    }

    dropPheromone(level) {
        if (this.pheromone) {
            
            this.pheromone.addLevel(level); 
        } else {
            
            this.pheromone = new Pheromone(level); 
        }
    }

    placeFoodPile(amount) {
        this.foodPile = new FoodPile(amount);
    }

    addAnt(ant) {
        this.ants.push(ant);
    }

    removeAnt(ant) {
        this.ants = this.ants.filter(a => a !== ant);
    }

    addAntEater(antEater) {
        this.antEaters.push(antEater);
    }

    removeAntEater(antEater) {
        this.antEaters = this.antEaters.filter(ae => ae !== antEater);
    }
    
    tick() {
        
        if (this.pheromone) {
            const shouldRemove = this.pheromone.decreaseLevel();
            if (shouldRemove) {
                this.pheromone = null; 
            }
        }
        if (this.foodPile && this.foodPile.getAmount() <= 0) {
            this.foodPile = null;
        }
    }
}