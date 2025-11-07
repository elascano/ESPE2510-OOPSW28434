const readline = require('readline');
const ChickenCoop = require('./model/ChickenCoop');
const Chicken = require('./model/Chicken');
const JSONFileManager = require('./model/JSONFileManager');

class ChickenFarmSimulator {
    constructor() {
        this.coops = [];
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
    }

    async main() {
        console.log("Welcome to the Chicken Farm Simulator");
        
        this.coops = JSONFileManager.loadFromFile();
        
        await this.showMainMenu();
    }

    async showMainMenu() {
        let option;
        do {
            console.log("\n=== CHICKEN FARM MAIN MENU ===");
            console.log("1. Create Chicken Coop");
            console.log("2. Add Chicken to Coop");
            console.log("3. List All Coops");
            console.log("4. List Chickens in Coop");
            console.log("5. Remove Chicken from Coop");
            console.log("6. Make Chickens Do Stuff");
            console.log("7. Remove Coop");
            console.log("=== JSON FILE OPERATIONS ===");
            console.log("8. Save Data to JSON File");
            console.log("9. Load Data from JSON File");
            console.log("10. Display JSON File Content");
            console.log("11. Update Chicken Data");
            console.log("12. Delete Chicken from JSON");
            console.log("0. Exit");
            
            try {
                option = await this.question("Select option: ");
                option = parseInt(option);
                
                switch (option) {
                    case 1:
                        await this.createChickenCoop();
                        break;
                    case 2:
                        await this.addChickenToCoop();
                        break;
                    case 3:
                        this.listAllCoops();
                        break;
                    case 4:
                        await this.listChickensInCoop();
                        break;
                    case 5:
                        await this.removeChickenFromCoop();
                        break;
                    case 6:
                        await this.makeChickensDoStuff();
                        break;
                    case 7:
                        await this.removeCoop();
                        break;
                    case 8:
                        JSONFileManager.saveToFile(this.coops);
                        break;
                    case 9:
                        this.coops = JSONFileManager.loadFromFile();
                        break;
                    case 10:
                        JSONFileManager.displayFileData();
                        break;
                    case 11:
                        await this.updateChickenData();
                        break;
                    case 12:
                        await this.deleteChickenFromJSON();
                        break;
                    case 0:
                        JSONFileManager.saveToFile(this.coops);
                        console.log("Goodbye! Thanks for using Chicken Farm Simulator!");
                        break;
                    default:
                        console.log("Invalid option! Please select a number between 0 and 12.");
                        option = -1;
                }
            } catch (e) {
                console.log("Error: Only numbers are allowed. Please enter a valid number.");
                option = -1;
            }
        } while (option !== 0);
        
        this.rl.close();
    }

