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
        const chicken = new Chicken(ch.Name, ch.Color, ch.Age, ch.Molting, ch.ID);
        if (!coopsDict[ch.CoopName]) {
          coopsDict[ch.CoopName] = new ChickenCoop(ch.CoopName);
        }
        coopsDict[ch.CoopName].addChicken(chicken);
      }
      this.coops = Object.values(coopsDict);
    } catch (error) {
      console.log("Error reading Chickens.json or file not found.");
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
    const allChickens = this.coops.reduce((acc, c) => acc.concat(c.getChickens()), []);
    const nextId = allChickens.length + 1;

    const newChicken = new Chicken(name, color, age, isMolting, nextId);
    coop.addChicken(newChicken);
    this.saveChickens();
    console.log("\nThe chicken is clucking! Added successfully.\n");
  }

  async mainMenu() {
    console.log("\n  ---Welcome to the Chicken Farm Simulator---  ");
    while (true) {
      console.log("\nMain Menu:");
      console.log("1. View Chickens");
      console.log("2. Add Chicken");
      console.log("3. Exit");

      const choice = await this.getUserInput("Enter your choice: ");
      const num = parseInt(choice);

      if (isNaN(num) || num < 1 || num > 3) {
        console.log("Invalid input. Please enter a valid option.");
        continue;
      }

      if (num === 1) {
        await this.visualizeChickens();
      } else if (num === 2) {
        await this.addChicken();
      } else if (num === 3) {
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