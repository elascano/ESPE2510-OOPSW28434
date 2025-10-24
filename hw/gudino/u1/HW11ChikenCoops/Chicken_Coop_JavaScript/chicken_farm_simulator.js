const fs = require('fs');
const Chicken = require('./model/chicken');
const ChickenCoop = require('./model/chickenCoop');

// Crear 10 pollos
const chickens = [
    new Chicken(1, "Lucy", "black", 2, false),
    new Chicken(2, "Maruja", "white", 1, true),
    new Chicken(3, "Carmela", "brown", 3, false),
    new Chicken(4, "Pepa", "red", 2, false),
    new Chicken(5, "Nina", "yellow", 1, true),
    new Chicken(6, "Tita", "gray", 4, false),
    new Chicken(7, "Rosa", "white", 2, false),
    new Chicken(8, "Lola", "black", 1, false),
    new Chicken(9, "Mimi", "orange", 3, true),
    new Chicken(10, "Chispa", "brown", 2, false)
];

// Crear 2 gallineros
const coop1 = new ChickenCoop(101);
const coop2 = new ChickenCoop(102);

// Asignar pollos a gallineros
chickens.forEach((c, i) => {
    if (i < 5) coop1.add(c);
    else coop2.add(c);
});

// Mostrar contenido de gallineros
console.log("Coop 1 contents:");
coop1.showAllChickens();

console.log("\nCoop 2 contents:");
coop2.showAllChickens();

// Guardar en CSV
const allData = [["Coop ID", "Chicken ID", "Name", "Color", "Age", "Is Molting"]];
allData.push(...coop1.toArray(), ...coop2.toArray());

const csvContent = allData.map(e => e.join(",")).join("\n");

fs.writeFileSync("chickens_data.csv", csvContent);

console.log("\nData saved to 'chickens_data.csv' successfully!");

// Ejemplo de acciones
chickens[0].doStuff();
chickens[3].doStuff();
chickens[7].doStuff();
