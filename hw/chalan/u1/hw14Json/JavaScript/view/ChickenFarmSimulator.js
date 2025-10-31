import fs from "fs";
import readlineSync from "readline-sync";
import Farmer from "../Model/Farmer.js";
import ChickenCoop from "../Model/ChickenCoop.js";
import Chicken from "../Model/Chicken.js";

const DATA_DIR = "farmChickenData";
const FARMERS_FILE = `${DATA_DIR}/farmers.json`;
const COOPS_FILE = `${DATA_DIR}/coops.json`;
const CHICKENS_FILE = `${DATA_DIR}/chickens.json`;

class ChickenFarmSimulator {
    constructor() {
        this.farmers = [];
        this.coops = [];
        this.currentFarmer = null;
        
        // Crear directorio si no existe
        if (!fs.existsSync(DATA_DIR)) {
            fs.mkdirSync(DATA_DIR);
        }
        
        this.loadData();
    }

    loadData() {
        try {
            // Cargar granjeros
            if (fs.existsSync(FARMERS_FILE)) {
                const farmersData = JSON.parse(fs.readFileSync(FARMERS_FILE));
                this.farmers = farmersData.map(data => Farmer.fromJSON(data));
            }

            // Cargar gallineros
            if (fs.existsSync(COOPS_FILE)) {
                const coopsData = JSON.parse(fs.readFileSync(COOPS_FILE));
                this.coops = coopsData.map(data => ChickenCoop.fromJSON(data));
            }

            console.log(`Loaded ${this.farmers.length} farmers and ${this.coops.length} coops`);
        } catch (error) {
            console.log("No existing data found or error loading data.");
        }
    }

    saveData() {
        try {
            fs.writeFileSync(FARMERS_FILE, JSON.stringify(this.farmers.map(f => f.toJSON()), null, 2));
            fs.writeFileSync(COOPS_FILE, JSON.stringify(this.coops.map(c => c.toJSON()), null, 2));
            console.log("Data saved successfully!");
        } catch (error) {
            console.log("Error saving data:", error);
        }
    }

    mainMenu() {
        console.log("\n- - - Kevin Chalan's Chicken Farm Simulator - - -");

        let option = 0;
        do {
            console.log("\n=== MAIN MENU ===");
            console.log("1. Farmer Management");
            console.log("2. Chicken Coop Management");
            console.log("3. Chicken Management");
            console.log("4. Exit");

            option = readlineSync.questionInt("Choose an option: ");

            switch (option) {
                case 1:
                    this.farmerManagementMenu();
                    break;
                case 2:
                    this.coopManagementMenu();
                    break;
                case 3:
                    this.chickenManagementMenu();
                    break;
                case 4:
                    console.log("Goodbye!");
                    break;
                default:
                    console.log("Invalid option.");
            }
        } while (option !== 4);
    }

    farmerManagementMenu() {
        let option = 0;
        do {
            console.log("\n--- FARMER MANAGEMENT ---");
            console.log("1. Create new farmer");
            console.log("2. Select current farmer");
            console.log("3. View all farmers");
            console.log("4. Back to main menu");

            option = readlineSync.questionInt("Choose an option: ");

            switch (option) {
                case 1:
                    this.createFarmer();
                    break;
                case 2:
                    this.selectFarmer();
                    break;
                case 3:
                    this.viewFarmers();
                    break;
                case 4:
                    break;
                default:
                    console.log("Invalid option.");
            }
        } while (option !== 4);
    }

    createFarmer() {
        console.log("\n--- CREATE NEW FARMER ---");
        const id = readlineSync.questionInt("Farmer ID: ");
        const name = readlineSync.question("Farmer Name: ");

        if (this.farmers.find(f => f.getId() === id)) {
            console.log("Farmer ID already exists!");
            return;
        }

        const farmer = new Farmer(id, name);
        this.farmers.push(farmer);
        console.log(`Farmer '${name}' created successfully!`);
        this.saveData();
    }

    selectFarmer() {
        if (this.farmers.length === 0) {
            console.log("No farmers available. Create one first.");
            return;
        }

        console.log("\n--- SELECT FARMER ---");
        this.farmers.forEach(farmer => console.log(farmer.toString()));

        const farmerId = readlineSync.questionInt("Enter Farmer ID to select: ");
        this.currentFarmer = this.farmers.find(f => f.getId() === farmerId);

        if (this.currentFarmer) {
            console.log(`Current farmer: ${this.currentFarmer.getName()}`);
        } else {
            console.log("Farmer not found!");
        }
    }

    viewFarmers() {
        if (this.farmers.length === 0) {
            console.log("No farmers available.");
            return;
        }

        console.log("\n--- ALL FARMERS ---");
        this.farmers.forEach(farmer => console.log(farmer.toString()));
    }

