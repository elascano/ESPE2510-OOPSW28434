// Owner/Author: Josue Rojas
import { Area } from './Area_model.js';
import { Colony } from './Colony_model.js';
import { AntEater } from './AntEater_model.js';

const width = 50;
const height = 50;
const area = new Area(width, height);

console.log(`Simulation area created: ${width}x${height}`);

const colony = new Colony(1, { x: 10, y: 10 });
area.addColony(colony);

const antEater = new AntEater({ x: 5, y: 5 });
area.addAntEater(antEater);

let tick = 1;
const MAX_TICKS = 20;

while (tick <= MAX_TICKS) {
  console.log(`\nTick ${tick} executed.`);
  area.grid.flat().forEach(c => c.tick());

  console.log("----- State of the area -----");
  console.log(`Active colonies: ${area.colonies.length}`);
  console.log(`Anteaters: ${area.antEaters.length}`);
  console.log(`Total ants: ${area.colonies.reduce((s, c) => s + c.ants.length, 0)}`);
  console.log(`Food in nests: ${area.colonies.reduce((s, c) => s + c.nest.getAmount(), 0)}`);
  console.log(`Pheromone level: ${area.totalPheromoneLevel()}`);
  console.log("-----------------------------");
  tick++;
}
  