const Nest = require('./Nest');
const Ant = require('./Ant');



class Colony {
    constructor(name, nestX, nestY, simulator) {
        this.name = name;
        this.nest = new Nest(nestX, nestY, this);
        this.ants = [];
        this.simulator = simulator;
        
        
        this.createAnt();
    }

    createAnt() {
        const ant = new Ant(this.nest.x, this.nest.y, this);
        this.ants.push(ant);
        
        
        const cell = this.simulator.getCell(this.nest.x, this.nest.y);
        if (cell) {
            cell.addAnt(ant);
        }
        
        return ant;
    }

    update(tick, simulator) {
        
        for (let i = this.ants.length - 1; i >= 0; i--) {
            const ant = this.ants[i];
            ant.update(tick, simulator);
            
            
            if (ant.weight <= 0) {
                const cell = simulator.getCell(ant.x, ant.y);
                if (cell) {
                    cell.removeAnt(ant);
                }
                this.ants.splice(i, 1);
            }
        }
    }

    removeAnt(ant) {
        const index = this.ants.indexOf(ant);
        if (index > -1) {
            this.ants.splice(index, 1);
        }
    }
}

module.exports = Colony;