import { Food, Pheromone } from './components.js';
import { MAX_ANT_WEIGHT, MIN_WEIGHT_TO_LEAVE_NEST, FOOD_CARRY_CAPACITY,
         PHEROMONE_MAX_STRENGTH, WEIGHT_DECREASE_TICKS, WEIGHT_DECREASE_AMOUNT,
         ANT_EATER_DIGEST_TICKS } from './constants.js';

export class Ant {
    constructor(name, nestLocation, colony, area) {
        this.name = name;
        this.weight = 1.0;
        this.position = nestLocation;
        this.isInNest = true;
        this.foodCarried = 0;
        this.colony = colony;
        this.area = area;
        this.ticksSinceWeightLoss = 0;
        this.isAlive = true;
        area.getCell(nestLocation).addAnt(this);
    }

    getCurrentCell() {
        return this.area.getCell(this.position);
    }

    move(newPos, action="Se mueve") {
        const oldCell = this.getCurrentCell();
        const newCell = this.area.getCell(newPos);
        if (!newCell) return false;

        if (oldCell) oldCell.removeAnt(this);

        this.position = newPos;
        newCell.addAnt(this);

        let carryStatus = this.foodCarried > 0 ? `(Carga: ${this.foodCarried}mg)` : "";
        if (this.foodCarried > 0 && !this.isInNest) {
            newCell.dropPheromone(PHEROMONE_MAX_STRENGTH);
            carryStatus += ", Deja Feromona.";
        }
        console.log(`Ant ${this.name} en ${this.position}: ${action} ${carryStatus}`);
        return true;
    }

    decreaseWeight() {
        this.ticksSinceWeightLoss++;
        if (this.ticksSinceWeightLoss >= WEIGHT_DECREASE_TICKS) {
            this.weight = Math.max(1.0, this.weight - WEIGHT_DECREASE_AMOUNT);
            this.ticksSinceWeightLoss = 0;
        }
    }

    behaveInNest() {
        const nest = this.colony.nest;
        if (nest.foodStock >= 1 && this.weight < MAX_ANT_WEIGHT) {
            const eaten = nest.retrieveFood(1);
            this.weight = Math.min(MAX_ANT_WEIGHT, this.weight + eaten);
            console.log(`Ant ${this.name} en ${this.position}: Come ${eaten}mg. Peso: ${this.weight.toFixed(1)}mg.`);
        }
        if (this.weight >= MIN_WEIGHT_TO_LEAVE_NEST) {
            this.isInNest = false;
            this.moveOutOfNest();
        }
    }

    behaveOutsideNest() {
        const currentCell = this.getCurrentCell();
        const nestLoc = this.colony.nest.location;

        if (this.position[0] === nestLoc[0] && this.position[1] === nestLoc[1]) {
            if (this.foodCarried > 0) {
                this.colony.nest.addFood(new Food(this.foodCarried));
                this.foodCarried = 0;
            }
            this.isInNest = true;
            console.log(`Ant ${this.name} ha entrado al nido en ${this.position}.`);
            return;
        }

        if (this.foodCarried === 0 && currentCell.foodPile && currentCell.foodPile.getAmount() > 0) {
            const canCarry = FOOD_CARRY_CAPACITY - this.foodCarried;
            const { food, empty } = currentCell.foodPile.requestFood(canCarry);
            this.foodCarried += food.amount;
            if (empty) currentCell.foodPile = null;
            console.log(`Ant ${this.name} en ${this.position}: ENCUENTRA y recoge ${food.amount}mg de comida. Comienza regreso al Nido.`);
            this.moveToNest();
        } else if (this.foodCarried > 0) {
            this.moveToNest();
        } else {
            this.moveToFood();
        }
    }

    moveToNest() {
        const neighbors = this.area.getNeighbors(this.position);
        let maxLevel = -1;
        let targetCell = null;
        neighbors.forEach(cell => {
            const level = cell.pheromone ? cell.pheromone.getLevel() : 0;
            if (level > maxLevel) {
                maxLevel = level;
                targetCell = cell;
            }
        });
        if (!targetCell || maxLevel === 0) {
            const preferred = this._getNestPreferredNeighbors(neighbors);
            targetCell = preferred.length > 0 ? preferred[Math.floor(Math.random() * preferred.length)] :
                                               neighbors[Math.floor(Math.random() * neighbors.length)];
        }
        this.move(targetCell.position, "Busca Nido (Feromona Creciente / Geométrica)");
    }

