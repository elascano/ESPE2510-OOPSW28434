/**
 * Ant.js
 * Clase que representa una hormiga con comportamiento de forrajeo.
 */
class Ant {
    /**
     * @param {number} x - Coordenada X inicial.
     * @param {number} y - Coordenada Y inicial.
     * @param {Colony} colony - Referencia a la colonia a la que pertenece.
     */
    constructor(x, y, colony) {
        this.x = x;
        this.y = y;
        this.colony = colony;
        this.weight = 3; // Nivel de energía inicial (se consume fuera del nido)
        this.carryingFood = false;
        this.foodAmount = 0;
        this.lastWeightDecreaseTick = 0;
        this.inNest = true;
        this.maxCarry = 5; // Capacidad máxima de carga
        // Propiedad para identificar el tipo de entidad
        this.constructor.name = 'Ant'; 
    }

    /**
     * Función principal de actualización del comportamiento.
     */
    update(tick, simulator) {
        const currentCell = simulator.getCell(this.x, this.y);
        const isInNest = this.isNestCell(currentCell);

        // Consumo de peso (energía)
        if (!isInNest && tick - this.lastWeightDecreaseTick >= 50) {
            this.weight = Math.max(0, this.weight - 1);
            this.lastWeightDecreaseTick = tick;
        }

        if (isInNest) {
            this.behaveInNest(simulator);
        } else {
            this.behaveOutsideNest(simulator, currentCell);
        }
    }

    behaveInNest(simulator) {
        this.inNest = true;
        const nest = this.colony.nest;

        // 1. Entregar comida
        if (this.carryingFood && this.foodAmount > 0) {
            nest.addFood(this.foodAmount);
            // console.log(`[${this.colony.name}] Ant entregó ${this.foodAmount}mg de comida.`);
            this.foodAmount = 0;
            this.carryingFood = false;
        }

        // 2. Comer (reponer peso)
        if (nest.getFoodAmount() > 0 && this.weight < this.maxCarry) {
            const food = nest.takeFood(1);
            this.weight = Math.min(this.maxCarry, this.weight + food.amount);
        }

        // 3. Dejar el nido para buscar si tiene suficiente energía
        if (this.weight >= 2) {
            this.leaveNest(simulator);
        }
    }

    behaveOutsideNest(simulator, currentCell) {
        this.inNest = false;

        if (!this.carryingFood) {
            // 1. Buscar y recoger comida
            if (currentCell.getFoodAmount() > 0) {
                const maxCanCarry = this.maxCarry - this.foodAmount;
                const foodToTake = Math.min(maxCanCarry, currentCell.getFoodAmount());
                const food = currentCell.removeFood(foodToTake);
                this.foodAmount += food.amount;
                this.carryingFood = this.foodAmount > 0;

                if (this.carryingFood) {
                    // console.log(`[${this.colony.name}] Ant recogió ${food.amount}mg en (${this.x}, ${this.y}).`);
                    this.returnToNest(simulator, currentCell); // Volver inmediatamente
                }
            } else {
                // 2. Buscar comida (moverse siguiendo feromonas)
                this.searchForFood(simulator);
            }
        } else {
            // 3. Volver al nido
            this.returnToNest(simulator, currentCell);
        }
    }

    searchForFood(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        
        // Prioridad: Seguir feromonas de comida
        const pheromoneNeighbors = neighbors.filter(n => n.cell.getPheromoneLevel() > 0);
        
        if (pheromoneNeighbors.length > 0) {
            // Ir a la celda con más feromona
            pheromoneNeighbors.sort((a, b) => b.cell.getPheromoneLevel() - a.cell.getPheromoneLevel());
            this.moveTo(simulator, pheromoneNeighbors[0].cell.x, pheromoneNeighbors[0].cell.y);
        } else {
            // Movimiento aleatorio
            this.moveRandomly(simulator);
        }
    }

    returnToNest(simulator, currentCell) {
        // 1. Dejar feromona para que otras hormigas sigan el rastro
        currentCell.dropPheromone(10); // Menos nivel para no saturar

        // 2. Moverse hacia el nido
        this.moveTowardNest(simulator);
    }
    
    // --- Utilidades de Movimiento ---

    isNestCell(cell) {
        return cell.x === this.colony.nest.x && cell.y === this.colony.nest.y;
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

    moveRandomly(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        if (neighbors.length > 0) {
            const randomNeighbor = neighbors[Math.floor(Math.random() * neighbors.length)];
            this.moveTo(simulator, randomNeighbor.cell.x, randomNeighbor.cell.y);
        }
    }

    moveTowardNest(simulator) {
        const neighbors = simulator.getNeighborsWithDistance(this.x, this.y, this.colony.nest.x, this.colony.nest.y);
        
        if (neighbors.length === 0) return;

        // Moverse al vecino con la distancia Manhattan más pequeña al nido
        neighbors.sort((a, b) => a.distance - b.distance);
        
        this.moveTo(simulator, neighbors[0].cell.x, neighbors[0].cell.y);
    }
    
    leaveNest(simulator) {
        const neighbors = simulator.getNeighborCells(this.x, this.y);
        
        // Preferir celdas que NO sean el nido para salir
        const availableNeighbors = neighbors.filter(n => !this.isNestCell(n.cell));
        
        if (availableNeighbors.length > 0) {
            const randomNeighbor = availableNeighbors[Math.floor(Math.random() * availableNeighbors.length)];
            this.moveTo(simulator, randomNeighbor.cell.x, randomNeighbor.cell.y);
        }
    }
}

module.exports = Ant;