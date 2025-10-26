import { Chicken } from '../model/Chicken.js';
import { ChickenCoop } from '../model/ChickenCoop.js';

console.log("----- Welcome to the Chicken Farm Simulator -----");

const chickens = [
  new Chicken(1, "Lucy", "White", 2, false),
  new Chicken(2, "Maruja", "Brown", 1, true),
  new Chicken(3, "Rosita", "Black", 3, false),
  new Chicken(4, "Lola", "White and Brown", 2, true),
  new Chicken(5, "Clara", "Yellow", 1, false),
  new Chicken(6, "Rita", "Red", 2, false),
  new Chicken(7, "Sofi", "Black", 3, true),
  new Chicken(8, "Pepa", "White", 2, false),
  new Chicken(9, "Nina", "Gray", 1, true),
  new Chicken(10, "Luna", "Golden", 4, false),
];


const coop1 = new ChickenCoop(1);
const coop2 = new ChickenCoop(2);

for (let i = 0; i < 5; i++) coop1.addChicken(chickens[i]);
for (let i = 5; i < 10; i++) coop2.addChicken(chickens[i]);


coop1.showChickens();
coop2.showChickens();


import fs from "fs";

const outputText = `
Chicken Coop 1 has 5 chickens:
 - Lucy (White, 2 years old)
 - Maruja (Brown, 1 years old)
 - Rosita (Black, 3 years old)
 - Lola (White and Brown, 2 years old)
 - Clara (Yellow, 1 years old)

Chicken Coop 2 has 5 chickens:
 - Rita (Red, 2 years old)
 - Sofi (Black, 3 years old)
 - Pepa (White, 2 years old)
 - Nina (Gray, 1 years old)
 - Luna (Golden, 4 years old)
`;

// --- Guardar en TXT ---
fs.writeFileSync("./data/chickens.txt", outputText);

// --- Guardar en CSV ---
let csvData = "Coop,Name,Color,Age\n";
csvData += `
1,Lucy,White,2
1,Maruja,Brown,1
1,Rosita,Black,3
1,Lola,White and Brown,2
1,Clara,Yellow,1
2,Rita,Red,2
2,Sofi,Black,3
2,Pepa,White,2
2,Nina,Gray,1
2,Luna,Golden,4
`.trim();

fs.writeFileSync("./data/chickens.csv", csvData);

console.log(" Data saved successfully to data/chickens.txt and data/chickens.csv");