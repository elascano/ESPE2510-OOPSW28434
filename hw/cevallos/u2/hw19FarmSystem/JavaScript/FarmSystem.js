const Cow = require('./Cow');
const Chicken = require('./Chicken');
const Pig = require('./Pig');
const Sheep = require('./Sheep');
const Cage = require('./Cage');
const Location = require('./Location');

function main() {
    const cows = [];
    const chickens = [];
    const pigs = [];
    const sheeps = [];

    // Create locations
    const location1 = new Location(10, 20);
    const location2 = new Location(15, 25);
    const location3 = new Location(5, 15);
    const location4 = new Location(30, 40);

    // Create cages
    const cowCage = new Cage(1, "Cow barn", 2, location1);
    const chickenCage = new Cage(2, "Chicken coop", 1, location2);
    const sheepCage = new Cage(3, "Sheep pen", 3, location3);
    const pigCage = new Cage(4, "Pig pen", 3, location4);

    // Create animals
    const today = new Date();
    const bornDate1 = new Date(2023, 3, 15); // Note: months are 0-indexed in JS
    const bornDate2 = new Date(2022, 7, 10);
    const bornDate3 = new Date(2021, 1, 5);

    const cow1 = new Cow(true, 0.5, 1, "Holstein", bornDate1, "female", true, 0, cowCage);
    const cow2 = new Cow(true, 0.6, 2, "Holstein", bornDate2, "female", true, 0, cowCage);
    const cow3 = new Cow(false, 0.4, 3, "Jersey", bornDate3, "male", false, 0, cowCage);

    cows.push(cow1, cow2, cow3);

    const chicken1 = new Chicken(false, 0, 1, "Rhode Island Red", bornDate1, "female", true, 2.5, chickenCage);
    const chicken2 = new Chicken(true, 5, 2, "Leghorn", bornDate2, "female", true, 2.1, chickenCage);
    const chicken3 = new Chicken(false, 12, 3, "Plymouth Rock", bornDate3, "male", false, 3.2, chickenCage);

    chickens.push(chicken1, chicken2, chicken3);

    chicken1.layAnEgg();
    chicken2.layAnEgg();
    chicken2.layAnEgg();

    const pig1 = new Pig(1, "Large White", bornDate1, "male", true, 120.5, pigCage);
    const pig2 = new Pig(2, "Duroc", bornDate2, "female", true, 105.3, pigCage);
    const pig3 = new Pig(3, "Landrace", bornDate3, "male", true, 130.0, pigCage);

    pigs.push(pig1, pig2, pig3);

    const sheep1 = new Sheep(null, 1, "Merino", bornDate1, "female", true, 55.4, sheepCage);
    const sheep2 = new Sheep(new Date(), 2, "Suffolk", bornDate2, "male", true, 62.8, sheepCage);
    const sheep3 = new Sheep(new Date(2024, 0, 15), 3, "Dorper", bornDate3, "female", true, 48.2, sheepCage);

    sheeps.push(sheep1, sheep2, sheep3);

    sheep1.shear();
    sheep3.cutWool();

    // Print farm status
    console.log(`--- COWS ON THE FARM (${cows.length}) ---`);
    cows.forEach((cow, index) => {
        console.log(`Cow ${index + 1}: ${cow}`);
        console.log(`   - Producing milk: ${cow.isProducingMilk}`);
        console.log(`   - Liters today: ${cow.litersADay}L`);
        console.log(`   - Cage: ${cow.cage.description}`);
    });

    console.log(`\n--- CHICKENS ON THE FARM (${chickens.length}) ---`);
    chickens.forEach((chicken, index) => {
        console.log(`Chicken ${index + 1}:`);
        console.log(`   - Breed: ${chicken.breed}`);
        console.log(`   - Molting: ${chicken.isMolting}`);
        console.log(`   - Eggs laid: ${chicken.laidEggs}`);
        console.log(`   - Weight: ${chicken.weight}kg`);
        console.log(`   - Cage: ${chicken.cage.description}`);
    });

    console.log(`\n--- PIGS ON THE FARM (${pigs.length}) ---`);
    pigs.forEach((pig, index) => {
        console.log(`Pig ${index + 1}: ${pig}`);
        console.log(`   - Weight: ${pig.weight}kg`);
        console.log(`   - Cage: ${pig.cage.description}`);
    });

    console.log(`\n--- SHEEP ON THE FARM (${sheeps.length}) ---`);
    sheeps.forEach((sheep, index) => {
        console.log(`Sheep ${index + 1}:`);
        console.log(`   - Weight: ${sheep.weight}kg`);
        console.log(`   - Last shearing: ${sheep.lastSheering}`);
        console.log(`   - Cage: ${sheep.cage.description}`);
    });
}

// Run the farm system
main();