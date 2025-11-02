import readlineSync from "readline-sync";
import { ChickenFarm } from "../model/ChickenFarm.js";

const farm = new ChickenFarm();

function showMenu() {
  console.log("\n=== Chicken Farm Simulator ===");
  console.log("1) Add Chicken");
  console.log("2) View All Chickens");
  console.log("3) Remove Chicken");
  console.log("4) Exit");
}

let exit = false;
while (!exit) {
  showMenu();
  const option = readlineSync.question("Select an option (1-4): ");

  switch (option) {
    case "1":
      const name = readlineSync.question("Enter chicken name: ");
      const age = parseInt(readlineSync.question("Enter chicken age: "), 10);
      const moltingInput = readlineSync.question("Is molting? (true/false): ").toLowerCase();
      const molting = moltingInput === "true";

      if (!name || isNaN(age)) {
        console.log(" Invalid input, please try again.");
        break;
      }

      farm.addChicken(name, age, molting);
      break;

    case "2":
      farm.showAll();
      break;

    case "3":
      const id = parseInt(readlineSync.question("Enter chicken ID to remove: "), 10);
      if (isNaN(id)) {
        console.log("Invalid ID.");
      } else {
        farm.removeChicken(id);
      }
      break;

    case "4":
      console.log("Exiting Chicken Farm Simulator...");
      exit = true;
      break;

    default:
      console.log("Invalid option. Please select 1–4.");
  }
}