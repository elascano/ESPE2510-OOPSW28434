const prompt = require('prompt-sync');
const { Chicken } = require('../Model/Chicken.js');
const { ChickenCoop } = require('../Model/ChickenCoop.js');

class ChickenFarmSimulatorView {
    constructor() {
        this.coop = new ChickenCoop();
        this.prompt = prompt({ sigint: true });
    }

    showMenu() {
        console.log("\n Welcome to my Chicken Farm Simulator ");
        console.log("-----------------------------------------");
        console.log("1. Add Chicken");
        console.log("2. Visualize Chickens");
        console.log("3. Find Chicken by ID");
        console.log("4. Edit Chicken");
        console.log("5. Delete Chicken by ID");
        console.log("6. Exit");
        console.log("-----------------------------------------");
    }

    askChickensNumber() {
        while (true) {
            const amount = parseInt(this.prompt("How many chickens do you want to add? "));
            if (!isNaN(amount) && amount > 0) return amount;
            console.log("Please enter a valid number.");
        }
    }

    askChickenInformation() {
        console.log("\n New Chicken Information");
        const name = this.prompt("Name: ");
        const color = this.prompt("Color: ");
        
        let age;
        while (true) {
            age = parseInt(this.prompt("Age in years: "));
            if (!isNaN(age) && age >= 0) break;
            console.log("Please enter a valid number for age.");
        }

        let isMolting;
        while (true) {
            const isMoltingInput = this.prompt("Is molting? (y/n): ").toLowerCase();
            if (isMoltingInput === 'y' || isMoltingInput === 'n') {
                isMolting = isMoltingInput === 'y';
                break;
            }
            console.log("Please enter 'y' for Yes or 'n' for No.");
        }
        return { name, color, age, isMolting };
    }

    addChicken() {
        const amount = this.askChickensNumber();
        for (let i = 0; i < amount; i++) {
            const { name, color, age, isMolting } = this.askChickenInformation(); 
            const chicken = new Chicken(name, color, age, isMolting);
            this.coop.addChicken(chicken);
            console.log(`Chicken '${chicken.name}' added with ID: ${chicken.id}`);
        }
        console.log("Data saved successfully.");
    }

    showChicken(chickenList) {
        if (!chickenList || chickenList.length === 0) {
            console.log("No chickens to show.");
            return;
        }

        const header = "| ID | Name     | Color    | Age | Molting |";
        const separator = "+----+----------+----------+-----+---------+";
        console.log(separator);
        console.log(header);
        console.log(separator);
        
        for (const chicken of chickenList) {
            const moltingStatus = chicken.isMolting ? "Yes" : "No";
            const line = `| ${chicken.id.padEnd(2)} | ${chicken.name.padEnd(8)} | ${chicken.color.padEnd(8)} | ${String(chicken.age).padEnd(3)} | ${moltingStatus.padEnd(7)} |`;
            console.log(line);
        }
        console.log(separator);
    }

    visualizeAllChickens() {
        this.coop.loadFromStorage();
        this.showChicken(this.coop.getChickens());
    }

    findChicken() {
        this.coop.loadFromStorage();
        const chickenId = this.prompt("Enter the ID of the chicken to find: ");
        const chickenFound = this.coop.findChickenById(chickenId);
        if (chickenFound) this.showChicken([chickenFound]);
        else console.log(`Error: Chicken with ID ${chickenId} not found.`);
    }

    editChicken() {
        this.coop.loadFromStorage();
        const chickenId = this.prompt("Enter the ID of the chicken to edit: ");
        const chicken = this.coop.findChickenById(chickenId);
        if (!chicken) { console.log(`Error: Chicken with ID ${chickenId} not found.`); return; }

        console.log("Enter the new information:");
        const newInfo = this.askChickenInformation();
        const success = this.coop.editChicken(chickenId, newInfo.name, newInfo.color, newInfo.age, newInfo.isMolting);

        if (success) console.log(`Success: Chicken ID ${chickenId} updated.`);
        else console.log("Error: Could not update chicken.");
    }

    deleteChicken() {
        this.coop.loadFromStorage();
        const chickenId = this.prompt("Enter the ID of the chicken to delete: ");
        const chicken = this.coop.findChickenById(chickenId);
        if (!chicken) { console.log(`Error: Chicken with ID ${chickenId} not found.`); return; }

        const confirmDelete = this.prompt(`Are you sure you want to delete '${chicken.name}'? (y/n): `).toLowerCase();
        if (confirmDelete === 'y') {
            const success = this.coop.deleteChicken(chickenId);
            if (success) console.log(`Chicken ID ${chickenId} deleted successfully.`);
            else console.log("Error: Could not delete chicken.");
        } else {
            console.log("Deletion canceled.");
        }
    }

    run() {
        while (true) {
            this.showMenu();
            const option = this.prompt("Choose an option: ");
            switch(option) {
                case "1": this.addChicken(); break;
                case "2": this.visualizeAllChickens(); break;
                case "3": this.findChicken(); break;
                case "4": this.editChicken(); break;
                case "5": this.deleteChicken(); break;
                case "6": console.log("Goodbye!"); return;
                default: console.log("Invalid option, please try again.");
            }
        }
    }
}

module.exports = { ChickenFarmSimulatorView };
