const readline = require("readline");
const { Chicken } = require("../model/Chicken.js");
const { ChickenCoop } = require("../model/ChickenCoop.js");
const { loadData, saveData } = require("../model/DataService.js");

const rl = readline.createInterface({
 input: process.stdin,
 output: process.stdout,
});

function ask(question) {
 return new Promise(resolve => rl.question(question, resolve));
}

let coops = [];
let chickens = [];
let farmerName = "Farmer";

function initializeData() {
 const { coops: loadedCoops, chickens: loadedChickens } = loadData();

 if (loadedCoops.length === 0 && loadedChickens.length === 0) {
  console.log("No data found. Initializing with default farm data...");

  const initialChickens = [
   new Chicken(1, "Lucy", "White", 2, false),
   new Chicken(2, "Maruja", "Brown", 1, true),
   new Chicken(3, "Clara", "Golden", 3, false),
   new Chicken(4, "Tina", "Black", 1, false),
   new Chicken(5, "Nina", "Gray", 2, true),
   new Chicken(6, "Sofi", "White", 4, false),
   new Chicken(7, "Luna", "Golden", 3, true),
   new Chicken(8, "Mimi", "Black", 1, false),
   new Chicken(9, "Rita", "Brown", 2, false),
   new Chicken(10, "Coco", "White", 1, true),
  ];

  const initialCoops = [
   new ChickenCoop(1, "Coop #1"),
   new ChickenCoop(2, "Coop #2"),
  ];

  for (let i = 0; i < 5; i++) {
   initialCoops[0].addChicken(initialChickens[i]);
  }
  for (let i = 5; i < initialChickens.length; i++) {
   initialCoops[1].addChicken(initialChickens[i]);
  }

  chickens = initialChickens;
  coops = initialCoops;

  saveData(coops, chickens);
 } else {
  console.log("Loaded existing data from files.");
  chickens = loadedChickens;
  coops = loadedCoops;
 }
}

function printMenu() {
 console.log(`\n--- ${farmerName}'s Chicken Farm Simulator ---`);
 console.log("1. List all coops and chickens");
 console.log("2. Add a new chicken");
 console.log("3. Add a new coop");
 console.log("4. Run farm simulation");
 console.log("5. Exit");
}

async function listCoopsAndChickens() {
 console.log("\n--- Farm Status ---");
 if (coops.length === 0) {
  console.log("There are no coops on this farm.");
  return;
 }

 for (const coop of coops) {
  console.log(`\n ${coop.name} (ID: ${coop.id}) contains:`);
  const chickensInCoop = coop.getChickens();
  if (chickensInCoop.length === 0) {
   console.log(" - (Empty)");
  } else {
   for (const chicken of chickensInCoop) {
    console.log(` - ${chicken.name} (ID: ${chicken.id})`);
   }
  }
  console.log("------------------------------------");
 }
}

async function addNewChicken() {
 console.log("\n--- Add New Chicken ---");
 if (coops.length === 0) {
  console.log("You must add a coop first!");
  return;
 }

 const id = parseInt(await ask("Enter chicken ID: "));
 if (chickens.some(ch => ch.id === id)) {
  console.log("Error: A chicken with this ID already exists.");
  return;
 }
 const name = await ask("Enter chicken name: ");
 const color = await ask("Enter chicken color: ");
 const age = parseInt(await ask("Enter chicken age: "));
 const isMoltingInput = await ask("Is the chicken molting? (true/false): ");
 const isMolting = isMoltingInput.toLowerCase() === "true";

 const newChicken = new Chicken(id, name, color, age, isMolting);

 console.log("Available coops:");
 coops.forEach(c => console.log(` - ID ${c.id}: ${c.name}`));
 const coopId = parseInt(
  await ask("Enter the ID of the coop to add this chicken to: ")
 );

 const targetCoop = coops.find(c => c.id === coopId);
 if (!targetCoop) {
  console.log("Error: Coop not found.");
  return;
 }

 targetCoop.addChicken(newChicken);
 chickens.push(newChicken);
 saveData(coops, chickens);
 console.log(`Successfully added ${name} to ${targetCoop.name}!`);
}

async function addNewCoop() {
 console.log("\n--- Add New Coop ---");
 const id = parseInt(await ask("Enter new coop ID: "));
 if (coops.some(c => c.id === id)) {
  console.log("Error: A coop with this ID already exists.");
  return;
 }
 const name = await ask("Enter new coop name: ");

 const newCoop = new ChickenCoop(id, name);
 coops.push(newCoop);
 saveData(coops, chickens);
 console.log(`Successfully added coop: ${name}!`);
}

function runSimulation() {
 console.log("\n--- Running Farm Simulation ---");
 console.log("All chickens in the farm are now doing their routines:\n");

 if (chickens.length === 0) {
  console.log("There are no chickens to simulate.");
  return;
 }

 for (const chicken of chickens) {
  console.log(`The chicken is --> ${chicken.toString()}`);
  console.log(`His owner and friend is --> ${farmerName}`);
  chicken.doStuff();
  console.log("------------------------------\n");
 }
 console.log("Simulation finished successfully.");
}

async function main() {
 console.log("Welcome to the Chicken Farm Simulator");
 farmerName = await ask("Please enter the farmer's name: ");

 initializeData();

 let running = true;
 while (running) {
  printMenu();
  const choice = await ask("Enter your choice (1-5): ");

  switch (choice) {
   case "1":
    await listCoopsAndChickens();
    break;
   case "2":
    await addNewChicken();
    break;
   case "3":
    await addNewCoop();
    break;
   case "4":
    runSimulation();
    break;
   case "5":
    running = false;
    console.log("Saving data and exiting. Goodbye!");
    break;
   default:
    console.log("Invalid choice. Please enter a number between 1 and 5.");
  }
 }

 rl.close();
}

main();