/**
 * FoodPile.js
 * Clase que representa una pila de alimento en una celda del entorno.
 */
const Food = require('./Food');

class FoodPile {
    /**
     * @param {number} amount - La cantidad inicial de alimento en la pila.
     */
    constructor(amount) {
        this.amount = amount;
    }

    /**
     * Intenta tomar una cantidad de alimento de la pila.
     * @param {number} requestedAmount - La cantidad solicitada.
     * @returns {Food} El objeto Food con la cantidad realmente tomada.
     */
    takeFood(requestedAmount) {
        const actualAmount = Math.min(requestedAmount, this.amount);
        this.amount -= actualAmount;
        
        const food = new Food(actualAmount);
        
        if (this.amount <= 0) {
            this.amount = 0;
        }
        
        return food;
    }

    getAmount() {
        return this.amount;
    }
}

module.exports = FoodPile;