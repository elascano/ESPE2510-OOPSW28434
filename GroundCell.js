/**
 * GroundCell.js
 * Clase que representa una celda del mapa de simulación.
 */
const FoodPile = require('./FoodPile');
const Pheromone = require('./Pheromone');
const Food = require('./Food'); // Necesario para la función removeFood
// Se asume que Ant y AntEater se añadirán/removerán en el simulador

class GroundCell {
    /**
     * @param {number} x - Coordenada X.
     * @param {number} y - Coordenada Y.
     */
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.foodPile = null;
        this.pheromone = null;
        // Almacenar residentes genéricamente
        this.residents = []; 
    }

    /**
     * Añade o refuerza el rastro de feromona.
     * @param {number} level - El nivel de feromona a añadir.
     */
    dropPheromone(level) {
        if (this.pheromone) {
            this.pheromone.level = Math.min(100, this.pheromone.level + level);
        } else {
            this.pheromone = new Pheromone(level);
        }
    }

    /**
     * Agrega comida a la celda.
     * @param {number} amount - La cantidad de comida.
     */
    addFood(amount) {
        if (this.foodPile) {
            this.foodPile.amount += amount;
        } else {
            this.foodPile = new FoodPile(amount);
        }
    }

    /**
     * Remueve comida de la celda.
     * @param {number} amount - La cantidad a remover.
     * @returns {Food} El objeto Food removido.
     */
    removeFood(amount) {
        if (this.foodPile) {
            const foodTaken = this.foodPile.takeFood(amount);
             if (this.foodPile.getAmount() <= 0) {
                this.foodPile = null; // Eliminar pila vacía
            }
            return foodTaken;
        }
        return new Food(0);
    }
    
    // --- Gestión de Residentes (Entidades) ---

    addResident(entity) {
        this.residents.push(entity);
    }

    removeResident(entity) {
        const index = this.residents.indexOf(entity);
        if (index > -1) {
            this.residents.splice(index, 1);
            return true;
        }
        return false;
    }

    getAnts() {
        // Filtrar las hormigas (asumiendo que tienen una propiedad 'isAnt')
        return this.residents.filter(r => r.constructor.name === 'Ant');
    }

    getAntEaters() {
        // Filtrar los osos hormigueros (asumiendo que tienen una propiedad 'isAntEater')
        return this.residents.filter(r => r.constructor.name === 'AntEater');
    }

    // --- Getters ---

    getPheromoneLevel() {
        return this.pheromone ? this.pheromone.level : 0;
    }

    getFoodAmount() {
        return this.foodPile ? this.foodPile.amount : 0;
    }

    /**
     * Actualiza el estado de la celda (ej. evaporación de feromonas).
     */
    updatePheromone() {
        if (this.pheromone) {
            this.pheromone.decreaseLevel();
            if (this.pheromone.getLevel() <= 0) {
                this.pheromone = null;
            }
        }
    }
}

module.exports = GroundCell;