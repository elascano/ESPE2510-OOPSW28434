import readlineSync from 'readline-sync';
import Chicken from './Chicken.js';

console.log("Welcome to the Chicken Farm Simulator \n");

const chickens = [];

while (true) {
    console.log("\n--- Enter a new chicken ---");

    const id = parseInt(readlineSync.question("Enter the chicken ID: "));
    const name = readlineSync.question("Enter the chicken name: ");
    const color = readlineSync.question("Enter the chicken color: ");
    const age = parseInt(readlineSync.question("Enter the chicken age: "));
    const isMoltingInput = readlineSync.question("Is the chicken molting? (yes/no): ").toLowerCase();
    const isMolting = isMoltingInput === "yes";

    const chicken = new Chicken(id, name, color, age, isMolting);
    chickens.push(chicken);

    console.log("\n Chicken successfully added:");
    console.log(chicken.toString());

    const continueInput = readlineSync.question("\nDo you want to add another chicken? (yes/no): ").toLowerCase();
    if (continueInput !== "yes") break;
}

console.log("\n===== List of chickens on the farm =====");
chickens.forEach(c => {
    console.log(c.toString());
    console.log("-".repeat(40));
});

console.log("\n Chickens start their activities...\n");
chickens.forEach(c => c.doStuff());
