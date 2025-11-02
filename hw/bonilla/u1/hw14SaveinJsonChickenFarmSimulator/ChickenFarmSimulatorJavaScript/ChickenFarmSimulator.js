const fs = require("fs");
const readline = require("readline");
const { Chicken } = require("./Chicken.js");
const { ChickenCoop } = require("./ChickenCoop.js");

class ChickenFarmMenu {
  constructor() {
    this.coops = [];
    this.fileName = "Chickens.json";
    this.loadChickens();
  }

  loadChickens() {
    try {
      const data = fs.readFileSync(this.fileName, "utf8");
      const chickensData = JSON.parse(data);
      const coopsDict = {};
      for (const ch of chickensData) {
        if (!coopsDict[ch.CoopName]) {
          coopsDict[ch.CoopName] = new ChickenCoop(ch.CoopName);
        }
        const chicken = new Chicken(ch.Name, ch.Color, ch.Age, ch.Molting, ch.ID);
        coopsDict[ch.CoopName].addChicken(chicken);
      }
      this.coops = Object.values(coopsDict);
    } catch (error) {
      this.coops = [new ChickenCoop("Happy Hens Coop"), new ChickenCoop("Sunrise Nest")];
    }
  }

  saveChickens() {
    const allChickens = [];
    for (const coop of this.coops) {
      for (const ch of coop.getChickens()) {
        allChickens.push({
          ID: ch.id,
          Name: ch.name,
          Color: ch.color,
          Age: ch.age,
          Molting: ch.isMolting,
          CoopName: coop.name,
        });
      }
    }
    fs.writeFileSync(this.fileName, JSON.stringify(allChickens, null, 2), "utf8");
  }

  displayChickens(coop) {
    console.log(`\n          ----Chickens in ${coop.name}----  `);
    const chickens = coop.getChickens();
    if (chickens.length === 0) {
      console.log("No chickens found.");
      return;
    }

    const headers = ["ID", "Name", "Color", "Age", "Molting"];
    const colWidths = [5, 15, 20, 5, 10];

    const formatRow = (cols) => {
      return cols
        .map((col, i) => String(col).padEnd(colWidths[i]))
        .join("");
    };

    console.log(formatRow(headers));
    console.log(formatRow(headers.map(h => "-".repeat(h.length))));

    chickens.forEach(ch => {
      const row = [
        ch.id,
        ch.name,
        ch.color,
        ch.age,
        ch.isMolting ? "Yes" : "No"
      ];
      console.log(formatRow(row));
    });
  }

  async getUserInput(prompt) {
    const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
    const answer = await new Promise(resolve => rl.question(prompt, resolve));
    rl.close();
    return answer.trim();
  }

  async selectCoop() {
    while (true) {
      console.log("\nSelect a Coop:");
      this.coops.forEach((c, i) => console.log(`${i + 1}. ${c.name}`));
      const choice = await this.getUserInput("Enter your choice: ");
      const num = parseInt(choice);
      if (!isNaN(num) && num >= 1 && num <= this.coops.length) {
        return this.coops[num - 1];
      }
      console.log("Invalid input. Please enter a valid number.");
    }
  }

  async visualizeChickens() {
    const coop = await this.selectCoop();
    this.displayChickens(coop);
  }

  async addChicken() {
    const coop = await this.selectCoop();
    const name = await this.getUserInput("Enter chicken name: ");
    const color = await this.getUserInput("Enter chicken color: ");
    let age;
    while (true) {
      const input = await this.getUserInput("Enter chicken age (number): ");
      age = parseInt(input);
      if (!isNaN(age) && age > 0) break;
      console.log("Invalid age. Enter a valid number.");
    }
    let moltingInput;
    while (true) {
      moltingInput = (await this.getUserInput("Is the chicken molting? (yes/no): ")).toLowerCase();
      if (["yes", "no", "y", "n"].includes(moltingInput)) break;
      console.log("Invalid input. Enter yes or no.");
    }
    const isMolting = moltingInput.startsWith("y");
    const nextId = coop.getChickens().length + 1;
    const newChicken = new Chicken(name, color, age, isMolting, nextId);
    coop.addChicken(newChicken);
    this.saveChickens();
    console.log("\nThe chicken is clucking! Added successfully.\n");
    await this.afterActionPrompt();
  }

  async findChickenById(coop, id) {
    const chickens = coop.getChickens();
    return chickens.find(c => c.id === id) || null;
  }

  async promptForId(coop) {
    while (true) {
      const input = await this.getUserInput("Enter chicken ID: ");
      const id = parseInt(input);
      if (isNaN(id)) {
        console.log("Invalid input. Enter a valid number.");
        continue;
      }
      const chicken = await this.findChickenById(coop, id);
      if (!chicken) {
        console.log("No chicken found with that ID in this coop.");
        continue;
      }
      return chicken;
    }
  }

