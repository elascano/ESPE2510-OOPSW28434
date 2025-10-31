const fs = require('fs');
const readline = require('readline/promises');
const path = require('path');
const ChickenCoop = require('../ChickenFarmModel/ChickenCoop');
const Chicken = require('../ChickenFarmModel/Chicken');

const FILE_NAME = "ChickenCoops_data.json";

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function save_data(coops) {
    try {
        const data_to_save = coops.map(c => c.to_dict());
        fs.writeFileSync(FILE_NAME, JSON.stringify(data_to_save, null, 4));
    } catch (e) {
        console.error(`Error saving data: ${e.message}`);
    }
}

function load_data() {
    let coops = [];
    
    try {
        const data_raw = fs.readFileSync(FILE_NAME, 'utf8');
        const data_list = JSON.parse(data_raw);
        
        for (const data of data_list) {
            const coop = new ChickenCoop(data.id, data.name);
            for (const c of data.chickens) {
                const chicken = new Chicken(c.id, c.name, c.color, c.is_molting, c.age);
                coop.add_chicken(chicken, true);
            }
            coops.push(coop);
        }
    } catch (e) {
        if (e.code === 'ENOENT') {
            const coop_A = new ChickenCoop(1, "Coop A");
            const coop_B = new ChickenCoop(2, "Coop B");
            
            const chickens_to_add = [
                new Chicken(1, "Pepita", "Brown", false, 1),
                new Chicken(2, "Maria", "Black", true, 3),
                new Chicken(3, "Coco", "White", false, 2),
                new Chicken(4, "Lili", "Black", false, 1),
                new Chicken(5, "Juana", "White", true, 4)
            ];

            chickens_to_add.forEach(chicken => coop_A.add_chicken(chicken, true));
                
            coops.push(coop_A, coop_B);
            save_data(coops);
        } else {
            console.error(`Error loading data: ${e.message}`);
        }
    }
    return coops;
}

class ChickenFarmSimulator {
    constructor() {
        this.coops = load_data();
    }

    _get_coop_by_name(name) {
        return this.coops.find(c => c.name.toLowerCase() === name.toLowerCase());
    }
    async add_chicken() {
        const coop_name = await rl.question("Enter the name of the coop to add the chicken to (e.g., Coop A): ");
        const target_coop = this._get_coop_by_name(coop_name);

        if (!target_coop) {
            console.log(`Error: Coop '${coop_name}' not found.`);
            return;
        }

        try {
            const chicken_id_str = await rl.question("Enter chicken ID: ");
            const chicken_id = parseInt(chicken_id_str);

            if (isNaN(chicken_id)) throw new Error("ID must be a number.");
            
            if (target_coop.chickens.some(c => c.id === chicken_id)) {
                console.log(`Error: Chicken ID ${chicken_id} already exists in ${coop_name}. Please use a unique ID.`);
                return;
            }
            
            const name = await rl.question("Enter chicken name: ");
            const color = await rl.question("Enter chicken color: ");
            const age_str = await rl.question("Enter chicken age: ");
            const age = parseInt(age_str);

            if (isNaN(age)) throw new Error("Age must be a number.");

            const is_molting_answer = await rl.question("Is the chicken molting? (yes/no): ");
            const is_molting = is_molting_answer.toLowerCase() === "yes";
            
            const chicken = new Chicken(chicken_id, name, color, is_molting, age);
            
            target_coop.add_chicken(chicken);
            save_data(this.coops);
            
        } catch (error) {
            console.log(`Invalid input: ${error.message}. Try again.`);
        }
    }
    show_chickens() {
        if (this.coops.length === 0) {
            console.log("No coops available in the farm.");
            return;
        }

        console.log("\n--- FARM OVERVIEW ---");
        this.coops.forEach(coop => coop.show_coop());
        console.log("---------------------------------");
    }

