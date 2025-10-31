const fs = require('fs');
const path = require('path');

class JSONFileManager {
    static FILE_NAME = "chicken_farm_data.json";
    
    static saveToFile(coops) {
        try {
            const data = {
                coops: coops.map(coop => ({
                    id: coop.getId(),
                    description: coop.getDescription(),
                    chickens: coop.getChickens().map(chicken => ({
                        id: chicken.getId(),
                        name: chicken.getName(),
                        color: chicken.getColor(),
                        age: chicken.getAge(),
                        isMolting: chicken.isMolting, // Propiedad, no método
                        coopId: coop.getId()
                    }))
                }))
            };
            
            fs.writeFileSync(this.FILE_NAME, JSON.stringify(data, null, 2));
            console.log(`Data saved successfully to ${this.FILE_NAME}`);
            return true;
           
        } catch (e) {
            console.log(`Error saving data: ${e.message}`);
            return false;
        }
    }
    
    static loadFromFile() {
        try {
            if (!fs.existsSync(this.FILE_NAME)) {
                console.log("No existing data file found. Starting with empty farm.");
                return [];
            }
            
            const fileContent = fs.readFileSync(this.FILE_NAME, 'utf8');
            const data = JSON.parse(fileContent);
            
            console.log(`Data loaded successfully from ${this.FILE_NAME}`);
            return this.convertFromJSON(data);
            
        } catch (e) {
            console.log(`Error loading data: ${e.message}`);
            return [];
        }
    }
    
    static convertFromJSON(data) {
        const ChickenCoop = require('./ChickenCoop');
        const Chicken = require('./Chicken');
        const coops = [];
        
        if (!data.coops || !Array.isArray(data.coops)) {
            return coops;
        }
        
        data.coops.forEach(coopData => {
            const coop = new ChickenCoop(coopData.id, coopData.description);
            
            if (coopData.chickens && Array.isArray(coopData.chickens)) {
                coopData.chickens.forEach(chickenData => {
                    try {
                        // CORREGIDO: Crear instancia de Chicken correctamente
                        const chicken = new Chicken(
                            chickenData.id,
                            chickenData.name,
                            chickenData.color,
                            chickenData.age,
                            chickenData.isMolting
                        );
                        coop.getChickens().push(chicken);
                    } catch (error) {
                        console.log(`Error creating chicken: ${error.message}`);
                    }
                });
            }
            
            coops.push(coop);
        });
        
        return coops;
    }
    
    static displayFileData() {
        try {
            if (!fs.existsSync(this.FILE_NAME)) {
                console.log("ℹNo data file found. Please save data first.");
                return;
            }
            
            const content = fs.readFileSync(this.FILE_NAME, 'utf8');
            console.log("\n=== JSON FILE DATA ===");
            console.log(content);
            
        } catch (e) {
            console.log(`Error displaying file data: ${e.message}`);
        }
    }
    
    static deleteFile() {
        try {
            if (fs.existsSync(this.FILE_NAME)) {
                fs.unlinkSync(this.FILE_NAME);
                console.log(`File ${this.FILE_NAME} deleted successfully.`);
            } else {
                console.log(`File ${this.FILE_NAME} does not exist.`);
            }
        } catch (e) {
            console.log(`Error deleting file: ${e.message}`);
        }
    }
    
    static updateChickenData(coops, chickenId, newName, newColor, newAge, newMoltingStatus) {
        try {
            let chickenFound = false;
            let foundCoop = null;
            
            for (const coop of coops) {
                const chickens = coop.getChickens();
                for (const chicken of chickens) {
                    if (chicken.getId() === chickenId) {
                        chicken.setName(newName);
                        chicken.setColor(newColor);
                        chicken.setAge(newAge);
                        chicken.setMolting(newMoltingStatus);
                        chickenFound = true;
                        foundCoop = coop;
                        break;
                    }
                }
                if (chickenFound) break;
            }
            
            if (chickenFound) {
                this.saveToFile(coops);
                console.log(`Chicken with ID ${chickenId} in Coop ${foundCoop.getId()} updated successfully.`);
            } else {
                console.log(`Chicken with ID ${chickenId} not found in any coop.`);
            }
            
        } catch (e) {
            console.log(`Error updating chicken data: ${e.message}`);
        }
    }
    
    static deleteChickenFromFile(coops, coopId, chickenId) {
        try {
            let chickenFound = false;
            let foundCoop = null;
            
            for (const coop of coops) {
                if (coop.getId() === coopId) {
                    foundCoop = coop;
                    const chickens = coop.getChickens();
                    for (let i = 0; i < chickens.length; i++) {
                        const chicken = chickens[i];
                        if (chicken.getId() === chickenId) {
                            chickens.splice(i, 1);
                            chickenFound = true;
                            break;
                        }
                    }
                    break;
                }
            }
            
            if (chickenFound) {
                this.saveToFile(coops);
                console.log(`Chicken with ID ${chickenId} deleted from Coop ${coopId} and JSON file updated successfully.`);
            } else {
                if (!foundCoop) {
                    console.log(`Coop with ID ${coopId} not found.`);
                } else {
                    console.log(`Chicken with ID ${chickenId} not found in Coop ${coopId}.`);
                }
            }
            
        } catch (e) {
            console.log(`Error deleting chicken from JSON: ${e.message}`);
        }
    }
    
    static fileExists() {
        return fs.existsSync(this.FILE_NAME);
    }
    
    // Métodos de escape/unescape para compatibilidad
    static escapeJson(str) {
        if (!str) return "";
        return str.replace(/\\/g, "\\\\")
                  .replace(/\"/g, "\\\"")
                  .replace(/\n/g, "\\n")
                  .replace(/\r/g, "\\r")
                  .replace(/\t/g, "\\t");
    }
    
    static unescapeJson(str) {
        if (!str) return "";
        return str.replace(/\\\"/g, "\"")
                  .replace(/\\\\/g, "\\")
                  .replace(/\\n/g, "\n")
                  .replace(/\\r/g, "\r")
                  .replace(/\\t/g, "\t");
    }
}

module.exports = JSONFileManager;