/**
 * Pheromone.js
 * Clase que representa un rastro de feromona.
 */
class Pheromone {
    /**
     * @param {number} level - El nivel inicial de feromona.
     */
    constructor(level = 100) {
        this.level = level;
    }

    /**
     * Disminuye el nivel de feromona (evaporación).
     */
    decreaseLevel() {
        // La evaporación es lenta para que el rastro dure más
        this.level = Math.max(0, this.level - 0.5); 
    }

    getLevel() {
        return this.level;
    }
}

module.exports = Pheromone;