    async update_chicken() {
        const coop_name = await rl.question("Enter the name of the coop where the chicken is located: ");
        const target_coop = this._get_coop_by_name(coop_name);

        if (!target_coop) {
            console.log(`Error: Coop '${coop_name}' not found.`);
            return;
        }
            
        const name = await rl.question("Enter the name of the chicken to update: ");
        const chicken = target_coop.get_chicken_by_name(name);
        
        if (!chicken) {
            console.log("Chicken not found in the specified coop.");
            return;
        }
        
        console.log(`--- Updating Chicken: ${chicken.name} in ${coop_name} ---`);
        
        const new_color = await rl.question(`New color (current: ${chicken.color}, leave empty to skip): `) || null;
        const new_age_str = await rl.question(`New age (current: ${chicken.age}, leave empty to skip): `);
        const new_molting_str = await rl.question(`Is molting? (current: ${chicken.is_molting ? 'yes' : 'no'}, enter yes/no/empty): `);
        
        let new_age = null;
        if (new_age_str.length > 0) {
            new_age = parseInt(new_age_str);
            if (isNaN(new_age)) {
                console.log("Invalid age input. Update canceled.");
                return;
            }
        }
        
        let new_molting = null;
        if (new_molting_str.toLowerCase() === "yes") {
            new_molting = true;
        } else if (new_molting_str.toLowerCase() === "no") {
            new_molting = false;
        }
            
        if (target_coop.update_chicken(name, new_color, new_age, new_molting)) {
            save_data(this.coops);
        } else {
            console.log("No changes were made or an error occurred.");
        }
    }

    async remove_chicken() {
        const coop_name = await rl.question("Enter the name of the coop to remove the chicken from: ");
        const target_coop = this._get_coop_by_name(coop_name);

        if (!target_coop) {
            console.log(`Error: Coop '${coop_name}' not found.`);
            return;
        }

        if (target_coop.chickens.length === 0) {
            console.log(`Coop '${coop_name}' has no chickens to remove.`);
            return;
        }

        const name = await rl.question("Enter the name of the chicken to remove: ");
        
        if (target_coop.remove_chicken(name)) {
            save_data(this.coops);
        } else {
            console.log("Chicken not found in the specified coop.");
        }
    }

    async chicken_action() {
        const coop_name = await rl.question("Enter the name of the coop where the chicken is located: ");
        const target_coop = this._get_coop_by_name(coop_name);

        if (!target_coop) {
            console.log(`Error: Coop '${coop_name}' not found.`);
            return;
        }

        if (target_coop.chickens.length === 0) {
            console.log(`Coop '${coop_name}' has no chickens for actions.`);
            return;
        }

        const name = await rl.question("Enter the name of the chicken: ");
        const chicken = target_coop.get_chicken_by_name(name);

        if (!chicken) {
            console.log("Chicken not found in the specified coop.");
            return;
        }

        console.log("\n1. Cluck");
        console.log("2. Eat");
        console.log("3. Lay egg");
        console.log("4. Poop");
        const choice = await rl.question("Choose an action: ");

        switch (choice) {
            case '1':
                chicken.cluck();
                break;
            case '2':
                chicken.eat();
                break;
            case '3':
                await chicken.lay_egg(); 
                break;
            case '4':
                await chicken.poop();
                break;
            default:
                console.log("Invalid option.");
        }
    }

    async run() {
        let running = true;
        while (running) {
            console.log("\n=== CHICKEN FARM SIMULATOR ===\n");
            console.log("1. Add Chicken");
            console.log("2. Show Chickens");
            console.log("3. Chicken Actions");
            console.log("4. Update Chicken"); 
            console.log("5. Remove Chicken"); 
            console.log("6. Exit\n");
            
            const choice = await rl.question("Choose an option: ");

            switch (choice) {
                case '1':
                    await this.add_chicken();
                    break;
                case '2':
                    this.show_chickens();
                    break;
                case '3':
                    await this.chicken_action();
                    break;
                case '4':
                    await this.update_chicken();
                    break;
                case '5':
                    await this.remove_chicken();
                    break;
                case '6':
                    save_data(this.coops);
                    console.log("Goodbye!");
                    rl.close();
                    running = false;
                    break;
                default:
                    console.log("Invalid option. Try again.");
            }
        }
    }
}

const simulator = new ChickenFarmSimulator();
simulator.run().catch(console.error);