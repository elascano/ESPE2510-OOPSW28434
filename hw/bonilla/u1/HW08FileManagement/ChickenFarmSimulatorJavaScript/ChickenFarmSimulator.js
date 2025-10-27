const fs = require("fs");
const readline = require("readline");
const { Chicken } = require("./Chicken.js");
const { ChickenCoop } = require("./ChickenCoop.js");

class ChickenFarmSimulator {
  constructor() {
    this.coops = [];
  }

  displayWelcome() {
    console.log("\n  Welcome to the Chicken Farm Simulator  ");
  }

  setupFarm() {
    console.log("\nSetting up the chicken farm...");

    const coopsDict = {};
    const data = fs.readFileSync("ChickenFarmData.csv", "utf8").split("\n");
    data.shift(); 

    for (const line of data) {
      const trimmed = line.trim();
      if (!trimmed) continue;

      const parts = trimmed.split(",");
      const chickenId = parseInt(parts[0]);
      const name = parts[1];
      const color = parts[2];
      const age = parseInt(parts[3]);
      const isMolting = parts[4].trim().toLowerCase() === "true";
      const coopName = parts[5];

      const chicken = new Chicken(name, color, age, isMolting, chickenId);

      if (!coopsDict[coopName]) {
        coopsDict[coopName] = new ChickenCoop(coopName);
      }
      coopsDict[coopName].addChicken(chicken);
    }

    this.coops = Object.values(coopsDict);
  }

  displayFarmInfo() {
    console.log("\n ---- Farm Setup Complete ---- ");
    for (const coop of this.coops) {
      console.log(coop.toString());
    }
    console.log("-------------------------------");
  }

  showCoopDetails(coop) {
    console.log(`\n--- Chickens in ${coop.name} (ID: ${coop.id}) ---`);
    const chickens = coop.getChickens();

    if (chickens.length === 0) {
      console.log("This coop is currently empty.");
    } else {
      console.log(`Total Chickens: ${chickens.length}\n`);
      for (const chicken of chickens) {
        console.log(chicken.toString());
      }
    }
  }

  async getValidCoopChoice() {
    const rl = readline.createInterface({
      input: process.stdin,
      output: process.stdout,
    });

    const question = (text) =>
      new Promise((resolve) => rl.question(text, resolve));

    while (true) {
      const input = await question(`\nSelect a coop to view (1 to ${this.coops.length}): `);
      const choice = parseInt(input);

      if (!isNaN(choice) && choice >= 1 && choice <= this.coops.length) {
        rl.close();
        return this.coops[choice - 1];
      } else {
        console.log(`Invalid choice. Please enter a number between 1 and ${this.coops.length}.`);
      }
    }
  }

  async main() {
    this.displayWelcome();
    this.setupFarm();
    this.displayFarmInfo();

    while (true) {
      const coop = await this.getValidCoopChoice();
      this.showCoopDetails(coop);

      const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
      });

      const question = (text) =>
        new Promise((resolve) => rl.question(text, resolve));

      const again = (await question("\nDo you want to see another coop? (yes/no): "))
        .trim()
        .toLowerCase();

      rl.close();

      if (["no", "n"].includes(again)) {
        console.log("\nExiting the Chicken Farm Simulator.");
        return;
      } else if (!["yes", "y"].includes(again)) {
        console.log("Invalid input. Please type 'yes' or 'no'.");
      }
    }
  }
}

if (require.main === module) {
  const simulator = new ChickenFarmSimulator();
  simulator.main();
}

module.exports = { ChickenFarmSimulator };