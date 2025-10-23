import { Pheromone } from './Pheromone.js';
import { FoodPile } from './FoodPile.js';
import { Food } from './Food.js';
import { Cell } from './Cell.js';

export class Ant{
    static MAX_FOOD_SIZE = 5;
    static MIN_WEIGHT_TO_LEAVE = 3;

    constructor(colony, position, initialWeight){
        this._colony = colony;
        this._position = position;
        this._weight = initialWeight;
        this.foodCarried = 0;
        this.direction = null;
        this.state = 'IN_NEST';
        this.ticksSinceLastWeightDecrease = 0;
    }

    getPosition(){
        return this._position;
    }

    setPosition(newPosition){ 
        if (this._position) {
            this._position.removeAgent(this);
        }
        newPosition.addAgent(this);
        this._position = newPosition; 
    }

    getWeight(){
        return this._weight;
    }
    
    setDirection(newCell) {
        this.direction = newCell.getDirectionFrom(this._position); 
    }

    dropPheromone() {
        this._position.dropPheromone(new Pheromone());
    }

    decreaseWeight(){
        this.ticksSinceLastWeightDecrease++;
        if(this.ticksSinceLastWeightDecrease >= 50){
            this._weight = Math.max(0, this._weight - 1);
            this.ticksSinceLastWeightDecrease = 0;
        }
    }
    
    pickupFood(foodPile) {
        const space = Ant.MAX_FOOD_SIZE - this.foodCarried;
        const food = foodPile.requestFood(space);
        this.foodCarried += food.getAmount();
        
        if (foodPile.getAmount() === 0) {
            this._position.foodPile = null;
        }
    }
    
    eat(food) {
        this._weight = Math.min(10, this._weight + food.getAmount());
    }
    
    behaveInNest(nest, area) {
        if (this._weight < Ant.MIN_WEIGHT_TO_LEAVE) {
            const food = nest.requestFoodForAnt();
            if (food) {
                this.eat(food);
            }
        }
        
        if (this._weight >= Ant.MIN_WEIGHT_TO_LEAVE) {
            this.state = "SEARCHING";

            const exitCell = area.getBestExitCell(this._position);
            if(exitCell){
                this.setDirection(exitCell);
                this.setPosition(area, exitCell);
            }
        }
    }

    behaveOutsideColony(area){
        const currentCell = this._position;

        if (currentCell.foodPile && this.foodCarried < Ant.MAX_FOOD_SIZE) {
            this.pickupFood(currentCell.foodPile); 
            if (this.foodCarried > 0) {
                this.state = "RETURNING"; 
            }
        }
        
        if (this.state === "RETURNING" && currentCell.nest) {
            currentCell.nest.depositFood(new Food(this.foodCarried));
            this.foodCarried = 0;
            this.state = "IN_NEST";
            return;
        }

        if (this.state === "RETURNING") {
            this.dropPheromone();
        }

        const neighbors = area.getNeighboringCells(currentCell);
        let nextCell;
        
        if (this.state === "RETURNING") {
            nextCell = area.findBestCellByPheromone(neighbors, 'increasing', this.direction);
        } else {
            nextCell = area.findBestCellByPheromone(neighbors, 'decreasing', this.direction);
            
            if (!nextCell) {
                nextCell = area.getRandomCell(neighbors, true, this.direction); 
            }
        }

        if (nextCell) {
            this.setDirection(nextCell);
            this.setPosition(area, nextCell); 

            if (nextCell.nest) { 
                this.state = "IN_NEST";
            }
        }
    }
}