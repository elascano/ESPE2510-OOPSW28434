const Chicken = require("./Chicken");
const ChickenCoop = require("./ChickenCoop");

console.log("this is my Chicken Farm Simulator by Emily Calle");

const coop1 = new ChickenCoop(1, "Main Coop");
const coop2 = new ChickenCoop(2, "Secondary Coop");

const chickens = [
    new Chicken(1, "Lucy", "White and Brown", 2, false),
    new Chicken(2, "Matilda", "Black", 1, true),
    new Chicken(3, "Henrietta", "Red", 3, false),
    new Chicken(4, "Ginger", "Golden", 2, true),
    new Chicken(5, "BokBok", "Spotted", 1, false),
    new Chicken(6, "Clucky", "Gray", 4, false),
    new Chicken(7, "Feathers", "White", 2, true),
    new Chicken(8, "Sunny", "Yellow", 1, false),
    new Chicken(9, "Ruby", "Dark Red", 3, false),
    new Chicken(10, "Snowball", "Pure White", 2, true)
];

for (let i = 0; i < 6; i++) {
    coop1.addChicken(chickens[i]);
}

for (let i = 6; i < 10; i++) {
    coop2.addChicken(chickens[i]);
}

console.log("\n=== CHICKEN FARM STATUS ===");
console.log(coop1.toString());
console.log(coop2.toString());

coop1.displayAllChickens();
coop2.displayAllChickens();

console.log("\n=== FARM STATISTICS ===");
console.log(`Total chickens in farm: ${chickens.length}`);
console.log(`Chickens in ${coop1.get_name()}: ${coop1.getChickenCount()}`);
console.log(`Chickens in ${coop2.get_name()}: ${coop2.getChickenCount()}`);

console.log("\n=== REMOVING A CHICKEN ===");
console.log("Before removal:");
console.log(`Chickens in ${coop1.get_name()}: ${coop1.getChickenCount()}`);
coop1.removeChicken(3); 
console.log("After removing chicken with ID 3:");
console.log(`Chickens in ${coop1.get_name()}: ${coop1.getChickenCount()}`);

console.log("\n=== FINAL FARM STATUS ===");
console.log(coop1.toString());
console.log(coop2.toString());