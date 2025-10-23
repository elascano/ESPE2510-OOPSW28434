const GroundCell = require('./GroundCell');

class AntSimulator {
    constructor(width, height, tickInterval = 100) {
        this.width = width;
        this.height = height;
        this.tickInterval = tickInterval;

        this.grid = this.initializeGrid(width, height);
        this.colonies = [];
        this.antEaters = [];
        this.currentTick = 0;

        console.log(Grid inicializado con dimensiones: ${width}x${height});
    }

    initializeGrid(width, height) {
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
        console.log(Colonia "${colony.name}" añadida en (${colony.nest.x}, ${colony.nest.y}));
    }

    addAntEater(antEater) {
        this.antEaters.push(antEater);
        const cell = this.getCell(antEater.x, antEater.y);
        if (cell) {
            cell.addAntEater(antEater);
        }
        console.log(Oso hormiguero colocado en (${antEater.x}, ${antEater.y}));
    }

    getCell(x, y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return this.grid[x][y];
        }
        return null;
    }

    async runSimulation(totalTicks) {
        console.log(Ejecutando simulación por ${totalTicks} ticks...);

        for (let tick = 0; tick < totalTicks; tick++) {
            this.currentTick = tick;

            try {
                this.update(tick);
            } catch (err) {
                console.error(Error en tick ${tick}:, err);
                break;
            }

            if (tick % 10 === 0) {
                const activeAnts = this.colonies.reduce((sum, col) => sum + col.ants.length, 0);
                console.log(Tick ${tick}: ${activeAnts} hormigas activas);
            }

            await new Promise(resolve => setTimeout(resolve, this.tickInterval));
        }
    }

    update(tick) {
        // Reducir feromonas en cada celda
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

        // Actualizar colonias
        for (const colony of this.colonies) {
            colony.update(tick, this);
        }

        // Actualizar osos hormigueros
        for (const antEater of this.antEaters) {
            antEater.update(tick, this);
        }
    }

    getNeighborCells(x, y) {
        const neighbors = [];
        const directions = [
            { dx: 0, dy: -1 }, { dx: 1, dy: 0 }, { dx: 0, dy: 1 }, { dx: -1, dy: 0 },
            { dx: 1, dy: -1 }, { dx: 1, dy: 1 }, { dx: -1, dy: 1 }, { dx: -1, dy: -1 }
        ];

        for (const dir of directions) {
            const nx = x + dir.dx;
            const ny = y + dir.dy;
            const neighborCell = this.getCell(nx, ny);
            if (neighborCell) {
                neighbors.push({ cell: neighborCell, direction: dir });
            }
        }

        return neighbors;
    }
}

module.exports = AntSimulator;
