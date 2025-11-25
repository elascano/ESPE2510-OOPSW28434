const { Location } = require('./Location.js');
const { Cage } = require('./Cage.js');
const { Cow } = require('./Cow.js');
const { Chicken } = require('./Chicken.js');
const { Pig } = require('./Pig.js');
const { Sheep } = require('./Sheep.js');

class FarmSystem {
    static main() {
        const cows = [];
        const chickens = [];
        const pigs = [];
        const sheeps = [];
        
        const location1 = new Location(10, 20);
        const location2 = new Location(15, 25);
        const location3 = new Location(5, 15);
        const location4 = new Location(30, 40);
        
        const cowCage = new Cage(1, "Cow barn", 2, location1);
        const chickenCage = new Cage(2, "Chicken coop", 1, location2);
        const sheepCage = new Cage(3, "Sheep pen", 3, location3);
        const pigCage = new Cage(4, "Pig pen", 3, location4);

        const today = new Date();
        const bornDate = new Date(2024, 5, 15);
        const shearingDate = new Date(2024, 0, 15);

        const cow1 = new Cow(true, 0.0, 1, "Holstein", bornDate, "female", true, 450.5, cowCage);
        const cow2 = new Cow(false, 0.0, 2, "Jersey", bornDate, "female", false, 380.2, cowCage);
        const cow3 = new Cow(true, 5.5, 3, "Angus", bornDate, "female", true, 520.8, cowCage);
        
        cows.push(cow1, cow2, cow3);
        
        cow1.milk(8.2);
        cow3.milk(3.5);

        const chicken1 = new Chicken(false, 0, 4, "Rhode Island Red", 
                                   bornDate, "female", true, 2.5, chickenCage);
        const chicken2 = new Chicken(true, 5, 5, "Leghorn", 
                                   bornDate, "female", true, 2.1, chickenCage);
        const chicken3 = new Chicken(false, 12, 6, "Plymouth Rock", 
                                   bornDate, "male", false, 3.2, chickenCage);
        
        chickens.push(chicken1, chicken2, chicken3);
        
        chicken1.layAnEgg();
        chicken2.layAnEgg();
        chicken2.layAnEgg();
        chicken3.layAnEgg();

        const pig1 = new Pig(7, "Duroc", bornDate, "male", true, 120.5, pigCage);
        const pig2 = new Pig(8, "Yorkshire", bornDate, "female", true, 95.3, pigCage);
        const pig3 = new Pig(9, "Hampshire", bornDate, "male", false, 150.8, pigCage);
        
        pigs.push(pig1, pig2, pig3);

        const sheep1 = new Sheep(null, 10, "Merino", bornDate, "female", true, 65.2, sheepCage);
        const sheep2 = new Sheep(today, 11, "Dorset", bornDate, "male", false, 72.5, sheepCage);
        const sheep3 = new Sheep(shearingDate, 12, "Suffolk", bornDate, "female", true, 58.7, sheepCage);
        
        sheeps.push(sheep1, sheep2, sheep3);
        
        sheep1.shear();
        sheep3.cutWool();
        
        console.log(`--- COWS ON THE FARM ---`);
        cows.forEach((cow, index) => {
            console.log(`Cow ${index + 1}:`);
            console.log(`   - ID: ${cow.id}`);
            console.log(`   - Breed: ${cow.breed}`);
            console.log(`   - Producing milk: ${cow.isProducingMilk}`);
            console.log(`   - Liters today: ${cow.litersPerDay}L`);
            console.log(`   - Weight: ${cow.weight}kg`);
            console.log(`   - Cage: ${cow.cage.description}`);
        });
        
        console.log(`\n--- CHICKENS ON THE FARM ---`);
        chickens.forEach((chicken, index) => {
            console.log(`Chicken ${index + 1}:`);
            console.log(`   - ID: ${chicken.id}`);
            console.log(`   - Breed: ${chicken.breed}`);
            console.log(`   - Molting: ${chicken.isMolting}`);
            console.log(`   - Eggs laid: ${chicken.laidEggs}`);
            console.log(`   - Weight: ${chicken.weight}kg`);
            console.log(`   - Gender: ${chicken.gender}`);
            console.log(`   - Cage: ${chicken.cage.description}`);
        });
        
        console.log(`\n--- PIGS ON THE FARM ---`);
        pigs.forEach((pig, index) => {
            console.log(`Pig ${index + 1}:`);
            console.log(`   - ID: ${pig.id}`);
            console.log(`   - Breed: ${pig.breed}`);
            console.log(`   - Weight: ${pig.weight}kg`);
            console.log(`   - Gender: ${pig.gender}`);
            console.log(`   - Able to reproduce: ${pig.isAbleToReproduce}`);
        });
        
        console.log(`\n--- SHEEP ON THE FARM ---`);
        sheeps.forEach((sheep, index) => {
            console.log(`Sheep ${index + 1}:`);
            console.log(`   - ID: ${sheep.id}`);
            console.log(`   - Breed: ${sheep.breed}`);
            console.log(`   - Last shearing: ${sheep.lastShearing}`);
            console.log(`   - Weight: ${sheep.weight}kg`);
            console.log(`   - Gender: ${sheep.gender}`);
        });
        
    }
}

FarmSystem.main();