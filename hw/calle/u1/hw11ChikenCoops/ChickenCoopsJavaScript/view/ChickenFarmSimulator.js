const { Chicken } = require("../model/Chicken");
const { ChickenCoop } = require("../model/ChickenCoop");

const chickenCoops = [
  new ChickenCoop(1, "Main Coop"),
  new ChickenCoop(2, "Secondary Coop"),
];

const chickens = [
  new Chicken(1, "Lucy", "White", 2, true),
  new Chicken(2, "Maruja", "Brown", 1, false),
  new Chicken(3, "Cluckencia", "Speckled", 4, true),
  new Chicken(4, "Piolina", "Black", 2, false),
  new Chicken(5, "Plumifera", "Gray", 3, true),
  new Chicken(6, "Cascaronia", "Brown", 1, false),
  new Chicken(7, "Ponederas", "White", 2, true),
  new Chicken(8, "Picotera", "Golden", 4, false),
  new Chicken(9, "Alitona", "Red", 1, true),
  new Chicken(10, "Copetona", "Spotted", 3, false),
];

// Asignar gallinas a los gallineros
for (let i = 0; i < 5; i++) {
  chickenCoops[0].addChicken(chickens[i]);
}
for (let i = 5; i < chickens.length; i++) {
  chickenCoops[1].addChicken(chickens[i]);
}

// Mostrar información del primer gallinero
console.log(` Main Coop (ID: ${chickenCoops[0].id})`);
console.log(`Total chickens: ${chickenCoops[0].getChickens().length}`);
console.log("-".repeat(60));
console.log("ID  Name         Color        Age  Molting");
console.log("-".repeat(60));
for (const chicken of chickenCoops[0].getChickens()) {
  console.log(
    `${chicken.id.toString().padEnd(3)} ${chicken.name.padEnd(12)} ${chicken.color.padEnd(12)} ${chicken.age.toString().padEnd(4)} ${chicken.isMolting ? "Yes" : "No"}`
  );
}

console.log("\n" + "=".repeat(60));

// Mostrar información del segundo gallinero
console.log(`\n Secondary Coop (ID: ${chickenCoops[1].id})`);
console.log(`Total chickens: ${chickenCoops[1].getChickens().length}`);
console.log("-".repeat(60));
console.log("ID  Name         Color        Age  Molting");
console.log("-".repeat(60));
for (const chicken of chickenCoops[1].getChickens()) {
  console.log(
    `${chicken.id.toString().padEnd(3)} ${chicken.name.padEnd(12)} ${chicken.color.padEnd(12)} ${chicken.age.toString().padEnd(4)} ${chicken.isMolting ? "Yes" : "No"}`
  );
}

console.log("\n" + "=".repeat(60));
console.log("\nFarm Owner: Emily Calle");
console.log("=== Farm Information Display Completed ===");