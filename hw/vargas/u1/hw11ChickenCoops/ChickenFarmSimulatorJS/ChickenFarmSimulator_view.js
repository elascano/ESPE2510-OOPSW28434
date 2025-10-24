import { Chicken } from "./Chicken_model.js";
import { ChickenCoop } from "./ChickenCoop_model.js";
console.log("This is my Chicken Farm Simulator");
let chicken = new Chicken(1, "Lucy", "White and Brown", 2, false);
let chicken2 = new Chicken(0, "Maruja", "White", 1, true);

let allChickens = [
    chicken, chicken2, 
    new Chicken(3, "Lola", "White", 2, true),
    new Chicken(4, "Pepa", "Black", 1, false),
    new Chicken(5, "Gusepa", "Brown and white", 4, false),
    new Chicken(6, "Pancracia", "Gray", 2, true),
    new Chicken(7, "Federica", "Brown", 1, false),
    new Chicken(8, "Pancha", "White", 3, false),
    new Chicken(9, "Zoe", "Black", 2, false),
    new Chicken(10, "Lina", "Brown and white", 1, true),
];

let coop1 = new ChickenCoop("Chicken Coop A");
let coop2 = new ChickenCoop("Chicken Coop B");

for (let i = 0; i < 7; i++) {
    coop1.addChicken(allChickens[i]); 
}

for (let i = 7; i < 10; i++) {
    coop2.addChicken(allChickens[i]);
}

console.log("\n\n=== Showing Chicken Coops ===");

ChickenCoop.listAllCoops();