// Owner/Author: Josue Rojas

import fs from "node:fs";
import { Chicken } from "./Chicken.js";
import { ChickenCoop } from "./ChickenCoop.js";

console.log("===  Chicken Farm Simulator (JavaScript) ===");

const coop1 = new ChickenCoop(1, "North Coop");
const coop2 = new ChickenCoop(2, "South Coop");

const chickens = [
  new Chicken(1, "Lucy", "White", 2, false),
  new Chicken(2, "Maruja", "Brown", 1, true),
  new Chicken(3, "Coco", "Black", 3, false),
  new Chicken(4, "Bella", "Yellow", 1, false),
  new Chicken(5, "Lola", "Gray", 2, true),
  new Chicken(6, "Nina", "White", 4, false),
  new Chicken(7, "Kira", "Brown", 2, false),
  new Chicken(8, "Daisy", "White", 1, false),
  new Chicken(9, "Molly", "Black", 3, true),
  new Chicken(10, "Gigi", "White", 2, false),
];

chickens.forEach((ch, i) => (i < 6 ? coop1 : coop2).addChicken(ch));

let eggId = 1, poopId = 1;
[coop1, coop2].forEach(coop => {
  coop.chickens.forEach(ch => {
    if (!ch.isMolting && Math.random() < 0.6) coop.collectEgg(ch.layEgg(eggId++));
    coop.collectPoop(ch.poop(poopId++));
  });
});

coop1.showInfo();
coop2.showInfo();

const rows = [["coopId", "coopName", "chickenId", "name", "color", "age", "isMolting", "eggsCount", "poopsCount"]];

[coop1, coop2].forEach(coop => {
  coop.chickens.forEach(c => {
    const eggsCount = coop.eggs.filter(e => e.chickenId === c.id).length;
    const poopsCount = coop.poops.filter(p => p.chickenId === c.id).length;
    rows.push([coop.id, coop.name, c.id, c.name, c.color, c.age, c.isMolting, eggsCount, poopsCount]);
  });
});

const csv = rows.map(r => r.join(",")).join("\n");
fs.writeFileSync("farm_data.csv", csv);

console.log("\n Single CSV created: farm_data.csv");
