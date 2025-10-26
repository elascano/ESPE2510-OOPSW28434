//chickenFarmSimulatorTXT.js
const fs = require('fs');
const readline = require('readline');

class Chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }
    
    toString() {
        return `Chicken: id=${this.id}, name=${this.name}, color=${this.color}, age=${this.age}, isMolting=${this.isMolting}`;
    }
    
    toTXT() {
        return `ID: ${this.id} | Name: ${this.name} | Color: ${this.color} | Age: ${this.age} | Molting: ${this.isMolting}`;
    }
}

class ChickenCoop {
    constructor(id, name, capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.chickens = [];
    }
    
    addChicken(chicken) {
        if (this.chickens.length < this.capacity) {
            this.chickens.push(chicken);
            return true;
        }
        console.log(`Coop ${this.name} is full! Cannot add ${chicken.name}`);
        return false;
    }
    
    displayCoop() {
        console.log(`\n=== ${this.name} (ID: ${this.id}) ===`);
        console.log(`Capacity: ${this.capacity}`);
        console.log(`Current chickens: ${this.chickens.length}`);
        console.log("Chickens in this coop:");
        this.chickens.forEach(chicken => {
            console.log(`  - ${chicken.toString()}`);
        });
    }
    
    toTXT() {
        let content = `COOP: ${this.name} (ID: ${this.id})\n`;
        content += `Capacity: ${this.capacity} | Current: ${this.chickens.length}\n`;
        content += "Chickens:\n";
        this.chickens.forEach(chicken => {
            content += `  - ${chicken.toTXT()}\n`;
        });
        content += "=".repeat(50) + "\n";
        return content;
    }
}

// Función para guardar en archivo TXT
function saveToTXT(coops, filename) {
    let content = "=== CHICKEN FARM REPORT ===\n";
    content += `Generated on: ${new Date().toLocaleString()}\n\n`;
    
    let totalChickens = 0;
    
    coops.forEach(coop => {
        content += coop.toTXT();
        totalChickens += coop.chickens.length;
    });
    
    content += `\nFARM SUMMARY:\n`;
    content += `Total Coops: ${coops.length}\n`;
    content += `Total Chickens: ${totalChickens}\n`;
    
    // Guardar productos de la granja
    content += `\nFARM PRODUCTS:\n`;
    content += `Egg: size=M\n`;
    content += `Poop: amount=5\n`;
    
    fs.writeFileSync(filename, content);
    console.log(`Data saved to ${filename}`);
}

// Función para guardar datos simples en otro archivo
function saveSimpleData(coops, filename) {
    let content = "";
    coops.forEach(coop => {
        coop.chickens.forEach(chicken => {
            content += `${coop.id},${coop.name},${chicken.id},${chicken.name},${chicken.color},${chicken.age},${chicken.isMolting}\n`;
        });
    });
    fs.writeFileSync(filename, content);
    console.log(` Simple data saved to ${filename}`);
}

// Crear granja con datos estáticos
function createStaticFarm() {
    // Crear 2 gallineros
    const coop1 = new ChickenCoop(1, "Main Coop", 6);
    const coop2 = new ChickenCoop(2, "Secondary Coop", 4);
    
    // Crear 10 gallinas
    const chickens = [
        new Chicken(1, "Henrietta", "Brown", 2, false),
        new Chicken(2, "Cluck Norris", "Black", 3, true),
        new Chicken(3, "Eggatha", "White", 1, false),
        new Chicken(4, "Feathers", "Red", 2, false),
        new Chicken(5, "Bok Choy", "Yellow", 1, true),
        new Chicken(6, "Nugget", "Brown", 4, false),
        new Chicken(7, "Drumstick", "Black", 2, false),
        new Chicken(8, "Sunny", "Yellow", 1, false),
        new Chicken(9, "Penny", "Red", 3, true),
        new Chicken(10, "Ginger", "Brown", 2, false)
    ];
    
    // Asignar gallinas a gallineros
    for (let i = 0; i < 6; i++) {
        coop1.addChicken(chickens[i]);
    }
    
    for (let i = 6; i < 10; i++) {
        coop2.addChicken(chickens[i]);
    }
    
    return [coop1, coop2];
}

// Función interactiva
function createInteractiveFarm() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    
    const coops = [
        new ChickenCoop(1, "Main Coop", 5),
        new ChickenCoop(2, "Secondary Coop", 5)
    ];
    
    let chickenCount = 0;
    const maxChickens = 5; // Menos gallinas para prueba
    
    function askForChicken() {
        if (chickenCount >= maxChickens) {
            rl.close();
            return coops;
        }
        
        console.log(`\n--- Creating Chicken ${chickenCount + 1} of ${maxChickens} ---`);
        
        rl.question("ID: ", (id) => {
            rl.question("Name: ", (name) => {
                rl.question("Color: ", (color) => {
                    rl.question("Age: ", (age) => {
                        rl.question("Is Molting? (true/false): ", (isMolting) => {
                            rl.question("Assign to Coop (1 or 2): ", (coopId) => {
                                const chicken = new Chicken(
                                    parseInt(id),
                                    name,
                                    color,
                                    parseInt(age),
                                    isMolting.toLowerCase() === "true"
                                );
                                
                                const coop = coops.find(c => c.id === parseInt(coopId));
                                if (coop && coop.addChicken(chicken)) {
                                    console.log("Chicken added successfully!");
                                } else {
                                    console.log("Failed to add chicken.");
                                }
                                
                                chickenCount++;
                                askForChicken();
                            });
                        });
                    });
                });
            });
        });
    }
    
    return new Promise((resolve) => {
        console.log("=== INTERACTIVE CHICKEN FARM CREATION ===");
        askForChicken();
        setTimeout(() => resolve(coops), 100);
    });
}

// Función principal
async function main() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    
    console.log("=== CHICKEN FARM SIMULATOR (TXT FILES) ===");
    console.log("Choose an option:");
    console.log("1. Use static data");
    console.log("2. Enter data interactively");
    
    rl.question("Your choice (1 or 2): ", async (choice) => {
        let coops;
        
        if (choice === "1") {
            coops = createStaticFarm();
        } else {
            coops = await createInteractiveFarm();
        }
        
        // Mostrar gallineros en consola
        console.log("\n" + "=".repeat(50));
        coops.forEach(coop => coop.displayCoop());
        
        // Guardar en archivos TXT
        saveToTXT(coops, "chicken_farm_report.txt");
        saveSimpleData(coops, "chicken_farm_data.txt");
        
        // Resumen final
        const totalChickens = coops.reduce((total, coop) => total + coop.chickens.length, 0);
        console.log(`\n=== FARM SUMMARY ===`);
        console.log(`Total coops: ${coops.length}`);
        console.log(`Total chickens: ${totalChickens}`);
        console.log(`Files created: chicken_farm_report.txt, chicken_farm_data.txt`);
        
        rl.close();
    });
}

// Ejecutar programa
main().catch(console.error);