    moveToFood() {
        const neighbors = this.area.getNeighbors(this.position);
        let bestCells = [];
        let lowestLevel = PHEROMONE_MAX_STRENGTH + 1;
        neighbors.forEach(cell => {
            const level = cell.pheromone ? cell.pheromone.getLevel() : 0;
            if (level > 0 && level < lowestLevel) { lowestLevel = level; bestCells = [cell]; }
            else if (level === lowestLevel && level > 0) bestCells.push(cell);
        });
        let targetCell = bestCells.length > 0 ? bestCells[Math.floor(Math.random() * bestCells.length)]
                                               : neighbors[Math.floor(Math.random() * neighbors.length)];
        this.move(targetCell.position, bestCells.length > 0 ? "Busca Comida (Feromona Decreciente)" : "Busca Comida (Aleatorio)");
    }

    moveOutOfNest() {
        const neighbors = this.area.getNeighbors(this.position);
        const pheromoneNeighbors = neighbors.filter(c => c.pheromone && c.pheromone.getLevel() > 0);
        const targetCell = pheromoneNeighbors.length > 0 ? 
                           pheromoneNeighbors[Math.floor(Math.random() * pheromoneNeighbors.length)]
                           : neighbors[Math.floor(Math.random() * neighbors.length)];
        this.move(targetCell.position, "Sale del Nido");
    }

    _getNestPreferredNeighbors(neighbors) {
        const [nx, ny] = this.colony.nest.location;
        const dx = this.position[0] > nx ? -1 : (this.position[0] < nx ? 1 : 0);
        const dy = this.position[1] > ny ? -1 : (this.position[1] < ny ? 1 : 0);
        return neighbors.filter(c => (c.position[0] - this.position[0] === dx) || (c.position[1] - this.position[1] === dy));
    }
}

export class AntEater {
    constructor(position, area) {
        this.position = position;
        this.area = area;
        this.state = "hungry_roaming";
        this.digestTimer = 0;
        this.area.getCell(position).antEaters.push(this);
        console.log(`Oso Hormiguero aparece en (${this.position[0]}, ${this.position[1]}).`);
    }

    getCurrentCell() {
        return this.area.getCell(this.position);
    }

    roam() {
        const neighbors = this.area.getNeighbors(this.position);
        if (neighbors.length > 0) {
            const newCell = neighbors[Math.floor(Math.random() * neighbors.length)];
            const oldCell = this.getCurrentCell();
            oldCell.antEaters = oldCell.antEaters.filter(ae => ae !== this);
            this.position = newCell.position;
            newCell.antEaters.push(this);
            console.log(`Oso Hormiguero en (${this.position[0]}, ${this.position[1]}): Vaga hambriento.`);
        }
    }

    update() {
        const currentCell = this.getCurrentCell();
        if (this.state === "hungry_roaming") {
            if (currentCell.ants.length > 0) {
                const antEaten = currentCell.ants[0];
                antEaten.isAlive = false;
                currentCell.removeAnt(antEaten);
                this.state = "eating_digest";
                this.digestTimer = 0;
                console.log(`Oso Hormiguero en ${this.position}: COME a ${antEaten.name}. Comienza a digerir por ${ANT_EATER_DIGEST_TICKS} ticks.`);
            } else this.roam();
        } else if (this.state === "eating_digest") {
            this.digestTimer++;
            if (this.digestTimer >= ANT_EATER_DIGEST_TICKS) {
                console.log(`Oso Hormiguero en ${this.position}: Termina la digestión. Vuelve a vagar.`);
                this.state = "hungry_roaming";
                this.digestTimer = 0;
            } else console.log(`Oso Hormiguero en ${this.position}: Digiriendo (Restan ${ANT_EATER_DIGEST_TICKS - this.digestTimer} ticks).`);
        }
    }
}
