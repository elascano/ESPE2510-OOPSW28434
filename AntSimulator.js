/**
 * AntSimulator.js
 * Clase principal que gestiona la cuadrícula, entidades y el bucle de simulación.
 */
const GroundCell = require('./GroundCell');
const Colony = require('./Colony'); 
const AntEater = require('./AntEater'); 

class AntSimulator {
    /**
     * @param {number} width - Ancho de la cuadrícula.
     * @param {number} height - Alto de la cuadrícula.
     * @param {number} tickDuration - Duración de cada tick en ms.
     */
    constructor(width, height, tickDuration = 100) {
        this.width = width;
        this.height = height;
        this.tickDuration = tickDuration;
        this.grid = this.createGrid(width, height);
        this.colonies = [];
        this.antEaters = [];
        this.currentTick = 0;
    }

    createGrid(width, height) {
        const grid = [];
        for (let x = 0; x < width; x++) {
            grid[x] = [];
            for (let y = 0; y < height; y++) {
                grid[x][y] = new GroundCell(x, y);
            }
        }
        return grid;
    }

    addColony(colony) {
        this.colonies.push(colony);
        // Asegurar que las hormigas iniciales de la colonia estén en la celda del nido
        const nestCell = this.getCell(colony.nest.x, colony.nest.y);
        if (nestCell) {
             colony.ants.forEach(ant => nestCell.addResident(ant));
        }
    }

    addAntEater(antEater) {
        this.antEaters.push(antEater);
        const cell = this.getCell(antEater.x, antEater.y);
        if (cell) {
            cell.addResident(antEater);
        }
    }

    getCell(x, y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return this.grid[x][y];
        }
        return null;
    }

    /**
     * Inicia y ejecuta el bucle de simulación.
     */
    async runSimulation(totalTicks) {
        for (let tick = 0; tick < totalTicks; tick++) {
            this.currentTick = tick;
            
            this.update(tick);
            
            if (tick % 10 === 0 && tick > 0) {
                const totalAnts = this.colonies.reduce((sum, col) => sum + col.ants.length, 0);
                // console.log(`\n--- Tick ${tick} | Hormigas: ${totalAnts} ---`);
            }
            
            await new Promise(resolve => setTimeout(resolve, this.tickDuration));
        }
    }

    update(tick) {
        // 1. Evaporación de feromonas
        for (let x = 0; x < this.width; x++) {
            for (let y = 0; y < this.height; y++) {
                this.grid[x][y].updatePheromone();
            }
        }

        // 2. Actualizar Colonias (Hormigas)
        for (const colony of this.colonies) {
            colony.update(tick);
        }

        // 3. Actualizar Osos Hormigueros
        for (const antEater of this.antEaters) {
            antEater.update(tick, this);
        }
    }

    /**
     * Obtiene las celdas vecinas (incluyendo diagonales) dentro del mapa.
     */
    getNeighborCells(x, y) {
        const neighbors = [];
        const directions = [
            {dx: 0, dy: -1}, {dx: 1, dy: 0}, {dx: 0, dy: 1}, {dx: -1, dy: 0},
            {dx: 1, dy: -1}, {dx: 1, dy: 1}, {dx: -1, dy: 1}, {dx: -1, dy: -1}
        ];

        for (const dir of directions) {
            const newX = x + dir.dx;
            const newY = y + dir.dy;
            const cell = this.getCell(newX, newY);
            if (cell) {
                neighbors.push({ cell: cell, direction: dir });
            }
        }
        return neighbors;
    }
    
    /**
     * Obtiene los vecinos con su distancia Manhattan a un punto objetivo.
     */
    getNeighborsWithDistance(currentX, currentY, targetX, targetY) {
        const neighbors = this.getNeighborCells(currentX, currentY);
        
        neighbors.forEach(neighbor => {
            const distX = Math.abs(neighbor.cell.x - targetX);
            const distY = Math.abs(neighbor.cell.y - targetY);
            neighbor.distance = distX + distY; // Distancia Manhattan
        });
        
        return neighbors;
    }
}

module.exports = AntSimulator;