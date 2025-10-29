export class Pheromone {
    static FULL_STRENGTH = 100;

    constructor(){
        this.level = Pheromone.FULL_STRENGTH;
    }

    getLevel() {
        return this.level;
    }

    decay() {
        this.level = Math.max(0, this.level - 1);
        return this.level;
    }
    
    addLevel(level) {
        this.level = Math.min(Pheromone.FULL_STRENGTH, this.level + level);
    }
}