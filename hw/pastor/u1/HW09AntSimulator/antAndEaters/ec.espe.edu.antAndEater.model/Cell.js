import { Pheromone } from './Pheromone.js';
export class Cell {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.ants = [];
        this.antEaters = [];
        this.pheromone = null;
        this.foodPile = null;
        this.nest = null;
    }

    addAgent(agent) {
        if (agent.constructor.name === 'Ant') {
            this.ants.push(agent);
        } else if (agent.constructor.name === 'AntEater') {
            this.antEaters.push(agent);
        }
    }

    removeAgent(agent) {
        if (agent.constructor.name === 'Ant') {
            this.ants = this.ants.filter(a => a !== agent);
        } else if (agent.constructor.name === 'AntEater') {
            this.antEaters = this.antEaters.filter(a => a !== agent);
        }
    }

    dropPheromone(pheromoneInstance) {
        if (this.pheromone) {
            this.pheromone.addLevel(new Pheromone(pheromoneInstance.getLevel()));
        } else {
            this.pheromone = pheromoneInstance;
        }
    }

    decayPheromone() {
        if (this.pheromone) {
            this.pheromone.decay();
            if (this.pheromone.getLevel() <= 0) {
                this.pheromone = null;
            }
        }
    }

    getPheromoneLevel() {
        return this.pheromone ? this.pheromone.getLevel() : 0;
    }

    getDirectionFrom(fromCell) {
        let dx = this.x - fromCell.x;
        let dy = this.y - fromCell.y;
        return `${dx},${dy}`;
    }
}