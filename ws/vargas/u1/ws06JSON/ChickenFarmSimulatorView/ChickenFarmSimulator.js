import { Chicken } from "../ChickenFarmSimulatorModel/Chicken.js";
import { ChickenCoop } from "../ChickenFarmSimulatorModel/ChickenCoops.js";
import { ChickenSave } from "../ChickenFarmSimulatorModel/SaveChickenInJson.js"; 
import * as readline from 'readline/promises';
import { stdin as input, stdout as output } from 'process';

console.log("This is my Chicken Farm Simulator");

const storage = new ChickenSave('chickensCoops.json'); 
const rl = readline.createInterface({ input, output });

let farmCoops = []; 

const initialChickensData = [
  { id: 1, "name": "Lucy", color: "White and Brown", age: 2, isMolting: false },
  { id: 0, "name": "Maruja", color: "White", age: 1, isMolting: true },
  { id: 3,"name": "Lola", color: "White", age: 2, isMolting: true },
  { id: 4, "name": "Pepa", color: "Black", age: 1, isMolting: false },
  { id: 5, "name": "Gusepa", color: "Brown and white", age: 4, isMolting: false },
  { id: 6, "name": "Pancracia", color: "Gray", age: 2, isMolting: true },
  { id: 7, "name": "Federica", color: "Brown", age: 1, isMolting: false },
  { id: 8, "name": "Pancha", color: "White", age: 3, isMolting: false },
  { id: 9, "name": "Zoe", color: "Black", age: 2, isMolting: false },
  { id: 10, "name": "Lina", color: "Brown and white", age: 1, isMolting: true },
];

function createCoopsFromData(coopData, chickenData) {
  ChickenCoop.allCoops = [];

  let coop1 = new ChickenCoop("Chicken Coop A");
  let coop2 = new ChickenCoop("Chicken Coop B");
  
  const allChickens = chickenData.map(c => new Chicken(c.id, c.name, c.color, c.age, c.isMolting));

  for (let i = 0; i < 7 && i < allChickens.length; i++) {
    coop1.addChicken(allChickens[i]); 
  }

  for (let i = 7; i < 10 && i < allChickens.length; i++) {
    coop2.addChicken(allChickens[i]);
  }
  
  return [coop1, coop2];
}

async function initializeFarm() {
  const loadedCoopData = await storage.load();

  let allChickensData = [];
  
  if (loadedCoopData.length === 0) {
    console.log("JSON file empty. Initializing with hardcoded data and saving...");
    
    allChickensData = initialChickensData;

    farmCoops = createCoopsFromData(loadedCoopData, initialChickensData);
    
    await storage.save(farmCoops);
    
  } else {
    loadedCoopData.forEach(coop => {
      allChickensData.push(...coop.chickens);
    });
    
    farmCoops = createCoopsFromData(loadedCoopData, allChickensData);
  }
}

async function mainMenu() {
  await initializeFarm();

  let exit = false;
  while (!exit) {
    console.log("\n---------------------------------------");
    console.log("  CHICKEN MANAGEMENT MENU");
    console.log("---------------------------------------");
    console.log("1. Add New Chicken");
    console.log("2. Display All Chickens");
    console.log("3. Exit");
    console.log("---------------------------------------");

    const choice = await rl.question('Select an option: ');
    
    switch (choice.trim()) {
      case '1':
        await addNewChicken();
        break;
      case '2':
        await listChickensFromJson();
        break;
      case '3':
        exit = true;
        break;
      default:
        console.log("Invalid option, please try again.");
    }
  }
  rl.close();
}

async function addNewChicken() {
  console.log("\n--- ADD NEW CHICKEN ---");
  const id = await rl.question('Chicken id: ')
  const name = await rl.question('Chicken name: ');
  const color = await rl.question('Chicken color: ');
  let age = parseInt(await rl.question('Chicken age: '));
  const isMolting = false; 

  if (isNaN(age) || name.trim() === '' || color.trim() === '') {
    console.log("Error: Please enter valid data.");
    return;
  }

  const newChicken = new Chicken(id, name, color, age, isMolting);
  
  if (farmCoops.length === 0) {
    console.log("Error: No coops initialized to add the chicken to.");
    return;
  }
    
    console.log("\n--- Select Chicken Coop ---");
    farmCoops.forEach((coop, index) => {
        console.log(`${index + 1}. ${coop.getName()}`);
    });
    console.log("---------------------------");

    const coopChoice = await rl.question(`Enter the number of the coop (1-${farmCoops.length}): `);
    const coopIndex = parseInt(coopChoice) - 1;

    if (isNaN(coopIndex) || coopIndex < 0 || coopIndex >= farmCoops.length) {
        console.log("Error: Invalid coop selection.");
        return;
    }

    const selectedCoop = farmCoops[coopIndex];
    selectedCoop.addChicken(newChicken);    
  
  await storage.save(farmCoops);

  console.log(`\nChicken "${name}" (ID: ${newChicken.id}) added and saved successfully to ${selectedCoop.getName()}!`);
}

async function listChickensFromJson() {
  console.log("\n--- STORED CHICKENS---");
  const loadedCoopData = await storage.load();

  let totalChickens = 0;
  
  if (loadedCoopData.length === 0) {
    console.log("No coop data registered in the JSON file.");
    return;
  }
  
  loadedCoopData.forEach(coopData => {
    console.log(`\nCoop ID: ${coopData.id}, Name: ${coopData.name}, Total Chickens: ${coopData.chickens.length}`);
    coopData.chickens.forEach(c => {
      console.log(` [ID: ${c.id}] Name: ${c.name}, Color: ${c.color}, Age: ${c.age}`);
      totalChickens++;
    });
  });
  console.log(`\nTotal chickens found across all coops: ${totalChickens}`);
}

mainMenu();