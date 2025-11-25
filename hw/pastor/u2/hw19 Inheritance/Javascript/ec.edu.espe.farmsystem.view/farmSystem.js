const Location = require('../ec.edu.espe.farmsystem.model/location');
const Cage = require('../ec.edu.espe.farmsystem.model/cage');
const Chicken = require('../ec.edu.espe.farmsystem.model/chicken');
const Cow = require('../ec.edu.espe.farmsystem.model/cow');
const Pig = require('../ec.edu.espe.farmsystem.model/pig');
const Sheep = require('../ec.edu.espe.farmsystem.model/sheep');

function runSystem() {
    // 1. Hardcoded Data
    const xCoordinate = 10;
    const yCoordinate = 20;
    
    const location1 = new Location(xCoordinate, yCoordinate);
    const location2 = new Location(30, 40);
    
    const cageCoop = new Cage(1, "Chicken Coop", 1, location1);
    const cageStable = new Cage(2, "Cow Stable", 2, location2);
    const cagePen = new Cage(3, "Pig Pen", 3, location1);
    
    const farmAnimals = [];
    
    const chicken = new Chicken(true, 5, 1, "Leghorn", new Date('2025-02-01'), "Female", true, 3.5, cageCoop, location1);
    farmAnimals.push(chicken);

    const cow = new Cow(true, 15.5, 2, "Holstein", new Date('2024-05-10'), "Female", true, 600.0, cageStable, location2);
    farmAnimals.push(cow);

    const pig = new Pig(false, 3, "Duroc", new Date('2024-08-20'), "Male", true, 120.5, cagePen, location1);
    farmAnimals.push(pig);

    const sheep = new Sheep(new Date('2025-01-15'), 4, "Merino", new Date('2023-11-05'), "Female", true, 70.0, cageStable, location2);
    farmAnimals.push(sheep);

    console.log("--- My Farm Animals ---");
    
    farmAnimals.forEach(animal => {
        console.log(animal.toString());
        
        if (animal instanceof Cow) {
            console.log(`  -> This is a Cow, Milk production: ${animal.milk()}`);
        }
    });

    console.log(`\nTotal animals in farm: ${farmAnimals.length}`);
}

module.exports = { runSystem };