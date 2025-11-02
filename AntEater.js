/**
 * AntEater.js
 * Clase que representa un depredador (Oso Hormiguero).
 */
class AntEater {
    /**
     * @param {number} x - Coordenada X inicial.
     * @param {number} y - Coordenada Y inicial.
     */
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.state = 'HUNGRY'; // HUNGRY, EATING, SLEEPING
        this.antsEaten = 0;
        this.eatingTicks = 0;
        this.sleepingTicks = 0;
        this.EATING_TIME = 10; // Ticks para comer una hormiga
        this.SLEEPING_TIME = 600; // Ticks para dormir
        this.SATURATION_LIMIT = 50; // Límite de hormigas para saturarse
        this.constructor.name = 'AntEater'; 
    }

    update(tick, simulator) {
        const currentCell = simulator.getCell(this.x, this.y);

        switch (this.state) {
            case 'HUNGRY':
                this.behaveHungry(simulator, currentCell);
                break;
            case 'EATING':
                this.behaveEating(currentCell);
                break;
            case 'SLEEPING':
                this.behaveSleeping();
                break;
        }
    }

    behaveHungry(simulator, currentCell) {
        if (currentCell && currentCell.getAnts().length > 0) {
            this.state = 'EATING';
            this.eatingTicks = 0;
        } else {
            // Moverse al azar para buscar
            this.moveRandomly(simulator);
        }
    }

    behaveEating(currentCell) {
        this.eatingTicks++;

        if (this.eatingTicks >= this.EATING_TIME) {
            
            if (currentCell && currentCell.getAnts().length > 0) {
                const ant = currentCell.getAnts()[0]; // Comer la primera hormiga
                
                // 1. Eliminar de la celda y de la colonia
                currentCell.removeResident(ant);
                ant.colony.removeAnt(ant); 
                
                this.antsEaten++;
                // console.log(`   *** Oso Hormiguero comió una hormiga de ${ant.colony.name}. Comidas: ${this.antsEaten}`);

                // 2. Control de estado
                if (this.antsEaten >= this.SATURATION_LIMIT) {
                    this.state = 'SLEEPING';
                    this.sleepingTicks = 0;
                } else if (currentCell.getAnts().length > 0) {
                    this.eatingTicks = 0; // Seguir comiendo si hay más hormigas
                } else {
                    this.state = 'HUNGRY'; // Volver a buscar si la celda se vació
                }
            } else {
                this.state = 'HUNGRY';
            }
        }
    }

    behaveSleeping() {
        this.sleepingTicks++;
        // No hace nada mientras duerme (no se mueve ni come)
        if (this.sleepingTicks >= this.SLEEPING_TIME) {
            this.state = 'HUNGRY';
            this.antsEaten = 0; 
        }
    }

    // --- Utilidades de Movimiento ---

    moveRandomly(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        if (neighbors.length > 0) {
            const randomNeighbor = neighbors[Math.floor(Math.random() * neighbors.length)];
            this.moveTo(simulator, randomNeighbor.cell.x, randomNeighbor.cell.y);
        }
    }

    moveTo(simulator, newX, newY) {
        const oldCell = simulator.getCell(this.x, this.y);
        const newCell = simulator.getCell(newX, newY);
        
        if (oldCell && newCell) {
            oldCell.removeResident(this);
            newCell.addResident(this);
            this.x = newX;
            this.y = newY;
        }
    }
}

module.exports = AntEater;