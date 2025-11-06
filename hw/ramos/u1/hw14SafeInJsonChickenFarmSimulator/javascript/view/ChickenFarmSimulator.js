import fs from "fs";
import path from "path";
import readlineSync from "readline-sync";
import Chicken from "../model/Chicken.js";
import ChickenCoop from "../model/ChickenCoop.js";
import { fileURLToPath } from "url";

// Obtener la ruta del directorio actual (view)
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Carpeta "data" dentro de la estructura que contiene model y view
const BASE_DIR = path.resolve(__dirname, ".."); // subimos un nivel desde view
const DATA_DIR = path.join(BASE_DIR, "data");

if (!fs.existsSync(DATA_DIR)) {
    fs.mkdirSync(DATA_DIR);
}

// Ruta completa del archivo JSON
const DATA_FILE = path.join(DATA_DIR, "chicken_farm.json");


// Solo dos gallineros disponibles
let coops = [new ChickenCoop(1), new ChickenCoop(2)];

function main() {
  console.log("---  Chicken Farm Simulator ---\n");

  let option = 0;
  do {
    console.log("\n===== MENU =====");
    console.log("1. Insert chicken");
    console.log("2. List chickens");
    console.log("3. Delete chicken");
    console.log("4. Update chicken");
    console.log("5. Find chicken");
    console.log("6. Exit");

    option = readlineSync.questionInt("Choose an option: ");

    switch (option) {
      case 1:
        insertChicken();
        break;
      case 2:
        listChickens();
        break;
      case 3:
        deleteChicken();
        break;
      case 4:
        updateChicken();
        break;
      case 5:
        findChicken();
        break;
      case 6:
        console.log("Exiting simulator...");
        break;
      default:
        console.log("Invalid option.");
    }
  } while (option !== 6);
}

// === Funciones de apoyo ===

function loadData() {
  if (fs.existsSync(DATA_FILE)) {
    const rawData = fs.readFileSync(DATA_FILE);
    const jsonData = JSON.parse(rawData);

    coops = [new ChickenCoop(1), new ChickenCoop(2)];

    jsonData.coops.forEach(c => {
      const coop = coops.find(x => x.id === c.id);
      if (coop) {
        c.chickens.forEach(ch => {
          const chicken = new Chicken(ch.id, ch.name, ch.color, ch.age, ch.molting);
          coop.addChicken(chicken);
        });
      }
    });
  }
}

function saveData() {
  const data = {
    coops: coops.map(coop => ({
      id: coop.id,
      chickens: coop.chickens.map(c => ({
        id: c.getId(),
        name: c.getName(),
        color: c.getColor(),
        age: c.getAge(),
        molting: c.isMolting(),
      })),
    })),
  };

  fs.writeFileSync(DATA_FILE, JSON.stringify(data, null, 2));
}

// === CRUD ===

function insertChicken() {
  loadData();

  console.log("Available coops: 1 and 2");
  const coopId = readlineSync.questionInt("Enter coop ID (1 or 2): ");
  const coop = coops.find(c => c.id === coopId);

  if (!coop) {
    console.log("Invalid coop ID.");
    return;
  }

  // ID automático: el siguiente al mayor existente
  const nextId =
    coop.chickens.length > 0
      ? Math.max(...coop.chickens.map(c => c.getId())) + 1
      : 1;

  const name = readlineSync.question("Name: ");
  const color = readlineSync.question("Color: ");
  const age = readlineSync.questionInt("Age: ");
  const molting = readlineSync.keyInYN("Is molting? (y/n): ");

  const chicken = new Chicken(nextId, name, color, age, molting);
  coop.addChicken(chicken);

  saveData();
  console.log(`Chicken '${name}' added to coop ${coop.id} with ID ${nextId}`);
}

function listChickens() {
  loadData();

  coops.forEach(coop => {
    console.log(`\n Coop ${coop.id}:`);

    if (coop.chickens.length === 0) {
      console.log("\tNo chickens.");
      return;
    }

    console.log("\nID\tName\tColor\tAge\tMolting");
    console.log("---------------------------------------------------");

    coop.chickens.forEach(c => {
      console.log(
        `${c.getId()}\t${c.getName()}\t${c.getColor()}\t${c.getAge()}\t${c.isMolting() ? "Yes" : "No"}`
      );
    });

    console.log("---------------------------------------------------\n");
  });
}

function deleteChicken() {
  loadData();

  const coopId = readlineSync.questionInt("Enter coop ID (1 or 2): ");
  const coop = coops.find(c => c.id === coopId);

  if (!coop || coop.chickens.length === 0) {
    console.log("No chickens in this coop.");
    return;
  }

  const id = readlineSync.questionInt("Enter chicken ID to delete: ");
  const index = coop.chickens.findIndex(c => c.getId() === id);

  if (index !== -1) {
    coop.chickens.splice(index, 1);
    saveData();
    console.log(`🗑️ Chicken ${id} deleted.`);
  } else {
    console.log("Chicken not found.");
  }
}

function updateChicken() {
  loadData();

  const coopId = readlineSync.questionInt("Enter coop ID (1 or 2): ");
  const coop = coops.find(c => c.id === coopId);
  if (!coop || coop.chickens.length === 0) {
    console.log("No chickens found.");
    return;
  }

  const id = readlineSync.questionInt("Enter chicken ID to update: ");
  const chicken = coop.chickens.find(c => c.getId() === id);

  if (chicken) {
    const name = readlineSync.question(`New name (${chicken.getName()}): `) || chicken.getName();
    const color = readlineSync.question(`New color (${chicken.getColor()}): `) || chicken.getColor();
    const ageInput = readlineSync.question(`New age (${chicken.getAge()}): `);
    const age = ageInput === "" ? chicken.getAge() : parseInt(ageInput);
    const molting = readlineSync.keyInYNStrict("Is molting? (y/n): ");

    chicken.setName(name);
    chicken.setColor(color);
    chicken.setAge(age);
    chicken.setIsMolting(molting);

    saveData();
    console.log("Chicken updated successfully.");
  } else {
    console.log("Chicken not found.");
  }
}

function findChicken() {
  loadData();

  const id = readlineSync.questionInt("Enter chicken ID to find: ");
  let found = false;

  coops.forEach(coop => {
    const chicken = coop.chickens.find(c => c.getId() === id);
    if (chicken) {
      console.log(`\nFound in coop ${coop.id}:`);
      console.log("\nID\tName\tColor\tAge\tMolting");
      console.log("---------------------------------------------------");
      console.log(
        `${chicken.getId()}\t${chicken.getName()}\t${chicken.getColor()}\t${chicken.getAge()}\t${chicken.isMolting() ? "Yes" : "No"}`
      );
      console.log("---------------------------------------------------\n");
      found = true;
    }
  });

  if (!found) console.log("Chicken not found.");
}

main();
