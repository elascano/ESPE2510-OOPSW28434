import { Cell } from './Cell.js';

export class Area {
    constructor(width, height) {
        this.width = width;
        this.height = height;
        this.grid = this._initializeGrid(width, height);
        this.colonies = [];
        this.antEaters = [];
        this.allAnts = [];
    }

    _initializeGrid(width, height) {
        const grid = [];
        for (let x = 0; x < width; x++) {
            grid[x] = [];
            for (let y = 0; y < height; y++) {
                grid[x][y] = new Cell(x, y);
            }
        }
        return grid;
    }
    
    getCell(x, y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return this.grid[x][y];
        }
        return null;
    }

    getNeighboringCells(cell) {
        const neighbors = [];
        const x = cell.x;
        const y = cell.y;
        
        for (let dx = -1; dx <= 1; dx++) {
            for (let dy = -1; dy <= 1; dy++) {
                if (dx === 0 && dy === 0) continue;
                
                const neighbor = this.getCell(x + dx, y + dy);
                if (neighbor) {
                    neighbors.push(neighbor);
                }
            }
        }
        return neighbors;
    }
    
    decayPheromones() {
        for (let x = 0; x < this.width; x++) {
            for (let y = 0; y < this.height; y++) {
                this.grid[x][y].decayPheromone();
            }
        }
    }
    
    cleanup() {
        this.allAnts = this.allAnts.filter(ant => ant.getWeight() > 0);
    }
    
    findBestCellByPheromone(neighbors, mode, preferredDirection) {
        let bestCell = null;
        let bestLevel = (mode === 'increasing') ? -1 : 101;
        
        for (const cell of neighbors) {
            const level = cell.getPheromoneLevel();
            
            if (mode === 'increasing') {
                if (level > bestLevel) {
                    bestLevel = level;
                    bestCell = cell;
                }
            } else {
                if (level > 0 && level < bestLevel) {
                    bestLevel = level;
                    bestCell = cell;
                }
            }
        }
        return bestCell;
    }

    getRandomCell(neighbors, prioritizePreviousDirection = false, preferredDirection = null) {
        if (prioritizePreviousDirection && preferredDirection) {
            const potentialCells = neighbors.filter(cell => cell.getDirectionFrom(this.getCell(cell.x, cell.y)) === preferredDirection);
            if (potentialCells.length > 0) {
                if (Math.random() < 0.7) { 
                    return potentialCells[Math.floor(Math.random() * potentialCells.length)];
                }
            }
        }
        return neighbors[Math.floor(Math.random() * neighbors.length)];
    }
    
    getBestExitCell(nestCell) {
        const neighbors = this.getNeighboringCells(nestCell);
        return neighbors[Math.floor(Math.random() * neighbors.length)];
    }
}