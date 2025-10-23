import { FoodPile } from './FoodPile_model.js';
import { Ant } from './Ant_model.js';
export class Nest extends FoodPile {
    constructor(position) {
        super(0); 
        this.position = position;
    }

    getPosition() {
        return this.position;
    }

    addFood(foodObject) {
        super.addFood(foodObject);
        return this.tryCreateAnt();
    }

    tryCreateAnt() {
        if (this.amount >= 5) {
            this.amount -= 5;
            const newAnt = new Ant(this.position);
            return { ant: newAnt, message: `¡NUEVA HORMIGA! creada. Peso inicial: ${newAnt.weightMg}mg.` };
        }
        return null;
    }
    
}