class AntEater {
    constructor(x, y) {
        this.x = x;
        this.y = y;

        this.state = 'HUNGRY'; // Estados: HUNGRY, EATING, SLEEPING
        this.antsConsumed = 0;
        this.ticksEating = 0;
        this.ticksSleeping = 0;
    }

    update(tick, simulator) {
        const cell = simulator.getCell(this.x, this.y);

        switch (this.state) {
            case 'HUNGRY':
                this.actHungry(simulator, cell);
                break;
            case 'EATING':
                this.actEating(tick, simulator, cell);
                break;
            case 'SLEEPING':
                this.actSleeping(tick);
                break;
        }
    }

    actHungry(simulator, cell) {
        // Si hay hormigas en la celda, comienza a comer
        if (cell && cell.ants.length > 0) {
            this.state = 'EATING';
            this.ticksEating = 0;
        } else {
            // Si no, se mueve aleatoriamente
            this.moveRandom(simulator);
        }
    }

    actEating(tick, simulator, cell) {
        this.ticksEating++;

        if (this.ticksEating >= 10) {
            if (cell && cell.ants.length > 0) {
                const ant = cell.ants[0];
                cell.removeAnt(ant);
                this.antsConsumed++;

                // Eliminar hormiga de la colonia
                ant.colony.removeAnt(ant);

                // Verificar si debe dormir
                if (this.antsConsumed >= 50) {
                    this.state = 'SLEEPING';
                    this.ticksSleeping = 0;
                } 
                // Si aún hay hormigas en la celda, reinicia el conteo de comer
                else if (cell.ants.length > 0) {
                    this.ticksEating = 0;
                } else {
                    this.state = 'HUNGRY';
                }
            } else {
                this.state = 'HUNGRY';
            }
        }
    }

    actSleeping(tick) {
        this.ticksSleeping++;

        if (this.ticksSleeping >= 600) {
            this.state = 'HUNGRY';
            this.antsConsumed = 0;
        }
    }

    moveRandom(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        if (neighbors.length > 0) {
            const target = neighbors[Math.floor(Math.random() * neighbors.length)];
            this.moveTo(simulator, target.cell.x, target.cell.y);
        }
    }

    moveTo(simulator, x, y) {
        const origin = simulator.getCell(this.x, this.y);
        const target = simulator.getCell(x, y);

        if (origin && target) {
            origin.removeAntEater(this);
            target.addAntEater(this);
            this.x = x;
            this.y = y;
        }
    }
}

module.exports = AntEater;
