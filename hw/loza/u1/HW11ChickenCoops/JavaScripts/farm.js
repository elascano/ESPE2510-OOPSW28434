// farm.js
import { Coop } from "./coop.js";
import { Chicken } from "./chicken.js";
import fs from "fs";
import readlineSync from "readline-sync";

function main() {
  const farm = [];

  const numCoops = parseInt(readlineSync.question("How many coops does the farm have?: "));

  for (let i = 0; i < numCoops; i++) {
    const coopName = readlineSync.question(`Enter name for coop ${i + 1}: `);
    const coop = new Coop(coopName);

    const numChickens = parseInt(readlineSync.question(`How many chickens are in ${coopName}?: `));

    for (let j = 0; j < numChickens; j++) {
      const chickenName = readlineSync.question(`Enter name for chicken ${j + 1}: `);
      const chicken = new Chicken(chickenName);
      coop.addChicken(chicken);
    }

    farm.push(coop);
  }

  console.log("\n=== FARM STRUCTURE ===");
  for (const coop of farm) {
    console.log(coop.toString());
  }

  saveToCSV(farm);
  console.log("\n✅ CSV file created successfully: farm.csv");
}

function saveToCSV(farm) {
  const header = "Coop,Chicken\n";
  let rows = "";

  for (const coop of farm) {
    for (const chicken of coop.getChickens()) {
      rows += `${coop.getCoopName()},${chicken.getName()}\n`;
    }
  }

  fs.writeFileSync("farm.csv", header + rows, "utf-8");
}

main();
