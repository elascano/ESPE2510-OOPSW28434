// Autor: Josue Carvajal
// Versión: 0.1

import { Chicken } from "./Chicken.js";

function main() {
  console.log("This is my Chicken Farm Simulator");

  const owner = "Josue Carvajal";
  const id = 1;
  const name = "Lucy";
  const color = "White and Brown";
  const age = 2;
  const isMolting = false;

  const chicken = new Chicken(id, name, color, age, isMolting);

  console.log(`The chicken is ${chicken.toString()}`);
  console.log(`chicken id -->\t${chicken.getId()}  ${chicken.getName()}`);
}

main();