  async searchChicken() {
    const coop = await this.selectCoop();
    const chicken = await this.promptForId(coop);
    this.displaySingleChicken(coop, chicken);
    await this.afterActionPrompt();
  }

  displaySingleChicken(coop, ch) {
    console.log(`\n          ----Chicken in ${coop.name}----  `);
    const headers = ["ID", "Name", "Color", "Age", "Molting"];
    const colWidths = [5, 15, 20, 5, 10];
    const formatRow = (cols) => {
      return cols
        .map((col, i) => String(col).padEnd(colWidths[i]))
        .join("");
    };
    console.log(formatRow(headers));
    console.log(formatRow(headers.map(h => "-".repeat(h.length))));
    const row = [ch.id, ch.name, ch.color, ch.age, ch.isMolting ? "Yes" : "No"];
    console.log(formatRow(row));
  }

  async editChicken() {
    const coop = await this.selectCoop();
    const chicken = await this.promptForId(coop);
    while (true) {
      this.displaySingleChicken(coop, chicken);
      const newName = await this.getUserInput(`Enter new name (press enter to keep "${chicken.name}"): `);
      if (newName !== "") chicken.name = newName;
      const newColor = await this.getUserInput(`Enter new color (press enter to keep "${chicken.color}"): `);
      if (newColor !== "") chicken.color = newColor;
      while (true) {
        const newAgeInput = await this.getUserInput(`Enter new age (press enter to keep "${chicken.age}"): `);
        if (newAgeInput === "") break;
        const newAge = parseInt(newAgeInput);
        if (!isNaN(newAge) && newAge > 0) {
          chicken.age = newAge;
          break;
        }
        console.log("Invalid age. Enter a valid number.");
      }
      while (true) {
        const moltingInput = await this.getUserInput(`Is the chicken molting? (yes/no, press enter to keep "${chicken.isMolting ? 'Yes' : 'No'}"): `);
        if (moltingInput === "") break;
        const low = moltingInput.toLowerCase();
        if (["yes", "no", "y", "n"].includes(low)) {
          chicken.isMolting = low.startsWith("y");
          break;
        }
        console.log("Invalid input. Enter yes or no.");
      }
      const confirm = await this.getUserInput("Are you sure the modification is correct? (1. Yes / 2. Edit again): ");
      if (confirm === "1") break;
    }
    this.saveChickens();
    console.log("\nChicken updated successfully.\n");
    await this.afterActionPrompt();
  }

  async deleteChicken() {
    const coop = await this.selectCoop();
    const chicken = await this.promptForId(coop);
    this.displaySingleChicken(coop, chicken);
    while (true) {
      const confirm = await this.getUserInput("Are you sure you want to delete this chicken? (1. Yes / 2. No): ");
      if (confirm === "1") {
        const index = coop.getChickens().findIndex(c => c.id === chicken.id);
        if (index !== -1) {
          coop.getChickens().splice(index, 1);
          for (let i = 0; i < coop.getChickens().length; i++) {
            coop.getChickens()[i].id = i + 1;
          }
          this.saveChickens();
          console.log("Chicken deleted successfully.");
          break;
        }
      } else if (confirm === "2") {
        console.log("Deletion cancelled.");
        break;
      }
      console.log("Invalid input. Enter 1 or 2.");
    }
    await this.afterActionPrompt();
  }

  async afterActionPrompt() {
    while (true) {
      console.log("\n1. Return to Menu");
      console.log("2. Exit Program");
      const choice = await this.getUserInput("Enter your choice: ");
      if (choice === "1") return;
      if (choice === "2") {
        console.log("Exiting the program.");
        process.exit(0);
      }
      console.log("Invalid input. Enter 1 or 2.");
    }
  }

  async mainMenu() {
    console.log("\n  ---Welcome to the Chicken Farm Simulator---  ");
    while (true) {
      console.log("\nMain Menu:");
      console.log("1. View Chickens");
      console.log("2. Add Chicken");
      console.log("3. Edit Chicken");
      console.log("4. Delete Chicken");
      console.log("5. Search Chicken");
      console.log("6. Exit");

      const choice = await this.getUserInput("Enter your choice: ");
      const num = parseInt(choice);

      if (isNaN(num) || num < 1 || num > 6) {
        console.log("Invalid input. Please enter a valid option.");
        continue;
      }

      if (num === 1) {
        await this.visualizeChickens();
      } else if (num === 2) {
        await this.addChicken();
      } else if (num === 3) {
        await this.editChicken();
      } else if (num === 4) {
        await this.deleteChicken();
      } else if (num === 5) {
        await this.searchChicken();
      } else if (num === 6) {
        console.log("Exiting the program.");
        break;
      }
    }
  }
}

if (require.main === module) {
  const menu = new ChickenFarmMenu();
  menu.mainMenu();
}

module.exports = { ChickenFarmMenu };