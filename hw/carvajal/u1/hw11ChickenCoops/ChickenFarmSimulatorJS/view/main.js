import { Chicken } from '../model/Chicken.js';
import { ChickenCoop } from '../model/ChickenCoop.js';

console.log("----- Welcome to the Chicken Farm Simulator -----");

// Crear gallinas
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

// Crear gallineros
const coop1 = new ChickenCoop(1);
const coop2 = new ChickenCoop(2);

// Asignar gallinas
for (let i = 0; i < 5; i++) coop1.addChicken(chickens[i]);
for (let i = 5; i < 10; i++) coop2.addChicken(chickens[i]);

// Mostrar resultados
coop1.showChickens();
coop2.showChickens();

console.log("\n✅ Simulation finished successfully!");