    coopManagementMenu() {
        if (!this.currentFarmer) {
            console.log("Please select a farmer first!");
            return;
        }

        let option = 0;
        do {
            console.log(`\n--- COOP MANAGEMENT - Farmer: ${this.currentFarmer.getName()} ---`);
            console.log("1. Add chicken coop");
            console.log("2. View my coops");
            console.log("3. Back to main menu");

            option = readlineSync.questionInt("Choose an option: ");

            switch (option) {
                case 1:
                    this.addChickenCoop();
                    break;
                case 2:
                    this.viewMyCoops();
                    break;
                case 3:
                    break;
                default:
                    console.log("Invalid option.");
            }
        } while (option !== 3);
    }

    addChickenCoop() {
        console.log("\n--- ADD CHICKEN COOP ---");
        const coopId = readlineSync.questionInt("Coop ID: ");

        if (this.coops.find(c => c.getId() === coopId)) {
            console.log("Coop ID already exists!");
            return;
        }

        const coop = new ChickenCoop(coopId, this.currentFarmer.getId());
        this.coops.push(coop);
        this.currentFarmer.addCoop(coopId);
        console.log(`Coop ${coopId} added successfully!`);
        this.saveData();
    }

    viewMyCoops() {
        const myCoops = this.coops.filter(coop => coop.getFarmerId() === this.currentFarmer.getId());

        if (myCoops.length === 0) {
            console.log("You don't have any coops yet.");
            return;
        }

        console.log(`\n--- MY COOPS - ${this.currentFarmer.getName()} ---`);
        myCoops.forEach(coop => {
            console.log(coop.toString());
            console.log("-".repeat(40));
        });
    }

    chickenManagementMenu() {
        if (!this.currentFarmer) {
            console.log("Please select a farmer first!");
            return;
        }

        let option = 0;
        do {
            console.log(`\n--- CHICKEN MANAGEMENT - Farmer: ${this.currentFarmer.getName()} ---`);
            console.log("1. Add chicken to coop");
            console.log("2. Make chicken do stuff");
            console.log("3. Back to main menu");

            option = readlineSync.questionInt("Choose an option: ");

            switch (option) {
                case 1:
                    this.addChickenToCoop();
                    break;
                case 2:
                    this.makeChickenDoStuff();
                    break;
                case 3:
                    break;
                default:
                    console.log("Invalid option.");
            }
        } while (option !== 3);
    }

    addChickenToCoop() {
        const myCoops = this.coops.filter(coop => coop.getFarmerId() === this.currentFarmer.getId());

        if (myCoops.length === 0) {
            console.log("You don't have any coops. Create one first.");
            return;
        }

        console.log("\n--- ADD CHICKEN TO COOP ---");
        console.log("Your coops:");
        myCoops.forEach(coop => console.log(`- Coop ID: ${coop.getId()} (${coop.getChickens().length} chickens)`));

        const coopId = readlineSync.questionInt("Enter coop ID: ");
        const selectedCoop = myCoops.find(coop => coop.getId() === coopId);

        if (!selectedCoop) {
            console.log("Coop not found or you don't own it!");
            return;
        }

        console.log("\n--- NEW CHICKEN DETAILS ---");
        const id = readlineSync.questionInt("Chicken ID: ");
        const name = readlineSync.question("Name: ");
        const color = readlineSync.question("Color: ");
        const age = readlineSync.questionInt("Age: ");
        const molting = readlineSync.keyInYN("Is the chicken molting?");

        if (selectedCoop.getChickens().find(ch => ch.getId() === id)) {
            console.log("Chicken ID already exists in this coop!");
            return;
        }

        const chicken = new Chicken(id, name, color, age, molting);
        selectedCoop.addChicken(chicken);
        console.log(`Chicken '${name}' added to coop ${coopId}!`);
        this.saveData();
    }

    makeChickenDoStuff() {
        const myCoops = this.coops.filter(coop => coop.getFarmerId() === this.currentFarmer.getId());

        if (myCoops.length === 0) {
            console.log("You don't have any coops.");
            return;
        }

        console.log("\n--- MAKE CHICKEN DO STUFF ---");
        console.log("Your coops:");
        myCoops.forEach(coop => console.log(`- Coop ID: ${coop.getId()} (${coop.getChickens().length} chickens)`));

        const coopId = readlineSync.questionInt("Enter coop ID: ");
        const selectedCoop = myCoops.find(coop => coop.getId() === coopId);

        if (!selectedCoop || selectedCoop.getChickens().length === 0) {
            console.log("No chickens in this coop!");
            return;
        }

        console.log("\nChickens in this coop:");
        selectedCoop.getChickens().forEach(chicken => console.log(chicken.toString()));

        const chickenId = readlineSync.questionInt("Enter chicken ID: ");
        const selectedChicken = selectedCoop.getChickens().find(ch => ch.getId() === chickenId);

        if (selectedChicken) {
            console.log(`\n--- ${selectedChicken.getName()} IS DOING STUFF ---`);
            selectedChicken.doStuff();
        } else {
            console.log("Chicken not found!");
        }
    }
}

export default ChickenFarmSimulator;