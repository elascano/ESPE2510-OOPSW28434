class Ant {
    constructor(x, y, colony) {
        this.x = x;
        this.y = y;
        this.colony = colony;
        this.weight = 1;
        this.carryingFood = false;
        this.foodAmount = 0;
        this.direction = this.getRandomDirection();
        this.lastWeightDecreaseTick = 0;
        this.inNest = true;
        this.ticksSinceCreation = 0;
        this.stepsSinceLastFood = 0;
    }

    getRandomDirection() {
        const directions = [
            {dx: 0, dy: -1}, {dx: 1, dy: 0}, {dx: 0, dy: 1}, {dx: -1, dy: 0},
            {dx: 1, dy: -1}, {dx: 1, dy: 1}, {dx: -1, dy: 1}, {dx: -1, dy: -1}
        ];
        return directions[Math.floor(Math.random() * directions.length)];
    }

    update(tick, simulator) {
        this.ticksSinceCreation++;
        
        
        if (!this.inNest && tick - this.lastWeightDecreaseTick >= 50) {
            this.weight = Math.max(0, this.weight - 1);
            this.lastWeightDecreaseTick = tick;
            this.stepsSinceLastFood++;
        }

        const currentCell = simulator.getCell(this.x, this.y);
        const isInNest = this.x === this.colony.nest.x && this.y === this.colony.nest.y;

        if (isInNest) {
            this.behaveInNest(tick, simulator, currentCell);
        } else {
            this.behaveOutsideNest(tick, simulator, currentCell);
        }
    }

    behaveInNest(tick, simulator, cell) {
        this.inNest = true;
        
        
        if (this.carryingFood && this.foodAmount > 0) {
            this.colony.nest.addFood(this.foodAmount);
            console.log(` Hormiga entregó ${this.foodAmount}mg de comida al nido ${this.colony.name}`);
            this.foodAmount = 0;
            this.carryingFood = false;
            this.stepsSinceLastFood = 0;
        }

        
        if (this.colony.nest.foodAmount > 0 && this.weight < 5) {
            const food = this.colony.nest.takeFood(1);
            this.weight = Math.min(5, this.weight + food.amount);
        }

        
        if (this.weight >= 2 && this.ticksSinceCreation % 5 === 0) {
            this.leaveNest(simulator);
        }
    }

    behaveOutsideNest(tick, simulator, currentCell) {
        this.inNest = false;
        
        if (!this.carryingFood) {
            
            if (currentCell.getFoodAmount() > 0) {
                
                const maxCanCarry = 5 - this.foodAmount;
                const foodToTake = Math.min(maxCanCarry, currentCell.getFoodAmount());
                const food = currentCell.removeFood(foodToTake);
                this.foodAmount += food.amount;
                this.carryingFood = this.foodAmount > 0;
                
                if (this.carryingFood) {
                    console.log(` Hormiga recogió ${food.amount}mg de comida en (${this.x}, ${this.y})`);
                    
                    this.moveTowardNest(simulator);
                }
            } else {
                
                this.searchForFood(simulator);
            }
        } else {
           
            this.returnToNest(simulator, currentCell);
        }
    }

    searchForFood(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        
        
        const pheromoneNeighbors = neighbors.filter(n => 
            n.cell.getPheromoneLevel() > 0 && 
            !this.isNestCell(n.cell)
        );
        
        if (pheromoneNeighbors.length > 0) {
            
            pheromoneNeighbors.sort((a, b) => a.cell.getPheromoneLevel() - b.cell.getPheromoneLevel());
            this.moveTo(simulator, pheromoneNeighbors[0].cell.x, pheromoneNeighbors[0].cell.y);
        } else {
            
            const nonNestNeighbors = neighbors.filter(n => !this.isNestCell(n.cell));
            if (nonNestNeighbors.length > 0) {
                const randomNeighbor = nonNestNeighbors[Math.floor(Math.random() * nonNestNeighbors.length)];
                this.moveTo(simulator, randomNeighbor.cell.x, randomNeighbor.cell.y);
            } else {
                this.moveRandomly(simulator);
            }
        }
    }

    returnToNest(simulator, currentCell) {
        
        currentCell.dropPheromone(100);

        
        this.moveTowardNest(simulator);
    }

    isNestCell(cell) {
        return cell.x === this.colony.nest.x && cell.y === this.colony.nest.y;
    }

    moveTo(simulator, newX, newY) {
        const oldCell = simulator.getCell(this.x, this.y);
        const newCell = simulator.getCell(newX, newY);
        
        if (oldCell && newCell) {
            oldCell.removeAnt(this);
            newCell.addAnt(this);
            this.x = newX;
            this.y = newY;
        }
    }

    moveRandomly(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        if (neighbors.length > 0) {
            const randomNeighbor = neighbors[Math.floor(Math.random() * neighbors.length)];
            this.moveTo(simulator, randomNeighbor.cell.x, randomNeighbor.cell.y);
        }
    }

    moveTowardNest(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        
        if (neighbors.length === 0) return;

        
        neighbors.forEach(neighbor => {
            const distX = Math.abs(neighbor.cell.x - this.colony.nest.x);
            const distY = Math.abs(neighbor.cell.y - this.colony.nest.y);
            neighbor.distance = distX + distY;
        });

        
        neighbors.sort((a, b) => a.distance - b.distance);
        
        
        this.moveTo(simulator, neighbors[0].cell.x, neighbors[0].cell.y);
    }

    leaveNest(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        if (neighbors.length > 0) {
            
            const availableNeighbors = neighbors.filter(n => !this.isNestCell(n.cell));
            
            if (availableNeighbors.length > 0) {
                const randomNeighbor = availableNeighbors[Math.floor(Math.random() * availableNeighbors.length)];
                this.moveTo(simulator, randomNeighbor.cell.x, randomNeighbor.cell.y);
            }
        }
    }
}

module.exports = Ant;