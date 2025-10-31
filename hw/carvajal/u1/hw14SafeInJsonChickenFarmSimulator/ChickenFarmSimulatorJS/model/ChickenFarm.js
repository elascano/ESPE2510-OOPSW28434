import { ChickenCoop } from "./ChickenCoop.js";
import { Chicken } from "./Chicken.js";
import fs from "fs";
import path from "path";

export class ChickenFarm {
  constructor() {
    this.coops = [new ChickenCoop(1)];
    this.dataPath = path.join("data", "chickens.json");
    this.loadData();
  }

  loadData() {
    if (fs.existsSync(this.dataPath)) {
      const data = JSON.parse(fs.readFileSync(this.dataPath));
      this.coops = data.map((coopData) => {
        const coop = new ChickenCoop(coopData.id);
        coop.chickens = coopData.chickens.map(
          (c) => new Chicken(c.id, c.name, c.age, c.molting)
        );
        return coop;
      });
    }
  }

  saveData() {
    fs.writeFileSync(this.dataPath, JSON.stringify(this.coops, null, 2));
  }

  getNextId() {
    const all = this.coops.flatMap(coop => coop.chickens);
    return all.length + 1;
  }

  addChicken(name, age, molting) {
    const id = this.getNextId();
    const chicken = new Chicken(id, name, age, molting);

    let added = false;
    for (const coop of this.coops) {
      if (coop.addChicken(chicken)) {
        added = true;
        break;
      }
    }

    if (!added) {
      const newCoop = new ChickenCoop(this.coops.length + 1);
      newCoop.addChicken(chicken);
      this.coops.push(newCoop);
    }

    this.saveData();
    console.log(` Chicken ${name} added successfully!`);
  }

  removeChicken(id) {
    for (const coop of this.coops) {
      coop.removeChicken(id);
    }
    this.saveData();
    console.log(`❌ Chicken ${id} removed.`);
  }

  showAll() {
    console.log("\n=== Chickens in the Farm ===");
    for (const coop of this.coops) {
      console.log(`\nCoop ${coop.id}`);
      console.table(
        coop.chickens.map(c => ({
          ID: `Chicken ${c.id}`,
          Name: c.name,
          Age: c.age,
          Molting: c.molting
        }))
      );
    }
  }
}