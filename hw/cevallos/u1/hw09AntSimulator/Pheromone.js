class Pheromone {
    constructor(level = 100) {
        this.level = level;
    }

    decreaseLevel() {
        this.level = Math.max(0, this.level - 1);
    }

    getLevel() {
        return this.level;
    }
}

module.exports = Pheromone;