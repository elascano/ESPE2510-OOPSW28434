import { Area } from './Area_model.js';
import { Colony } from './Colony_model.js';
import { Ant } from './Ant_model.js';
import { AntEater } from './AntEater_model.js';
import { FoodPile } from './FoodPile_model.js';

const simulationWidth = 50;
const simulationHeight = 50;
const area = new Area(simulationWidth, simulationHeight);
console.log(`Simulation area created: ${area.width}x${area.height} cells.`);

const nestPosition = { x: 10, y: 10 };
const colony1 = new Colony(1, nestPosition);
area.addColony(colony1);

console.log(`Colony 1 nest created at: (${colony1.getNestPosition().x}, ${colony1.getNestPosition().y}).`);
for (let i = 0; i < 3; i++) {
    const foodToDeposit = colony1.depositFood({ getAmount: () => 5 });
    if (foodToDeposit) {
        console.log(`Ant ${colony1.ants.length} created in the nest.`);
    }
}

area.placeAnts(colony1);

const antEaterPosition = { x: 5, y: 5 };
const antEater1 = new AntEater(antEaterPosition);
area.addAntEater(antEater1);
console.log(`Ant eater 1 created at: (${antEater1.getPosition().x}, ${antEater1.getPosition().y}).`);

const foodPosition = { x: 15, y: 15 };
const foodCell = area.getCell(foodPosition.x, foodPosition.y);
if (foodCell) {
    foodCell.placeFoodPile(100);
    console.log(`Food pile of 100mg placed at: (${foodPosition.x}, ${foodPosition.y}).`);
}

let currentTick = 1;
const MAX_TICKS = 20;
const ANT_WEIGHT_DECREASE_TICKS = 5;

while (currentTick <= MAX_TICKS) {
    let events = [];
    events.push(`\n=== TICK ${currentTick} ===`);

    area.grid.flat().forEach(cell => cell.tick());

    colony1.ants.forEach((ant, index) => {
        const antID = `Ant ${index + 1}`;
        
        if (currentTick % ANT_WEIGHT_DECREASE_TICKS === 0) {
            ant.decreaseWeight();
            events.push(`${antID}: lost 1mg. Current weight: ${ant.weightMg}mg.`);
        }

        if (ant.position.x === nestPosition.x && ant.position.y === nestPosition.y) {
            if (ant.weightMg < 5 && colony1.nest.getAmount() > 0) {
                const foodFromNest = colony1.nest.requestFood(1);
                ant.eat(foodFromNest);
                events.push(`${antID}: ate 1mg in the nest. New weight: ${ant.weightMg}mg.`);
            }
        }

        const randomMove = {
            x: Math.max(0, Math.min(simulationWidth - 1, ant.position.x + Math.floor(Math.random() * 3) - 1)),
            y: Math.max(0, Math.min(simulationHeight - 1, ant.position.y + Math.floor(Math.random() * 3) - 1))
        };
        ant.move(randomMove);
        events.push(`${antID}: moved to (${ant.position.x}, ${ant.position.y}).`);

        if (index === 0 && currentTick === 8) {
            const foodToDeposit = { getAmount: () => 5 };
            const creationMessage = colony1.depositFood(foodToDeposit);

            if (creationMessage) {
                events.push(`${antID}: deposited 5mg of food.`);
                events.push(`Nest event: new ant created. Total ants: ${colony1.ants.length}.`);
            } else {
                events.push(`${antID}: deposited 5mg of food. Nest now has ${colony1.nest.getAmount()}mg.`);
            }
        }
    });

    area.antEaters.forEach(ae => {
        let aeMessage = '';

        if (ae.isWandering()) {
            aeMessage = 'Ant eater is wandering.';
            if (currentTick === 3 || currentTick % 5 === 0) {
                ae.antsConsumedSinceSleep = 49;
                aeMessage = ae.startEating();
            }
        } else if (ae.isEating) {
            aeMessage = ae.digest();
        } else if (ae.isSleeping) {
            aeMessage = ae.sleep();
        }

        events.push(aeMessage);
    });
    events.forEach(msg => console.log(msg));

    currentTick++;
}

