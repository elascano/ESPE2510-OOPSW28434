class AntEater {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.state = 'HUNGRY'; 
        this.antsEaten = 0;
        this.eatingTicks = 0;
        this.sleepingTicks = 0;
    }

    update(tick, simulator) {
        const currentCell = simulator.getCell(this.x, this.y);

        switch (this.state) {
            case 'HUNGRY':
                this.behaveHungry(simulator, currentCell);
                break;
            case 'EATING':
                this.behaveEating(tick, simulator, currentCell);
                break;
            case 'SLEEPING':
                this.behaveSleeping(tick);
                break;
        }
    }

    behaveHungry(simulator, currentCell) {
        
        if (currentCell && currentCell.ants.length > 0) {
            this.state = 'EATING';
            this.eatingTicks = 0;
        } else {
            
            this.moveRandomly(simulator);
        }
    }

    behaveEating(tick, simulator, currentCell) {
        this.eatingTicks++;

        if (this.eatingTicks >= 10) {
            
            if (currentCell && currentCell.ants.length > 0) {
                const ant = currentCell.ants[0];
                currentCell.removeAnt(ant);
                this.antsEaten++;
                
                
                ant.colony.removeAnt(ant);

                
                if (this.antsEaten >= 50) {
                    this.state = 'SLEEPING';
                    this.sleepingTicks = 0;
                } 
                
                else if (currentCell.ants.length > 0) {
                    this.eatingTicks = 0; 
                } else {
                    this.state = 'HUNGRY';
                }
            } else {
                this.state = 'HUNGRY';
            }
        }
    }

    behaveSleeping(tick) {
        this.sleepingTicks++;

        if (this.sleepingTicks >= 600) {
            this.state = 'HUNGRY';
            this.antsEaten = 0;
        }
    }

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
            oldCell.removeAntEater(this);
            newCell.addAntEater(this);
            this.x = newX;
            this.y = newY;
        }
    }
}

module.exports = AntEater;