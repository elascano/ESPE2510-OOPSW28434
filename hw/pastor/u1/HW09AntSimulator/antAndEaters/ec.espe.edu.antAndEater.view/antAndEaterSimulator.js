import { Simulation } from '../ec.espe.edu.antAndEater.model/Simulation.js';
import { Colony } from '../ec.espe.edu.antAndEater.model/Colony.js';
import { FoodPile } from '../ec.espe.edu.antAndEater.model/FoodPile.js';
import { Food } from '../ec.espe.edu.antAndEater.model/Food.js';    
import { AntEater } from '../ec.espe.edu.antAndEater.model/AntEater.js'; 

const GRID_WIDTH = 30;
const GRID_HEIGHT = 30;
const TICK_DURATION = 100;

const sim = new Simulation(GRID_WIDTH, GRID_HEIGHT, TICK_DURATION);
const area = sim.area; 

const nestCell = area.getCell(Math.floor(GRID_WIDTH / 2), Math.floor(GRID_HEIGHT / 2));
const colony = new Colony(area, nestCell); 

colony.nest.depositFood(new Food(1000));
for (let i = 0; i < 10; i++) {
    colony.nest.tryCreateAnt();
}

const eater1 = new AntEater(area.getCell(5, 5));
const eater2 = new AntEater(area.getCell(25, 25));
area.antEaters.push(eater1, eater2); 
area.getCell(5, 5).addAgent(eater1);
area.getCell(25, 25).addAgent(eater2);

const foodCell = area.getCell(2, 2);
foodCell.foodPile = new FoodPile(500);

sim.startSimulation();