class Ant {
    constructor(x, y, colony) {
        this.x = x;
        this.y = y;
        this.colony = colony;

        this.weight = 1;
        this.carryingFood = false;
        this.foodAmount = 0;

        this.direction = this.randomDirection();
        this.lastWeightDecreaseTick = 0;

        this.inNest = true;
        this.ticksSinceCreation = 0;
        this.stepsSinceLastFood = 0;
    }

    randomDirection() {
        const moves = [
            { dx: 0, dy: -1 }, { dx: 1, dy: 0 }, { dx: 0, dy: 1 }, { dx: -1, dy: 0 },
            { dx: 1, dy: -1 }, { dx: 1, dy: 1 }, { dx: -1, dy: 1 }, { dx: -1, dy: -1 }
        ];
        return moves[Math.floor(Math.random() * moves.length)];
    }

    update(tick, simulator) {
        this.ticksSinceCreation++;

        if (!this.inNest && tick - this.lastWeightDecreaseTick >= 50) {
            this.weight = Math.max(0, this.weight - 1);
            this.lastWeightDecreaseTick = tick;
            this.stepsSinceLastFood++;
        }

        const currentCell = simulator.getCell(this.x, this.y);
        const insideNest = this.x === this.colony.nest.x && this.y === this.colony.nest.y;

        if (insideNest) {
            this.performInNest(tick, simulator, currentCell);
        } else {
            this.performOutsideNest(tick, simulator, currentCell);
        }
    }

    performInNest(tick, simulator, cell) {
        this.inNest = true;

        // Si trae comida, la deposita
        if (this.carryingFood && this.foodAmount > 0) {
            this.colony.nest.addFood(this.foodAmount);
            console.log(Hormiga entregó ${this.foodAmount}mg al nido ${this.colony.name});
            this.foodAmount = 0;
            this.carryingFood = false;
            this.stepsSinceLastFood = 0;
        }

        // Recupera energía del alimento del nido
        if (this.colony.nest.foodAmount > 0 && this.weight < 5) {
            const portion = this.colony.nest.takeFood(1);
            this.weight = Math.min(5, this.weight + portion.amount);
        }

        // Sale del nido si está lista
        if (this.weight >= 2 && this.ticksSinceCreation % 5 === 0) {
            this.exitNest(simulator);
        }
    }

    performOutsideNest(tick, simulator, currentCell) {
        this.inNest = false;

        if (!this.carryingFood) {
            // Busca comida
            if (currentCell.getFoodAmount() > 0) {
                const capacity = 5 - this.foodAmount;
                const toTake = Math.min(capacity, currentCell.getFoodAmount());
                const collected = currentCell.removeFood(toTake);

                this.foodAmount += collected.amount;
                this.carryingFood = this.foodAmount > 0;

                if (this.carryingFood) {
                    console.log(Hormiga recogió ${collected.amount}mg en (${this.x}, ${this.y}));
                    this.goTowardNest(simulator);
                }
            } else {
                this.seekFood(simulator);
            }
        } else {
            this.headBackToNest(simulator, currentCell);
        }
    }

    seekFood(simulator) {
        const nearbyCells = simulator.getNeighborCells(this.x, this.y);

        const cellsWithPheromone = nearbyCells.filter(n =>
            n.cell.getPheromoneLevel() > 0 && !this.isNest(n.cell)
        );

        if (cellsWithPheromone.length > 0) {
            cellsWithPheromone.sort((a, b) => a.cell.getPheromoneLevel() - b.cell.getPheromoneLevel());
            this.moveTo(simulator, cellsWithPheromone[0].cell.x, cellsWithPheromone[0].cell.y);
        } else {
            const available = nearbyCells.filter(n => !this.isNest(n.cell));
            if (available.length > 0) {
                const randomCell = available[Math.floor(Math.random() * available.length)];
                this.moveTo(simulator, randomCell.cell.x, randomCell.cell.y);
            } else {
                this.moveRandom(simulator);
            }
        }
    }

    headBackToNest(simulator, currentCell) {
        currentCell.dropPheromone(100);
        this.goTowardNest(simulator);
    }

    isNest(cell) {
        return cell.x === this.colony.nest.x && cell.y === this.colony.nest.y;
    }

    moveTo(simulator, newX, newY) {
        const origin = simulator.getCell(this.x, this.y);
        const target = simulator.getCell(newX, newY);

        if (origin && target) {
            origin.removeAnt(this);
            target.addAnt(this);
            this.x = newX;
            this.y = newY;
        }
    }

    moveRandom(simulator) {
        const options = simulator.getNeighborCells(this.x, this.y);
        if (options.length > 0) {
            const randomMove = options[Math.floor(Math.random() * options.length)];
            this.moveTo(simulator, randomMove.cell.x, randomMove.cell.y);
        }
    }

    goTowardNest(simulator) {
        const adjacents = simulator.getNeighborCells(this.x, this.y);
        if (adjacents.length === 0) return;

        adjacents.forEach(neighbor => {
            const dx = Math.abs(neighbor.cell.x - this.colony.nest.x);
            const dy = Math.abs(neighbor.cell.y - this.colony.nest.y);
            neighbor.distance = dx + dy;
        });

        adjacents.sort((a, b) => a.distance - b.distance);
        this.moveTo(simulator, adjacents[0].cell.x, adjacents[0].cell.y);
    }

    exitNest(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        if (neighbors.length === 0) return;

        const validCells = neighbors.filter(n => !this.isNest(n.cell));
        if (validCells.length > 0) {
            const chosen = validCells[Math.floor(Math.random() * validCells.length)];
            this.moveTo(simulator, chosen.cell.x, chosen.cell.y);
        }
    }
}

module.exports = Ant;
