
import fs from "fs";
import Chicken from "./Chicken.js";
import ChickenCoop from "./ChickenCoop.js";

function main() {
  console.log(" ---Chicken Farm Simulator--- \n");

  // Crear gallinas
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

  // Crear gallineros
  const coop1 = new ChickenCoop(1);
  const coop2 = new ChickenCoop(2);

  // Asignar gallinas
  chickens.slice(0, 5).forEach(chicken => coop1.addChicken(chicken));
  chickens.slice(5).forEach(chicken => coop2.addChicken(chicken));

  // Mostrar en consola
  console.log(coop1.toString());
  console.log();
  console.log(coop2.toString());

  // -----------------------------
  // Guardar en archivo TXT
  // -----------------------------
  const txtData = coop1.toString() + "\n\n" + coop2.toString();
  fs.writeFileSync("chicken_farm.txt", txtData);
  console.log("\n✅ Datos guardados en chicken_farm.txt");

  // -----------------------------
  // Guardar en archivo CSV
  // -----------------------------
  // Encabezado CSV
  let csvData = "CoopID,ChickenID,Name,Color,Age,IsMolting\n";

  // Agregar datos del coop1
  coop1.chickens.forEach(c => {
    csvData += `${coop1.id},${c.getId()},${c.getName()},${c.getColor()},${c.getAge()},${c.isMolting()}\n`;
  });

  // Agregar datos del coop2
  coop2.chickens.forEach(c => {
    csvData += `${coop2.id},${c.getId()},${c.getName()},${c.getColor()},${c.getAge()},${c.isMolting()}\n`;
  });

  fs.writeFileSync("chicken_farm.csv", csvData);
  console.log("✅ Datos guardados en chicken_farm.csv");
}

// Ejecutar simulador
main();