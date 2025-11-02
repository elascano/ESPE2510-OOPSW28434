/**
 * Nest.js
 * Clase que representa el nido de la colonia (almacenamiento y generación).
 */
const Food = require('./Food');

class Nest {
    /**
     * @param {number} x - Coordenada X del nido.
     * @param {number} y - Coordenada Y del nido.
     * @param {Colony} colony - Referencia a la colonia.
     */
    constructor(x, y, colony) {
        this.x = x;
        this.y = y;
        this.colony = colony;
        this.storedFood = 0; // Alimento almacenado
        this.ANTS_PER_FOOD_UNIT = 5; // Cuesta 5mg generar una hormiga
    }

    /**
     * Agrega comida al nido y genera nuevas hormigas si hay suficiente alimento.
     * @param {number} amount - Cantidad de comida a agregar.
     */
    addFood(amount) {
        this.storedFood += amount;
        
        while (this.storedFood >= this.ANTS_PER_FOOD_UNIT) {
            this.storedFood -= this.ANTS_PER_FOOD_UNIT;
            // Llama a la colonia para crear una nueva hormiga
            this.colony.createAnt(); 
        }
    }

    /**
     * Permite a las hormigas tomar comida del nido (para reponer energía).
     * @param {number} amount - Cantidad de comida a tomar.
     * @returns {Food} El objeto Food tomado.
     */
    takeFood(amount) {
        const actualAmount = Math.min(amount, this.storedFood);
        this.storedFood -= actualAmount;
        return new Food(actualAmount);
    }

    getFoodAmount() {
        return this.storedFood;
    }
}

module.exports = Nest;