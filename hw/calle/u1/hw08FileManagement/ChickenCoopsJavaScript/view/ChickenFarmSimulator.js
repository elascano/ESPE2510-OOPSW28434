const { Chicken } = require("../model/Chicken");
const { ChickenCoop } = require("../model/ChickenCoop");
const fs = require('fs');
const path = require('path');

function exportToCSV(chickenCoops, filename = "farm_data.csv") {
    const rows = [['Coop ID', 'Coop Name', 'Chicken ID', 'Chicken Name', 'Color', 'Age', 'Molting']];
    
    chickenCoops.forEach(coop => {
        coop.getChickens().forEach(chicken => {
            rows.push([
                coop.id,
                coop.name,
                chicken.id,
                chicken.name,
                chicken.color,
                chicken.age,
                chicken.isMolting ? 'Yes' : 'No'
            ]);
        });
    });

    const csvContent = rows.map(row => row.join(',')).join('\n');
    fs.writeFileSync(filename, csvContent, 'utf-8');
}

function exportToTXT(chickenCoops, filename = "farm_report.txt") {
    let content = "=== REPORTE DE LA GRANJA DE GALLINAS ===\n\n";
    content += "Propietaria: Emily Calle\n";
    content += "Fecha: 26 de octubre de 2025\n\n";

    chickenCoops.forEach(coop => {
        content += `\n${coop.name} (ID: ${coop.id})\n`;
        content += `Total de gallinas: ${coop.getChickens().length}\n`;
        content += "-".repeat(60) + "\n";
        content += "ID  Nombre       Color        Edad Mudando\n";
        content += "-".repeat(60) + "\n";

        coop.getChickens().forEach(chicken => {
            content += `${chicken.id.toString().padEnd(3)} ${chicken.name.padEnd(12)} 
            ${chicken.color.padEnd(12)} ${chicken.age.toString().padEnd(4)} ${chicken.isMolting ? 
                'Sí' : 'No'}\n`;
        });

        content += "\n" + "=".repeat(60) + "\n";
    });

    fs.writeFileSync(filename, content, 'utf-8');
}

function printCoopInfo(coop) {
    console.log(`\n ${coop.name} (ID: ${coop.id})`);
    console.log(`Total chickens: ${coop.getChickens().length}`);
    console.log("-".repeat(60));
    console.log("ID  Name         Color        Age  Molting");
    console.log("-".repeat(60));
    for (const chicken of coop.getChickens()) {
        console.log(
            `${chicken.id.toString().padEnd(3)} ${chicken.name.padEnd(12)} ${chicken.color.padEnd(12)}
             ${chicken.age.toString().padEnd(4)} ${chicken.isMolting ? "Yes" : "No"}`
        );
    }
    console.log("\n" + "=".repeat(60));
}

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

// Mostrar información en consola
chickenCoops.forEach(coop => printCoopInfo(coop));

console.log("\nFarm Owner: Emily Calle");
console.log("=== Farm Information Display Completed ===");

// Exportar datos a archivos
exportToCSV(chickenCoops);
exportToTXT(chickenCoops);

console.log("\nLos datos han sido exportados a:");
console.log("- farm_data.csv");
console.log("- farm_report.txt");