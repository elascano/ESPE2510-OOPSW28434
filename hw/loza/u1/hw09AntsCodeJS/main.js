import { Area } from './area.js'; // Ver abajo
import { Colony } from './colony.js'; // Ver abajo
import { Ant, AntEater } from './agents.js';
import { PHEROMONE_MAX_STRENGTH } from './constants.js';

class Simulation {
    constructor(width=4, height=4, ticks=20, tickDurationMs=500) {
        this.currentTick = 0;
        this.ticks = ticks;
        this.tickDuration = tickDurationMs;
        this.area = new Area(width, height);
        this.nestLocation = [0, 0];
        this.colony = new Colony(this.area, this.nestLocation);
        this.anteaters = [];

        this.FOOD_PILES = { "0,3": 10, "1,1": 10, "3,0": 10 };
        this.PHEROMONE_LOCATIONS = [[0,2],[3,3],[2,0]];
    }

    initialize() {
        this.colony.nest.foodStock = 20;

        for (const [key, amount] of Object.entries(this.FOOD_PILES)) {
            const pos = key.split(',').map(Number);
            this.area.placeFoodPile(pos, amount);
        }

        this.PHEROMONE_LOCATIONS.forEach(pos => {
            const cell = this.area.getCell(pos);
            if (cell) cell.dropPheromone(PHEROMONE_MAX_STRENGTH);
        });

        for (let i = 1; i <= 3; i++) this.colony.spawnAnt(`Hormiga #${i}`, this.nestLocation);

        const aePos = [Math.floor(Math.random() * this.area.width), Math.floor(Math.random() * this.area.height)];
        this.anteaters.push(new AntEater(aePos, this.area));
    }

    async run() {
        console.log("Iniciando simulación de hormigas y osos hormigueros (4x4)...\n");
        this.initialize();
        this.area.display();

        for (let tick = 1; tick <= this.ticks; tick++) {
            console.log(`\n--- TICK ${tick} / ${this.ticks} ---`);

            this.area.update();
            this.colony.update(tick);
            this.anteaters.forEach(ae => ae.update());

            if (this.colony.ants.length === 0) {
                console.log("\nSimulación terminada: No quedan hormigas!");
                break;
            }
            await new Promise(resolve => setTimeout(resolve, this.tickDuration));
        }

        console.log("\nSimulación finalizada.");
    }
}

(async () => {
    const sim = new Simulation(4, 4, 15, 200);
    await sim.run();
})();
