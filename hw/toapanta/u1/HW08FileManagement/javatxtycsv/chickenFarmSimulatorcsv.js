// chickenFarmSimulatorCSV.js
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
    
    toCSV() {
        return [this.id, this.name, this.color, this.age, this.isMolting];
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
    
    toCSV() {
        return this.chickens.map(chicken => [
            this.id,
            this.name,
            ...chicken.toCSV()
        ]);
    }
}

// Función para guardar en archivo CSV
function saveToCSV(coops, filename) {
    const headers = ["CoopID", "CoopName", "ChickenID", "ChickenName", "Color", "Age", "IsMolting"];
    let csvContent = headers.join(",") + "\n";
    
    coops.forEach(coop => {
        const coopData = coop.toCSV();
        coopData.forEach(row => {
            csvContent += row.join(",") + "\n";
        });
    });
    
    fs.writeFileSync(filename, csvContent);
    console.log(`✅ CSV data saved to ${filename}`);
}

// Función para guardar reporte detallado en CSV
function saveDetailedReport(coops, filename) {
    const headers = ["Type", "ID", "Name", "Details1", "Details2", "Details3", "Details4"];
    let csvContent = headers.join(",") + "\n";
    
    // Agregar información de gallineros
    coops.forEach(coop => {
        csvContent += `Coop,${coop.id},"${coop.name}",Capacity: ${coop.capacity},Chickens: ${coop.chickens.length},,\n`;
    });
    
    // Agregar información de gallinas
    coops.forEach(coop => {
        coop.chickens.forEach(chicken => {
            csvContent += `Chicken,${chicken.id},"${chicken.name}",Color: ${chicken.color},Age: ${chicken.age},Molting: ${chicken.isMolting},Coop: ${coop.name}\n`;
        });
    });
    
    // Agregar productos
    csvContent += `Product,1,Egg,Size: M,,,,\n`;
    csvContent += `Product,2,Poop,Amount: 5,,,,\n`;
    
    fs.writeFileSync(filename, csvContent);
    console.log(`✅ Detailed report saved to ${filename}`);
}

// Función para guardar estadísticas en CSV
function saveStatistics(coops, filename) {
    const headers = ["Statistic", "Value"];
    let csvContent = headers.join(",") + "\n";
    
    const totalChickens = coops.reduce((total, coop) => total + coop.chickens.length, 0);
    const totalCapacity = coops.reduce((total, coop) => total + coop.capacity, 0);
    const moltingChickens = coops.reduce((total, coop) => 
        total + coop.chickens.filter(chicken => chicken.isMolting).length, 0
    );
    
    csvContent += `Total Coops,${coops.length}\n`;
    csvContent += `Total Chickens,${totalChickens}\n`;
    csvContent += `Total Capacity,${totalCapacity}\n`;
    csvContent += `Available Space,${totalCapacity - totalChickens}\n`;
    csvContent += `Molting Chickens,${moltingChickens}\n`;
    csvContent += `Non-Molting Chickens,${totalChickens - moltingChickens}\n`;
    
    // Estadísticas por gallinero
    coops.forEach(coop => {
        const moltingInCoop = coop.chickens.filter(chicken => chicken.isMolting).length;
        csvContent += `"Coop ${coop.id} - Chickens",${coop.chickens.length}\n`;
        csvContent += `"Coop ${coop.id} - Capacity",${coop.capacity}\n`;
        csvContent += `"Coop ${coop.id} - Molting",${moltingInCoop}\n`;
    });
    
    fs.writeFileSync(filename, csvContent);
    console.log(`✅ Statistics saved to ${filename}`);
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
    const maxChickens = 5;
    
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
                                    console.log(" Chicken added successfully!");
                                } else {
                                    console.log(" Failed to add chicken.");
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
    
    console.log("=== CHICKEN FARM SIMULATOR (CSV FILES) ===");
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
        
        // Guardar en múltiples archivos CSV
        saveToCSV(coops, "chicken_farm_data.csv");
        saveDetailedReport(coops, "chicken_farm_detailed.csv");
        saveStatistics(coops, "chicken_farm_statistics.csv");
        
        // Resumen final
        const totalChickens = coops.reduce((total, coop) => total + coop.chickens.length, 0);
        console.log(`\n=== FARM SUMMARY ===`);
        console.log(`Total coops: ${coops.length}`);
        console.log(`Total chickens: ${totalChickens}`);
        console.log(`CSV files created:`);
        console.log(`   - chicken_farm_data.csv (datos principales)`);
        console.log(`   - chicken_farm_detailed.csv (reporte detallado)`);
        console.log(`   - chicken_farm_statistics.csv (estadísticas)`);
        
        rl.close();
    });
}

// Ejecutar programa
main().catch(console.error);