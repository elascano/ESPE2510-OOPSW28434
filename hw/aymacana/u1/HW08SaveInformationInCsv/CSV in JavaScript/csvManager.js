import fs from 'fs';
import { Chicken } from './chicken.js';
import { ChickenCoop } from './chickenCoop.js';

class CSVManager {
    static saveToCSV(coops, filename = "chicken_farm.csv") {
        try {
            let csvContent = "id,name,color,age,is_molting,coop_name\n";
            
            coops.forEach(coop => {
                coop.getAllChickens().forEach(chicken => {
                    csvContent += `${chicken.id},${chicken.name},${chicken.color},${chicken.age},${chicken.isMolting},${coop.name}\n`;
                });
            });
            
            fs.writeFileSync(filename, csvContent, 'utf8');
            console.log(`Data saved successfully to ${filename}`);
            console.log(`Saved ${coops.reduce((total, coop) => total + coop.countChickens(), 0)} chickens`);
        } catch (error) {
            console.log(`Error saving to CSV: ${error.message}`);
        }
    }

    static loadFromCSV(coops, filename = "chicken_farm.csv") {
        try {
            if (!fs.existsSync(filename)) {
                console.log(`File ${filename} not found`);
                return;
            }
            
            const fileContent = fs.readFileSync(filename, 'utf8');
            const lines = fileContent.split('\n').filter(line => line.trim() !== '');
            
            if (lines.length <= 1) {
                console.log("CSV file is empty or has no data");
                return;
            }
            
            coops.forEach(coop => {
                const chickensToRemove = coop.getAllChickens();
                chickensToRemove.forEach(chicken => {
                    coop.removeChicken(chicken.id);
                });
            });
            
            let chickensLoaded = 0;
            
            for (let i = 1; i < lines.length; i++) {
                try {
                    const row = lines[i].split(',');
                    if (row.length !== 6) continue;
                    
                    const [id, name, color, age, isMolting, coopName] = row;
                    
                    const chicken = new Chicken(
                        parseInt(id),
                        name,
                        color,
                        parseInt(age),
                        isMolting.toLowerCase() === 'true'
                    );
                    
                    let targetCoop = coops.find(coop => coop.name === coopName);
                    if (!targetCoop) {
                        targetCoop = new ChickenCoop(coopName);
                        coops.push(targetCoop);
                    }
                    
                    targetCoop.addChicken(chicken);
                    chickensLoaded++;
                    
                } catch (rowError) {
                    console.log(`Error processing row ${i}: ${lines[i]}`);
                    continue;
                }
            }
            
            console.log(`Data loaded successfully from ${filename}`);
            console.log(`Loaded ${chickensLoaded} chickens`);
            
        } catch (error) {
            console.log(`Error loading from CSV: ${error.message}`);
        }
    }

    static displayCSVFiles() {
        try {
            const files = fs.readdirSync('.');
            const csvFiles = files.filter(file => file.endsWith('.csv'));
            
            if (csvFiles.length === 0) {
                console.log("No CSV files in current directory");
            } else {
                console.log("\nAvailable CSV files:");
                csvFiles.forEach((file, index) => {
                    console.log(`  ${index + 1}. ${file}`);
                });
            }
            return csvFiles;
        } catch (error) {
            console.log("Error reading directory");
            return [];
        }
    }
}

export { CSVManager };