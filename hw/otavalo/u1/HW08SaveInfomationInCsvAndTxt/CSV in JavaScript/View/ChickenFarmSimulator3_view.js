const fs = require('fs');
const prompt = require('prompt-sync')({ sigint: true });
const Chicken = require('../Model/Chicken'); 
const ChickenCoop = require('../Model/ChickenCoop');

// --- FUNCIÓN PARA GUARDAR EN CSV ---
function saveChickensToCsv(chickens, filename = 'mychickens.csv') {
    let csvContent = "Name;Color;Age;IsMolting\n";

    chickens.forEach(chicken => {
        // Usamos punto y coma (;) como delimitador
        const row = `${chicken.name};${chicken.color};${chicken.age};${chicken.isMolting}`;
        csvContent += row + "\n";
    });

    try {
        // fs.writeFileSync es la función que escribe en el disco
        fs.writeFileSync(filename, csvContent, 'utf8');
        console.log(`\n Todas las gallinas se han guardado en ${filename}`);
    } catch (err) {
        // Esto ayudará a diagnosticar si hay un problema de permisos
        console.error(" Error al escribir el archivo CSV:", err);
    }
}

console.log("Welcome to my Chicken Farm Simulator ");

function getValidatedInt(message, minimum, maximum) {
    let value;
    do {
        value = prompt(message);
        const parsedValue = parseInt(value);
        if (!/^\d+$/.test(value) || parsedValue < minimum || parsedValue > maximum) {
            console.log(` Please enter a valid integer number between ${minimum} and ${maximum}.`);
            value = null;
        }
    } while (value === null);
    return value;
}

const chickens = [
    new Chicken('Mariluna', 'Brown', 2, false),
    new Chicken('Clucky', 'White', 1, true),
    new Chicken('Josefa', 'Black', 3, false),
    new Chicken('Kiki', 'Yellow', 1, false),
    new Chicken('Lucilda', 'Brown and White', 4, true),
    new Chicken('Ruperta', 'Speckled', 2, false),
    new Chicken('Lili', 'White', 3, false),
    new Chicken('Coco', 'Black and White', 1, true),
    new Chicken('Pepa', 'Brown', 2, false),
    new Chicken('Sunny', 'Brown and Yellow', 4, false),
];

const coop1 = new ChickenCoop('Kikiriki Palace');
const coop2 = new ChickenCoop('Chickens on Board');
const coops = [coop1, coop2];

for (let i = 0; i < 6; i++) {
    coop1.addChicken(chickens[i]);
}

for (let i = 6; i < 10; i++) {
    coop2.addChicken(chickens[i]);
}

saveChickensToCsv(chickens);

console.log("\n--- Farm Setup Complete ---");
console.log(coop1.toString());
console.log(coop2.toString());
console.log('---------------------------');

do {
    const coopIdToView = getValidatedInt("Which Chicken Coop would you like to view (1 or 2)? ", 1, 2);
    const selectedCoop = coops[coopIdToView - 1];

    console.log(`\n--- Chickens in ${selectedCoop.name} (ID: ${selectedCoop.id}) ---`);

    if (selectedCoop.getChickens().length === 0) {
        console.log("This coop is currently empty.");
    } else {
        console.log(`Number of Chickens: ${selectedCoop.getChickens().length}\n`);
        
        selectedCoop.getChickens().forEach((chicken) => {
            console.log(chicken.toString()); 
        });
    }

    var repeat = prompt("Do you want to view another coop? (yes/no): ").toLowerCase().trim();
} while (repeat === "yes");

console.log("\nExiting the Chicken Farm Simulator. Goodbye! ");