    async createChickenCoop() {
        console.log("\n--- CREATE CHICKEN COOP ---");
        
        let coopId;
        while (true) {
            try {
                coopId = await this.question("Enter Coop ID: ");
                coopId = parseInt(coopId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Coop ID.");
            }
        }

        for (const coop of this.coops) {
            if (coop.getId() === coopId) {
                console.log(`Coop with ID ${coopId} already exists!`);
                return;
            }
        }

        const description = await this.question("Enter Coop description: ");

        const newCoop = new ChickenCoop(coopId, description);
        this.coops.push(newCoop);
        console.log("Coop created successfully!");
    }

    async addChickenToCoop() {
        if (this.coops.length === 0) {
            console.log("No coops available. Please create a coop first.");
            return;
        }
        
        console.log("\n--- ADD CHICKEN TO COOP ---");
        
        console.log("Available coops:");
        for (const coop of this.coops) {
            console.log(`Coop ID: ${coop.getId()} - ${coop.getDescription()}`);
        }
        
        let coopId;
        while (true) {
            try {
                coopId = await this.question("Enter Coop ID: ");
                coopId = parseInt(coopId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Coop ID.");
            }
        }
        
        const selectedCoop = this.findCoopById(coopId);
        if (!selectedCoop) {
            console.log("Coop not found!");
            return;
        }
        
        console.log("\n--- Enter chicken data ---");
        
        let id;
        while (true) {
            try {
                id = await this.question("Chicken ID: ");
                id = parseInt(id);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Chicken ID.");
            }
        }
        
        const name = await this.question("Name: ");
        const color = await this.question("Color: ");
        
        let age;
        while (true) {
            try {
                age = await this.question("Age: ");
                age = parseInt(age);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Age.");
            }
        }
        
        let isMolting = false;
        while (true) {
            try {
                const moltingInput = await this.question("Is molting? (1 for true / 0 for false): ");
                const moltingValue = parseInt(moltingInput);
                
                if (moltingValue === 1) {
                    isMolting = true;
                    break;
                } else if (moltingValue === 0) {
                    isMolting = false;
                    break;
                } else {
                    console.log("Error: Only 1 (true) or 0 (false) are allowed for molting status.");
                }
            } catch (e) {
                console.log("Error: Only numbers are allowed. Please enter 1 for true or 0 for false.");
            }
        }
        
        const chicken = new Chicken(id, name, color, age, isMolting);
        selectedCoop.addChicken(chicken);
        console.log("----Chicken added successfully------");
    }

    listAllCoops() {
        console.log("\n--- ALL COOPS ---");
        if (this.coops.length === 0) {
            console.log("No coops available.");
            return;
        }
        
        for (const coop of this.coops) {
            console.log(coop.toString());
        }
    }

    async listChickensInCoop() {
        if (this.coops.length === 0) {
            console.log("No coops available.");
            return;
        }
        
        let coopId;
        while (true) {
            try {
                coopId = await this.question("Enter Coop ID to list chickens: ");
                coopId = parseInt(coopId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Coop ID.");
            }
        }
        
        const selectedCoop = this.findCoopById(coopId);
        if (selectedCoop) {
            selectedCoop.listChickens();
        } else {
            console.log("Coop not found!");
        }
    }

    async removeChickenFromCoop() {
        if (this.coops.length === 0) {
            console.log("No coops available.");
            return;
        }
        
        let coopId;
        while (true) {
            try {
                coopId = await this.question("Enter Coop ID: ");
                coopId = parseInt(coopId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Coop ID.");
            }
        }
        
        const selectedCoop = this.findCoopById(coopId);
        if (!selectedCoop) {
            console.log("Coop not found!");
            return;
        }
        
        let chickenId;
        while (true) {
            try {
                chickenId = await this.question("Enter Chicken ID to remove: ");
                chickenId = parseInt(chickenId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Chicken ID.");
            }
        }
        
        selectedCoop.removeChicken(chickenId);
    }

    async makeChickensDoStuff() {
        if (this.coops.length === 0) {
            console.log("No coops available.");
            return;
        }
        
        console.log("\n--- MAKE CHICKENS DO STUFF ---");
        console.log("1. Make specific chicken do stuff");
        console.log("2. Make all chickens in coop do stuff");
        
        let option;
        while (true) {
            try {
                option = await this.question("Select option: ");
                option = parseInt(option);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed.");
            }
        }
        
        switch (option) {
            case 1:
                await this.makeSpecificChickenDoStuff();
                break;
            case 2:
                await this.makeAllChickensInCoopDoStuff();
                break;
            default:
                console.log("Invalid option! Please select 1 or 2.");
        }
    }

    async makeSpecificChickenDoStuff() {
        let coopId;
        while (true) {
            try {
                coopId = await this.question("Enter Coop ID: ");
                coopId = parseInt(coopId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Coop ID.");
            }
        }
        
        let chickenId;
        while (true) {
            try {
                chickenId = await this.question("Enter Chicken ID: ");
                chickenId = parseInt(chickenId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Chicken ID.");
            }
        }
        
        const selectedCoop = this.findCoopById(coopId);
        if (selectedCoop) {
            const chicken = selectedCoop.findChickenById(chickenId);
            if (chicken) {
                console.log(`\n--- ${chicken.getName()} IS DOING STUFF ---`);
                chicken.doStuff();
            } else {
                console.log("Chicken not found!");
            }
        } else {
            console.log("Coop not found!");
        }
    }

    async makeAllChickensInCoopDoStuff() {
        let coopId;
        while (true) {
            try {
                coopId = await this.question("Enter Coop ID: ");
                coopId = parseInt(coopId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Coop ID.");
            }
        }
        
        const selectedCoop = this.findCoopById(coopId);
        if (selectedCoop) {
            selectedCoop.makeAllDoStuff();
        } else {
            console.log("Coop not found!");
        }
    }

    async removeCoop() {
        if (this.coops.length === 0) {
            console.log("No coops available.");
            return;
        }
        
        let coopId;
        while (true) {
            try {
                coopId = await this.question("Enter Coop ID to remove: ");
                coopId = parseInt(coopId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Coop ID.");
            }
        }
        
        const coopToRemove = this.findCoopById(coopId);
        if (coopToRemove) {
            const index = this.coops.indexOf(coopToRemove);
            if (index !== -1) {
                this.coops.splice(index, 1);
                console.log("Coop removed successfully!");
            }
        } else {
            console.log("Coop not found!");
        }
    }

    async updateChickenData() {
        if (this.coops.length === 0) {
            console.log("No coops available. Please create a coop and add chickens first.");
            return;
        }
        
        let hasChickens = false;
        for (const coop of this.coops) {
            if (coop.getChickenCount() > 0) {
                hasChickens = true;
                break;
            }
        }
        
        if (!hasChickens) {
            console.log("No chickens available. Please add chickens first.");
            return;
        }
        
        console.log("\n--- UPDATE CHICKEN DATA ---");
        
        console.log("Available chickens:");
        for (const coop of this.coops) {
            if (coop.getChickenCount() > 0) {
                console.log(`Coop ${coop.getId()} - ${coop.getDescription()}:`);
                coop.listChickens();
            }
        }
        
        let chickenId;
        while (true) {
            try {
                chickenId = await this.question("Enter Chicken ID to update: ");
                chickenId = parseInt(chickenId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Chicken ID.");
            }
        }
        
        const newName = await this.question("Enter new name: ");
        const newColor = await this.question("Enter new color: ");
        
        let newAge;
        while (true) {
            try {
                newAge = await this.question("Enter new age: ");
                newAge = parseInt(newAge);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Age.");
            }
        }
        
        let newMoltingStatus = false;
        while (true) {
            try {
                const moltingInput = await this.question("Is molting? (1 for true / 0 for false): ");
                const moltingValue = parseInt(moltingInput);
                
                if (moltingValue === 1) {
                    newMoltingStatus = true;
                    break;
                } else if (moltingValue === 0) {
                    newMoltingStatus = false;
                    break;
                } else {
                    console.log("Error: Only 1 (true) or 0 (false) are allowed for molting status.");
                }
            } catch (e) {
                console.log("Error: Only numbers are allowed. Please enter 1 for true or 0 for false.");
            }
        }
        
        JSONFileManager.updateChickenData(this.coops, chickenId, newName, newColor, newAge, newMoltingStatus);
    }

    async deleteChickenFromJSON() {
        if (this.coops.length === 0) {
            console.log("No coops available. Please create a coop and add chickens first.");
            return;
        }
        
        let hasChickens = false;
        for (const coop of this.coops) {
            if (coop.getChickenCount() > 0) {
                hasChickens = true;
                break;
            }
        }
        
        if (!hasChickens) {
            console.log("No chickens available. Please add chickens first.");
            return;
        }
        
        console.log("\n--- DELETE CHICKEN FROM JSON ---");
        
        console.log("Available chickens:");
        for (const coop of this.coops) {
            if (coop.getChickenCount() > 0) {
                console.log(`Coop ${coop.getId()} - ${coop.getDescription()}:`);
                coop.listChickens();
            }
        }
        
        let coopId;
        while (true) {
            try {
                coopId = await this.question("Enter Coop ID: ");
                coopId = parseInt(coopId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Coop ID.");
            }
        }
        
        let chickenId;
        while (true) {
            try {
                chickenId = await this.question("Enter Chicken ID to delete: ");
                chickenId = parseInt(chickenId);
                break;
            } catch (e) {
                console.log("Error: Only numbers are allowed for Chicken ID.");
            }
        }
        
        JSONFileManager.deleteChickenFromFile(this.coops, coopId, chickenId);
    }

    findCoopById(coopId) {
        for (const coop of this.coops) {
            if (coop.getId() === coopId) {
                return coop;
            }
        }
        return null;
    }

    question(prompt) {
        return new Promise((resolve) => {
            this.rl.question(prompt, resolve);
        });
    }
}

// Ejecutar la aplicación
if (require.main === module) {
    const simulator = new ChickenFarmSimulator();
    simulator.main().catch(console.error);
}

module.exports = ChickenFarmSimulator;