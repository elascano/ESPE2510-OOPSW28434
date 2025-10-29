import fs from "fs";
import readlineSync from "readline-sync";
import Chicken from "../Model/Chicken.js";
import ChickenCoop from "../Model/ChickenCoop.js";

const DATA_FILE = "chicken_farm.json";

// Aquí guardaremos todos los gallineros
let coops = [];

function main() {
  console.log("- - -Chicken Farm Simulator- - -  \n");


  if (fs.existsSync(DATA_FILE)) {
    const rawData = fs.readFileSync(DATA_FILE);
    const jsonData = JSON.parse(rawData);

    jsonData.coops.forEach(c => {
      const coop = new ChickenCoop(c.id);
      c.chickens.forEach(ch => {
        const chicken = new Chicken(ch.id, ch.name, ch.color, ch.age, ch.molting);
        coop.addChicken(chicken);
      });
      coops.push(coop);
    });

    console.log(`Loaded ${coops.length} chicken coops from ${DATA_FILE}`);
  } else {
    console.log("No JSON file found. Starting with empty coops.");
  }

  let option = 0;
  do {
    console.log("\n. . . MENU . . . ");
    console.log("1. Add a chicken.");
    console.log("2. View chicken coops.");
    console.log("3. Make a chicken do something. ");
    console.log("4. Exit ");

    option = readlineSync.questionInt("Choose an option: ");

    switch (option) {
      case 1:
        addChickenToCoop();
        break;
      case 2:
        viewCoops();
        break;
      case 3:
        makeChickenDoStuff();
        break;
      case 4:
        console.log("Bye Bye.");
        break;
      default:
        console.log("Invalid option.");
    }
  } while (option !== 4);
}


function addChickenToCoop() {
  let coop;
  if (coops.length === 0) {
    const coopId = readlineSync.questionInt("No coops exist. Enter new ID: ");
    coop = new ChickenCoop(coopId);
    coops.push(coop);
  } else {
    console.log("Available coops:");
    coops.forEach(c => console.log(`- Coop ID: ${c.id} (${c.chickens.length} chickens)`));
    const coopId = readlineSync.questionInt("Enter the ID of the coop to add the chicken: / Write 0 if you want to create a new one. ");

    if (coopId === 0) {
      const newId = readlineSync.questionInt("Enter new coop ID: ");
      coop = new ChickenCoop(newId);
      coops.push(coop);
    } else {
      coop = coops.find(c => c.id === coopId);
      if (!coop) {
        console.log("Coop not found, creating a new one.");
        coop = new ChickenCoop(coopId);
        coops.push(coop);
      }
    }
  }


  console.log("\n. . . Add a new chicken . . . ");
  const id = readlineSync.questionInt("ID: ");
  const name = readlineSync.question("Name: ");
  const color = readlineSync.question("Color: ");
  const age = readlineSync.questionInt("Age: ");
  const molting = readlineSync.keyInYN("Is the chicken molting? (y / n): ");

  const chicken = new Chicken(id, name, color, age, molting);
  coop.addChicken(chicken);

  console.log(`Chicken '${name}' added to coop ${coop.id}!`);


  saveDataJSON();
}

function viewCoops() {
  if (coops.length === 0) {
    console.log("No coops available.");
    return;
  }

  coops.forEach(coop => {
    console.log(`\nChickenCoop ID: ${coop.id}`);
    if (coop.chickens.length === 0) console.log("  No chickens in this coop.");
    else coop.chickens.forEach(ch => console.log("  " + ch.toString()));
  });
}


function makeChickenDoStuff() {
  if (coops.length === 0) {
    console.log("No coops available.");
    return;
  }

  const coopId = readlineSync.questionInt("Enter coop ID: ");
  const coop = coops.find(c => c.id === coopId);
  if (!coop || coop.chickens.length === 0) {
    console.log("No chickens in this coop.");
    return;
  }

  coop.chickens.forEach(ch => console.log(ch.toString()));
  const chickenId = readlineSync.questionInt("Enter the ID of the chicken to do actions: ");
  const chicken = coop.chickens.find(c => c.getId() === chickenId);

  if (chicken) {
    console.log(`\nDoing actions with ${chicken.getName()}...\n`);
    chicken.doStuff();
  } else {
    console.log("Chicken with that ID not found.");
  }
}


function saveDataJSON() {
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
  console.log("Data saved automatically to " + DATA_FILE);
}

main();
