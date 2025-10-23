import { Cell, FoodPile, Pheromone } from './components.js';
import { PHEROMONE_MAX_STRENGTH } from './constants.js';

export class Area {
    constructor(width, height) {
        this.width = width;
        this.height = height;
        this.cells = [];
        for (let x = 0; x < width; x++) {
            this.cells[x] = [];
            for (let y = 0; y < height; y++) {
                this.cells[x][y] = new Cell(x, y);
            }
        }
        this.colony = null;
    }

    getCell(position) {
        const [x, y] = position;
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return this.cells[x][y];
        }
        return null;
    }

    getNeighbors(position) {
        const [x, y] = position;
        const neighbors = [];
        for (let dx of [-1, 0, 1]) {
            for (let dy of [-1, 0, 1]) {
                if (dx === 0 && dy === 0) continue;
                const cell = this.getCell([x + dx, y + dy]);
                if (cell) neighbors.push(cell);
            }
        }
        return neighbors;
    }

    placeFoodPile(position, amount) {
        const cell = this.getCell(position);
        if (cell) {
            const pile = new FoodPile(amount);
            cell.foodPile = pile;
            pile.position = position;
        }
    }

    update() {
        for (let x = 0; x < this.width; x++) {
            for (let y = 0; y < this.height; y++) {
                this.cells[x][y].update();
            }
        }
    }

    display() {
        if (this.colony && this.colony.nest) {
            console.log(`Área ${this.width}x${this.height} creada. Nido en ${this.colony.nest.location}. Stock inicial: ${this.colony.nest.foodStock}mg.`);
        }
    }
}
