import { Area } from './Area.js';
import { Nest } from './Nest.js';
import { Colony } from './Colony.js';
import { Food } from './Food.js';
import { FoodPile } from './FoodPile.js';
import { AntEater } from './AntEater.js';

class AntSimulator {
    constructor(width, height, tickDuration = 100) {
        this.area = new Area(width, height);
        this.tickDuration = tickDuration;
        this.colonies = [];
        this.antEaters = [];
        this.currentTick = 0;
    }

    getCell(x, y) {
        return this.area.getCell(x, y);
    }

    addColony(colony) {
        this.colonies.push(colony);
        this.area.addColony(colony);
    }

    addAntEater(antEater) {
        this.antEaters.push(antEater);
        this.area.addAntEater(antEater);
    }

    getNeighbors(x, y) {
        return this.area.getNeighbors(x, y);
    }

    async runSimulation(ticks = 200) {
        for (let i = 0; i < ticks; i++) {
            this.update();
            await new Promise((res) => setTimeout(res, this.tickDuration));
        }
    }

    update() {
        this.currentTick++;

        for (const col of this.colonies) col.tick(this);

        for (const col of this.colonies)
            for (const ant of col.ants)
                ant.tick(this);

        for (const eater of this.antEaters)
            eater.tick(this);

        for (let y = 0; y < this.area.height; y++) {
            for (let x = 0; x < this.area.width; x++) {
                const cell = this.area.getCell(x, y);
                if (cell.pheromone) {
                    cell.pheromone.decay();
                    if (!cell.pheromone.isActive()) cell.pheromone = null;
                }
            }
        }
    }
}

async function main() {
    try {
        console.log('Starting Ant & AntEater simulation.');

        const simulator = new AntSimulator(20, 20, 50);
        console.log('Simulator created.');

        const nest1Cell = simulator.getCell(5, 5);
        const nest2Cell = simulator.getCell(15, 15);
        const nest1 = new Nest(nest1Cell);
        const nest2 = new Nest(nest2Cell);
        const colony1 = new Colony(nest1);
        const colony2 = new Colony(nest2);
        simulator.addColony(colony1);
        simulator.addColony(colony2);
        console.log('Colonies created.');

        nest1.addFood(new Food(30));
        nest2.addFood(new Food(30));

        const eater1 = new AntEater(simulator.getCell(10, 10));
        const eater2 = new AntEater(simulator.getCell(8, 12));
        simulator.addAntEater(eater1);
        simulator.addAntEater(eater2);
        console.log('Ant Eaters created.');

        let foodCount = 0;
        for (let i = 0; i < 10; i++) {
            const x = Math.floor(Math.random() * 20);
            const y = Math.floor(Math.random() * 20);
            const cell = simulator.getCell(x, y);
            if (cell) {
                cell.addFoodPile(new FoodPile(10 + Math.floor(Math.random() * 20)));
                foodCount++;
            }
        }
        console.log(`${foodCount} random food piles added.`);

        const nestPositions = [[5, 5], [15, 15]];
        for (const [nestX, nestY] of nestPositions) {
            for (let i = -3; i <= 3; i++) {
                for (let j = -3; j <= 3; j++) {
                    if (i === 0 && j === 0) continue;
                    const x = nestX + i;
                    const y = nestY + j;
                    const cell = simulator.getCell(x, y);
                    if (cell && Math.random() > 0.3) {
                        cell.addFoodPile(new FoodPile(8 + Math.floor(Math.random() * 15)));
                        foodCount++;
                    }
                }
            }
        }
        console.log(`Total food piles: ${foodCount} (including near nests)`);

        console.log('Initial state:');
        console.log(` Colonies: ${simulator.colonies.length}`);
        console.log(` Ant Eaters: ${simulator.antEaters.length}`);
        console.log(` Total Ants: ${simulator.colonies.reduce((sum, col) => sum + col.ants.size, 0)}`);

        let foodNearNest1 = 0;
        let foodNearNest2 = 0;
        for (let i = -2; i <= 2; i++) {
            for (let j = -2; j <= 2; j++) {
                const cell1 = simulator.getCell(5 + i, 5 + j);
                const cell2 = simulator.getCell(15 + i, 15 + j);
                if (cell1?.foodPile?.amount > 0) foodNearNest1++;
                if (cell2?.foodPile?.amount > 0) foodNearNest2++;
            }
        }
        console.log(` Food near North Colony: ${foodNearNest1} cells`);
        console.log(` Food near South Colony: ${foodNearNest2} cells`);

        console.log('Running simulation...');
        await simulator.runSimulation(200);

        console.log('Simulation completed.');
        console.log('Final state:');
        const totalAnts = simulator.colonies.reduce((sum, col) => sum + col.ants.size, 0);
        console.log(` Surviving ants: ${totalAnts}`);
        console.log(` Food in North Nest: ${colony1.nest.foodStock} mg`);
        console.log(` Food in South Nest: ${colony2.nest.foodStock} mg`);
        console.log(` Ants in North Colony: ${colony1.ants.size}`);
        console.log(` Ants in South Colony: ${colony2.ants.size}`);

        console.log('Ant Eater statistics:');
        simulator.antEaters.forEach((eater, index) => {
            console.log(` Eater ${index + 1}: ate ${eater.consumedAnts} ants`);
        });

    } catch (error) {
        console.error(' Simulation error:', error);
        console.error(' Stack trace:', error.stack);
    }
}

main();