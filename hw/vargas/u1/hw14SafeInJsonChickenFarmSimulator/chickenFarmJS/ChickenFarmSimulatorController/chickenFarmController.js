import { Chicken } from "../ChickenFarmSimulatorModel/chicken.js";
import { ChickenCoop } from "../ChickenFarmSimulatorModel/chickenCoops.js";
import { ChickenSave } from "../ChickenFarmSimulatorModel/saveChickenInJson.js"; 
import * as readline from 'readline/promises';
import { stdin as input, stdout as output } from 'process';

const initialChickensData = [
    { id: '1', "name": "Lucy", color: "White and Brown", age: 2, isMolting: false },
    { id: '2', "name": "Maruja", color: "White", age: 1, isMolting: true },
    { id: '3',"name": "Lola", color: "White", age: 2, isMolting: true },
    { id: '4', "name": "Pepa", color: "Black", age: 1, isMolting: false },
    { id: '5', "name": "Gusepa", color: "Brown and white", age: 4, isMolting: false },
    { id: '6', "name": "Pancracia", color: "Gray", age: 2, isMolting: true },
    { id: '7', "name": "Federica", color: "Brown", age: 1, isMolting: false },
    { id: '8', "name": "Pancha", color: "White", age: 3, isMolting: false },
    { id: '9', "name": "Zoe", color: "Black", age: 2, isMolting: false },
    { id: '10', "name": "Lina", color: "Brown and white", age: 1, isMolting: true },
];

export class FarmController {
    #storage;
    #rl;
    #farmCoops;

    constructor(filename = 'chickenFarm.json') {
        this.#storage = new ChickenSave(filename); 
        this.#rl = readline.createInterface({ input, output });
        this.#farmCoops = []; 

        console.log("This is my Chicken Farm Simulator Controller");
    }

    #createCoopsFromData(loadedCoopData, chickenData) {
        ChickenCoop.allCoops = []; 

        if (loadedCoopData.length > 0) {
            const reconstructedCoops = loadedCoopData.map(coopData => {
                const coop = new ChickenCoop(coopData.name, coopData.id); 
                
                coopData.chickens.forEach(c => {
                    const chicken = new Chicken(c.id, c.name, c.color, c.age, c.isMolting);
                    coop.addChicken(chicken);
                });
                return coop;
            });
            return reconstructedCoops;
        }

        let coop1 = new ChickenCoop("Chicken Coop 1", 'COOP_3'); 
        let coop2 = new ChickenCoop("Chicken Coop 2", 'COOP_2'); 
        
        const allChickens = chickenData.map(c => new Chicken(c.id, c.name, c.color, c.age, c.isMolting));

        for (let i = 0; i < 7 && i < allChickens.length; i++) {
            coop1.addChicken(allChickens[i]); 
        }

        for (let i = 7; i < allChickens.length; i++) {
            coop2.addChicken(allChickens[i]);
        }
        
