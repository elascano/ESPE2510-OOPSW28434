// Importamos cada archivo individualmente
const Location = require('../model/Location');
const Cage = require('../model/Cage');
const Chicken = require('../model/Chicken');
const Cow = require('../model/Cow');
const Pig = require('../model/Pig');
const Sheep = require('../model/Sheep');

function main() {
    console.log("=== FARM SYSTEM JS by Kevin Chalan ===");

    const bornOn = new Date(2025, 2, 1); 
    
    const loc = new Location(10, 20);
    const cage = new Cage(1, "Main Barn", 1, loc);

    const chicken = new Chicken(false, 0, 1, "Orpington", bornOn, "female", true, 2.5, cage);
    console.log("Chicken ->", chicken.toString());

    const cow = new Cow(true, 12.5, 2, "Holstein", bornOn, "female", true, 500.0, cage);
    console.log("Cow ->", cow.toString());

    const pig = new Pig(3, "Landrace", bornOn, "male", true, 150.0, cage);
    console.log("Pig ->", pig.toString());

    const sheep = new Sheep(new Date(2024, 4, 15), 4, "Merino", bornOn, "female", true, 70.0, cage);
    console.log("Sheep ->", sheep.toString());
}

main();