import { Cell } from './Cell_model.js';
import { Colony } from './Colony_model.js';

export class Area {
    constructor(width, height) {
        this.width = width;
        this.height = height;
        this.grid = this.createGrid(width, height);
        this.colonies = [];
        this.antEaters = [];
    }

    createGrid(width, height) {
        const grid = [];
        for (let y = 0; y < height; y++) {
            grid[y] = [];
            for (let x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y); 
            }
        }
        return grid;
    }

    getCell(x, y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return this.grid[y][x];
        }
        return null;
    }

    addColony(colony) {
        this.colonies.push(colony);
        const pos = colony.getNestPosition();
        const nestCell = this.getCell(pos.x, pos.y);
        if (nestCell) {
            nestCell.isNestLocation = true; 
        }
    }

    addAntEater(antEater) {
        this.antEaters.push(antEater);
        const pos = antEater.getPosition();
        const cell = this.getCell(pos.x, pos.y);
        if (cell) {
            cell.addAntEater(antEater);
        }
    }
    
    placeAnts(colony) {
    colony.ants.forEach(ant => {
        const cell = this.getCell(ant.position.x, ant.position.y);
        if (cell) cell.addAnt(ant);
    });
    console.log(`Placed ${colony.ants.length} ants in the area.`);
 }
}