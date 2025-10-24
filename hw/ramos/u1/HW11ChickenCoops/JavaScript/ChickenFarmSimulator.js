
import Chicken  from "./Chicken.js";
import  ChickenCoop  from "./ChickenCoop.js";

function main() {
  console.log(" ---Chicken Farm Simulator--- \n");

  // Create Chicken Static
  const chickens = [
    new Chicken(1, "Lucy", "White", 2, false),
    new Chicken(2, "Nita", "Gray", 1, true),
    new Chicken(3, "Lola", "Black", 3, false),
    new Chicken(4, "Pepa", "White", 2, true),
    new Chicken(5, "Clara", "Gray", 1, false),
    new Chicken(6, "Rita", "Brown", 4, true),
    new Chicken(7, "Tina", "Gray", 2, false),
    new Chicken(8, "Sofi", "Red", 3, true),
    new Chicken(9, "Lili", "White", 2, false),
    new Chicken(10, "Dani", "White", 1, true),
  ];

  // Create Chicken Coop
  const coop1 = new ChickenCoop(1);
  const coop2 = new ChickenCoop(2);

  // Assign Chickens
  chickens.slice(0, 5).forEach(chicken => coop1.addChicken(chicken));
  chickens.slice(5).forEach(chicken => coop2.addChicken(chicken));

  console.log(coop1.toString());
  console.log();
  console.log(coop2.toString());
}

// Ejecutar simulador
main();
