const Nest = require('./Nest');
const Ant = require('./Ant');

class Colony {
    constructor(name, nestX, nestY, simulator) {
        this.name = name;
        this.nest = new Nest(nestX, nestY, this);
        this.ants = [];
        this.simulator = simulator;

        // Crear la primera hormiga al inicio
        this.spawnAnt();
    }

    spawnAnt() {
        const ant = new Ant(this.nest.x, this.nest.y, this);
        this.ants.push(ant);

        const cell = this.simulator.getCell(this.nest.x, this.nest.y);
        if (cell) {
            cell.addAnt(ant);
        }

        return ant;
    }

    update(tick, simulator) {
        // Actualizar cada hormiga
        for (let i = this.ants.length - 1; i >= 0; i--) {
            const ant = this.ants[i];
            ant.update(tick, simulator);

            // Eliminar hormigas que pierden todo su peso
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
        const idx = this.ants.indexOf(ant);
        if (idx !== -1) {
            this.ants.splice(idx, 1);
        }
    }
}

module.exports = Colony;
