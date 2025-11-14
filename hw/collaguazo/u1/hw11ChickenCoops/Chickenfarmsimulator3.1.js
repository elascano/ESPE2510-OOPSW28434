const prompt = require('prompt-sync')({ sigint: true });
const Chicken = require('../Model/Chicken');
const ChickenCoop = require('../Model/ChickenCoop');

console.log("Welcome to the Poultry Farm Simulator");

function readInteger(promptText, min, max) {
    let input;
    do {
        input = prompt(promptText);
        const number = parseInt(input);
        if (!/^\d+$/.test(input) || number < min || number > max) {
            console.log(Enter a valid whole number between ${min} and ${max}.);
            input = null;
        }
    } while (input === null);
    return input;
}

const flock = [
    new Chicken('Mariluna', 'Brown', 2, false),
    new Chicken('Clucky', 'White', 1, true),
    new Chicken('Josefa', 'Black', 3, false),
    new Chicken('Kiki', 'Yellow', 1, false),
    new Chicken('Lucilda', 'Brown and White', 4, true),
    new Chicken('Ruperta', 'Speckled', 2, false),
    new Chicken('Lili', 'White', 3, false),
    new Chicken('Coco', 'Black and White', 1, true),
    new Chicken('Pepa', 'Brown', 2, false),
    new Chicken('Sunny', 'Brown and Yellow', 4, false),
];

const coopA = new ChickenCoop('Feather Haven');
const coopB = new ChickenCoop('Winged House');
const allCoops = [coopA, coopB];

for (let i = 0; i < 6; i++) {
    coopA.addChicken(flock[i]);
}

for (let i = 6; i < 10; i++) {
    coopB.addChicken(flock[i]);
}

console.log("\n--- Farm Initialization Complete ---");
console.log(coopA.toString());
console.log(coopB.toString());
console.log('------------------------------------');

do {
    const coopChoice = readInteger("Select a Coop to view (1 or 2): ", 1, 2);
    const chosenCoop = allCoops[coopChoice - 1];

    console.log(\n--- Chickens in ${chosenCoop.coopName} (ID: ${chosenCoop.identifier}) ---);

    if (chosenCoop.getChickens().length === 0) {
        console.log("No chickens currently in this coop.");
    } else {
        console.log(Total Chickens: ${chosenCoop.getChickens().length}\n);
        chosenCoop.getChickens().forEach((bird) => {
            console.log(bird.toString());
        });
    }

    var again = prompt("Would you like to view another coop? (yes/no): ").toLowerCase().trim();
} while (again === "yes");

console.log("\nLeaving the Poultry Farm Simulator. See you next time!");
