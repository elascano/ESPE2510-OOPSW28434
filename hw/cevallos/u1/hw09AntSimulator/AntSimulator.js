const GroundCell = require('./GroundCell');

class AntSimulator {
    constructor(width, height, tickDuration = 100) {
        this.width = width;
        this.height = height;
        this.tickDuration = tickDuration;
        this.grid = this.createGrid(width, height);
        this.colonies = [];
        this.antEaters = [];
        this.currentTick = 0;
        console.log(` Grid creado: ${width}x${height}`);
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
        console.log(` Colonia "${colony.name}" agregada en (${colony.nest.x}, ${colony.nest.y})`);
    }

    addAntEater(antEater) {
        this.antEaters.push(antEater);
        const cell = this.getCell(antEater.x, antEater.y);
        if (cell) {
            cell.addAntEater(antEater);
        }
        console.log(` Oso hormiguero agregado en (${antEater.x}, ${antEater.y})`);
    }

    getCell(x, y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return this.grid[x][y];
        }
        return null;
    }

    async runSimulation(totalTicks) {
        console.log(` Iniciando ${totalTicks} ticks...`);
        
        for (let tick = 0; tick < totalTicks; tick++) {
            this.currentTick = tick;
            
            try {
                this.update(tick);
            } catch (error) {
                console.error(` Error en tick ${tick}:`, error);
                break;
            }
            
            if (tick % 10 === 0) {
                const totalAnts = this.colonies.reduce((sum, col) => sum + col.ants.length, 0);
                console.log(` Tick ${tick}: ${totalAnts} hormigas activas`);
            }
            
            await new Promise(resolve => setTimeout(resolve, this.tickDuration));
        }
    }

    update(tick) {
        
        for (let x = 0; x < this.width; x++) {
            for (let y = 0; y < this.height; y++) {
                const cell = this.grid[x][y];
                if (cell.pheromone) {
                    cell.pheromone.decreaseLevel();
                    if (cell.pheromone.level <= 0) {
                        cell.pheromone = null;
                    }
                }
            }
        }

        
        for (const colony of this.colonies) {
            colony.update(tick, this);
        }

        
        for (const antEater of this.antEaters) {
            antEater.update(tick, this);
        }
    }

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
                neighbors.push({
                    cell: cell,
                    direction: dir
                });
            }
        }
        return neighbors;
    }
}

module.exports = AntSimulator;