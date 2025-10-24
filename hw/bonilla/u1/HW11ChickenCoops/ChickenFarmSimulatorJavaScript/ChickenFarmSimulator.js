const { Chicken } = require('./Chicken.js');
const { ChickenCoop } = require('./ChickenCoop.js');
const prompt = require('prompt-sync')({ sigint: true }); 

class ChickenFarmSimulator {
  constructor() {
    this.coops = [];
  }

  displayWelcome() {
    console.log("\n  ---Welcome to the Chicken Farm Simulator---  ");
  }

  setupFarm() {
    console.log("\nSetting up the chicken farm...");

    const chickens = [
      new Chicken("Lucy", "White and Brown", 2, false),
      new Chicken("Maruja", "White", 1, true),
      new Chicken("Carmela", "Brown", 2, false),
      new Chicken("Pepa", "Black", 3, false),
      new Chicken("Mary", "Brown", 4, true),
      new Chicken("Josefa", "Golden", 3, false),
      new Chicken("Betty", "Black", 1, false),
      new Chicken("Rita", "White and Brown", 2, false),
      new Chicken("Blanca", "White", 3, true),
      new Chicken("Tina", "Golden", 1, false)
    ];

    const coop1 = new ChickenCoop("Happy Hens Coop");
    const coop2 = new ChickenCoop("Sunrise Nest");

    for (let i = 0; i < 6; i++) {
      coop1.addChicken(chickens[i]);
    }

    for (let i = 6; i < 10; i++) {
      coop2.addChicken(chickens[i]);
    }

    this.coops = [coop1, coop2];
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

  getValidCoopChoice() {
    while (true) {
      const choice = parseInt(prompt("\nSelect a coop to view (1 or 2): "));
      if (!isNaN(choice) && choice >= 1 && choice <= this.coops.length) {
        return this.coops[choice - 1];
      } else {
        console.log("Invalid choice. Please enter 1 or 2.");
      }
    }
  }

  main() {
    this.displayWelcome();
    this.setupFarm();
    this.displayFarmInfo();

    while (true) {
      const coop = this.getValidCoopChoice();
      this.showCoopDetails(coop);

      const again = prompt("\nDo you want to see another coop? (yes/no): ").trim().toLowerCase();
      if (["no", "n"].includes(again)) {
        console.log("\nExiting the Chicken Farm Simulator.");
        break;
      }
    }
  }
}

const simulator = new ChickenFarmSimulator();
simulator.main();