import readlineSync from "readline-sync";
import { Chicken } from "./Chicken.js";

console.log(" Welcome to the Chicken Farm Simulator!\n");

let chickens = [];
let again;

do {
  const id = parseInt(readlineSync.question("Enter chicken id (int): "));
  const name = readlineSync.question("Enter chicken name: ");
  const color = readlineSync.question("Enter chicken color: ");
  const age = parseInt(readlineSync.question("Enter chicken age (int): "));
  const isMoltingInput = readlineSync.question("Is molting? (yes/no): ").toLowerCase();
  const isMolting = isMoltingInput === "yes";

  const chicken = new Chicken(id, name, color, age, isMolting);
  chickens.push(chicken);

  console.log("\n Chicken added successfully!");
  console.log(chicken.toString());

  console.log("\n Let's see what this chicken does...");
  chicken.doStuff();

  again = readlineSync.question("\nDo you want to add another chicken? (yes/no): ").toLowerCase();
} while (again === "yes");

console.log("\n Summary of chickens in the farm:");
chickens.forEach((c) => console.log(c.toString()));

console.log("\nSimulation complete! ");