        return [coop1, coop2];
    }

    async initializeFarm() {
        console.log('Initializing farm data...');
        const loadedCoopData = await this.#storage.load();

        if (loadedCoopData.length === 0) {
            console.log("JSON file empty. Initializing with hardcoded data and saving...");
            
            this.#farmCoops = this.#createCoopsFromData([], initialChickensData);
            
            await this.#storage.save(this.#farmCoops);
            
        } else {
            this.#farmCoops = this.#createCoopsFromData(loadedCoopData, []);
            console.log("Farm data loaded successfully from JSON.");
        }
    }

    async mainMenu() {
        await this.initializeFarm();

        let exit = false;
        while (!exit) {
            console.log("\n---------------------------------------");
            console.log("  CHICKEN FARM MANAGEMENT MENU ");
            console.log("---------------------------------------");
            console.log("1. Add New Chicken");
            console.log("2. Display All Chickens");
            console.log("3. Edit Data (Chicken/Coop)");
            console.log("4. Delete Data (Chicken/Coop)");
            console.log("5. Exit");
            console.log("---------------------------------------");

            const choice = await this.#rl.question('Select an option: ');
            
            switch (choice.trim()) {
                case '1':
                    await this.#addNewChicken();
                    break;
                case '2':
                    await this.#listChickensFromJson();
                    break;
                case '3':
                    await this.#handleUpdateMenu();
                    break;
                case '4':
                    await this.#handleDeleteMenu();
                    break;
                case '5':
                    exit = true;
                    break;
                default:
                    console.log("Invalid option, please try again.");
            }
        }
        this.#rl.close();
        console.log("Simulator closed. Goodbye! ");
    }

    async #addNewChicken() {
        console.log("\n--- ADD NEW CHICKEN ---");
        const id = await this.#rl.question('Chicken ID (must be unique): ')
        const name = await this.#rl.question('Chicken name: ');
        const color = await this.#rl.question('Chicken color: ');
        let age = parseInt(await this.#rl.question('Chicken age: '));
        const isMolting = false; 

        if (isNaN(age) || name.trim() === '' || color.trim() === '' || id.trim() === '') {
            console.log("Error: Please enter valid data for ID, name, color, and age.");
            return;
        }

        const existingItem = await this.#storage.findCoopOrChickenById(id, 'chicken');
        if (existingItem) {
            console.log(`Error: A chicken with ID ${id} already exists.`);
            return;
        }

        const newChicken = new Chicken(id, name, color, age, isMolting);
        
        if (this.#farmCoops.length === 0) {
            console.log("Error: No coops initialized to add the chicken to.");
            return;
        }
            
        console.log("\n--- Select Chicken Coop ---");
        this.#farmCoops.forEach((coop, index) => {
            console.log(`${index + 1}. ${coop.getName()} (ID: ${coop.getId()})`);
        });
        console.log("---------------------------");

        const coopChoice = await this.#rl.question(`Enter the number of the coop (1-${this.#farmCoops.length}): `);
        const coopIndex = parseInt(coopChoice) - 1;

        if (isNaN(coopIndex) || coopIndex < 0 || coopIndex >= this.#farmCoops.length) {
            console.log("Error: Invalid coop selection.");
            return;
        }

        const selectedCoop = this.#farmCoops[coopIndex];
        selectedCoop.addChicken(newChicken); 
        
        await this.#storage.save(this.#farmCoops);

        console.log(`\nChicken "${name}" (ID: ${newChicken._id}) added and saved successfully to ${selectedCoop.getName()}!`);
    }

    async #listChickensFromJson() {
        console.log("\n--- STORED CHICKENS AND COOPS ---");
        const loadedCoopData = await this.#storage.load();

        let totalChickens = 0;
        
        if (loadedCoopData.length === 0) {
            console.log("No coop data registered in the JSON file.");
            return;
        }
        
        loadedCoopData.forEach(coopData => {
            console.log(`\nCoop ID: ${coopData.id}, Name: ${coopData.name}, Total Chickens: ${coopData.chickens.length}`);
            if (coopData.chickens.length > 0) {
                coopData.chickens.forEach(c => {
                    console.log(` [ID: ${c.id}] Name: ${c.name}, Color: ${c.color}, Age: ${c.age}, Molting: ${c.isMolting ? 'Yes' : 'No'}`);
                    totalChickens++;
                });
            } else {
                console.log(' (No chickens in this coop)');
            }
        });
        console.log(`\nTotal chickens found across all coops: ${totalChickens}`);
    }

    async #handleUpdateMenu() {
        console.log("\n--- EDIT DATA MENU ---");
        console.log("1. Edit Chicken");
        console.log("2. Edit Coop");
        console.log("3. Back to Main Menu");
        console.log("----------------------");

        const choice = await this.#rl.question('Select data type to edit: ');
        
        switch (choice.trim()) {
            case '1':
                await this.#editChicken();
                break;
            case '2':
                await this.#editCoop();
                break;
            case '3':
                break;
            default:
                console.log("Invalid option.");
        }
    }

    async #editChicken() {
        console.log("\n--- EDIT CHICKEN ---");
        await this.#listChickensFromJson();

        const id = await this.#rl.question('Enter the ID of the Chicken to edit: ');
        
        try {
            const foundData = await this.#storage.findCoopOrChickenById(id, 'chicken');
            if (!foundData) {
                console.log(`Error: Chicken with ID ${id} not found.`);
                return;
            }

            const currentChicken = foundData.chicken;
            console.log(`\nEditing Chicken: ${currentChicken.name} (ID: ${currentChicken.id})`);

            const newName = await this.#rl.question(`Enter new Name (Current: ${currentChicken.name}, leave blank to skip): `);
            const newColor = await this.#rl.question(`Enter new Color (Current: ${currentChicken.color}, leave blank to skip): `);
            const newAgeStr = await this.#rl.question(`Enter new Age (Current: ${currentChicken.age}, leave blank to skip): `);
            const newMoltingStr = await this.#rl.question(`Is it Molting? (Current: ${currentChicken.isMolting ? 'Y' : 'N'}, Enter Y/N, leave blank to skip): `);
            
            const updateData = {};
            if (newName.trim()) updateData.name = newName.trim();
            if (newColor.trim()) updateData.color = newColor.trim();
            if (newAgeStr.trim()) {
                const age = parseInt(newAgeStr.trim());
                if (isNaN(age)) {
                    console.log('Warning: Invalid age entered. Skipping age update.');
                } else {
                    updateData.age = age;
                }
            }
            if (newMoltingStr.trim()) {
                const moltingInput = newMoltingStr.trim().toUpperCase();
                if (moltingInput === 'Y' || moltingInput === 'YES') {
                    updateData.isMolting = true;
                } else if (moltingInput === 'N' || moltingInput === 'NO') {
                    updateData.isMolting = false;
                } else {
                    console.log('Warning: Invalid molting status entered. Skipping status update.');
                }
            }

            if (Object.keys(updateData).length === 0) {
                console.log('No changes specified. Aborting update.');
                return;
            }

            await this.#storage.updateData(id, 'chicken', updateData);
            
            const coopInstance = this.#farmCoops.find(c => c.getId() === foundData.coop.id);
            if(coopInstance) {
                const chickenInstance = coopInstance.getChickens().find(c => c._id === id);
                if(chickenInstance) {
                    Object.assign(chickenInstance, updateData);
                    console.log('Internal instance updated.');
                }
            }

        } catch (error) {
            console.error(`Error during chicken update: ${error.message}`);
        }
    }

    async #editCoop() {
        console.log("\n--- EDIT COOP ---");
        await this.#listChickensFromJson(); 
        
        const id = await this.#rl.question('Enter the ID of the Coop to edit: ');
        
        try {
            const currentCoop = await this.#storage.findCoopOrChickenById(id, 'coop');
            if (!currentCoop) {
                console.log(`Error: Coop with ID ${id} not found.`);
                return;
            }
            
            console.log(`\nEditing Coop: ${currentCoop.name} (ID: ${currentCoop.id})`);
            
            const newName = await this.#rl.question(`Enter new Name (Current: ${currentCoop.name}, leave blank to skip): `);
            
            const updateData = {};
            if (newName.trim()) updateData.name = newName.trim();
            
            if (Object.keys(updateData).length === 0) {
                console.log('No changes specified. Aborting update.');
                return;
            }

            await this.#storage.updateData(id, 'coop', updateData);
            
            const coopInstance = this.#farmCoops.find(c => c.getId() === id);
            if(coopInstance) {
                coopInstance.setName(updateData.name); 
                console.log('Internal instance updated.');
            }

        } catch (error) {
            console.error(`Error during coop update: ${error.message}`);
        }
    }


    async #handleDeleteMenu() {
        console.log("\n--- DELETE DATA MENU ---");
        console.log("1. Delete Chicken");
        console.log("2. Delete Coop");
        console.log("3. Back to Main Menu");
        console.log("----------------------");

        const choice = await this.#rl.question('Select data type to delete: ');
        
        switch (choice.trim()) {
            case '1':
                await this.#deleteChicken();
                break;
            case '2':
                await this.#deleteCoop();
                break;
            case '3':
                break;
            default:
                console.log("Invalid option.");
        }
    }

    async #deleteChicken() {
        console.log("\n--- DELETE CHICKEN ---");
        await this.#listChickensFromJson();

        const id = await this.#rl.question('Enter the ID of the Chicken to delete: ');
        if (!id.trim()) {
            console.log("ID cannot be empty.");
            return;
        }

        try {
            const foundData = await this.#storage.findCoopOrChickenById(id, 'chicken');
            
            const success = await this.#storage.deleteData(id, 'chicken');

            if (success && foundData) {
                const coopInstance = this.#farmCoops.find(c => c.getId() === foundData.coop.id);
                if(coopInstance) {
                    coopInstance.removeChickenById(id); 
                    console.log('Internal chicken instance deleted.');
                }
            }

        } catch (error) {
            console.error(`Error during chicken deletion: ${error.message}`);
        }
    }

    async #deleteCoop() {
        console.log("\n--- DELETE COOP ---");
        await this.#listChickensFromJson();
        
        const id = await this.#rl.question('Enter the ID of the Coop to delete (WARNING: This will delete ALL chickens inside!): ');
        if (!id.trim()) {
            console.log("ID cannot be empty.");
            return;
        }
        
        try {
            const coopInstance = this.#farmCoops.find(c => c.getId() === id);

            const success = await this.#storage.deleteData(id, 'coop');

            if (success) {
                if (coopInstance) {
                    this.#farmCoops = this.#farmCoops.filter(c => c.getId() !== id);
                    console.log('Internal coop instance deleted.');
                }
            }

        } catch (error) {
            console.error(`Error during coop deletion: ${error.message}`);
        }
    }
}