import readline from 'readline';

export class FarmView {
    constructor(controller) {
        this.controller = controller;
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
    }
    
    showMenu() {
        console.log("\n" + "=".repeat(50));
        console.log("          CHICKEN FARM SIMULATOR");
        console.log("=".repeat(50));
        console.log(`Coops: ${this.controller.getCoopCount()} | Chickens: ${this.controller.getChickenCount()}`);
        console.log("-".repeat(50));
        console.log("1. Create coop");
        console.log("2. Add chicken to coop");
        console.log("3. View all coops and chickens");
        console.log("4. Edit chicken");
        console.log("5. Delete chicken");
        console.log("6. Perform chicken action");
        console.log("7. Exit");
        console.log("-".repeat(50));
    }
    
    async getInput(prompt) {
        return new Promise((resolve) => {
            this.rl.question(prompt, (answer) => {
                resolve(answer.trim());
            });
        });
    }
    
    async createCoop() {
        console.log("\n--- CREATE COOP ---");
        try {
            const coopId = parseInt(await this.getInput("Coop ID: "));
            if (this.controller.createCoop(coopId)) {
                console.log("Coop created successfully!");
            } else {
                console.log("Error: A coop with that ID already exists");
            }
        } catch (error) {
            console.log("Error: ID must be an integer");
        }
    }
    
    async addChicken() {
        console.log("\n--- ADD CHICKEN ---");
        try {
            const coopId = parseInt(await this.getInput("Coop ID: "));
            const chickenId = parseInt(await this.getInput("Chicken ID: "));
            const name = await this.getInput("Chicken name: ");
            const color = await this.getInput("Chicken color: ");
            const age = parseInt(await this.getInput("Chicken age (months): "));
            const moltingInput = (await this.getInput("Is molting? (y/n): ")).toLowerCase();
            const isMolting = moltingInput === 'y';
            
            if (this.controller.addChickenToCoop(coopId, chickenId, name, color, age, isMolting)) {
                console.log("Chicken added successfully!");
            } else {
                console.log("Error: Could not add chicken (coop not found or duplicate ID)");
            }
        } catch (error) {
            console.log("Error: IDs and age must be integers");
        }
    }
    
    printTableRow(values, columnWidths) {
        let row = "|";
        values.forEach((value, i) => {
            row += ` ${String(value).padEnd(columnWidths[i])} |`;
        });
        console.log(row);
    }
    
    printTableSeparator(columnWidths) {
        let separator = "+";
        columnWidths.forEach(width => {
            separator += "-".repeat(width + 2) + "+";
        });
        console.log(separator);
    }
    
    showAllCoops() {
        console.log("\n--- COOPS AND CHICKENS ---");
        const coops = this.controller.getAllCoops();
        
        if (!coops || coops.length === 0) {
            console.log("No coops registered.");
            return;
        }
        
        coops.forEach(coop => {
            console.log(`\nCOOP ID: ${coop.id}`);
            console.log(`Number of chickens: ${coop.chickens.length}`);
            
            if (!coop.chickens || coop.chickens.length === 0) {
                console.log("No chickens in this coop");
                return;
            }
            
            const columnHeaders = ["ID", "Name", "Color", "Age", "Molting"];
            let columnWidths = [4, 15, 10, 5, 7];
            
            coop.chickens.forEach(chicken => {
                columnWidths[0] = Math.max(columnWidths[0], String(chicken.id).length);
                columnWidths[1] = Math.max(columnWidths[1], chicken.name.length);
                columnWidths[2] = Math.max(columnWidths[2], chicken.color.length);
                columnWidths[3] = Math.max(columnWidths[3], String(chicken.age).length);
                columnWidths[4] = Math.max(columnWidths[4], chicken.isMolting ? 3 : 2);
            });
            
            this.printTableSeparator(columnWidths);
            this.printTableRow(columnHeaders, columnWidths);
            this.printTableSeparator(columnWidths);
            
            coop.chickens.forEach(chicken => {
                const moltingStatus = chicken.isMolting ? "Yes" : "No";
                const rowData = [
                    chicken.id,
                    chicken.name,
                    chicken.color,
                    chicken.age,
                    moltingStatus
                ];
                this.printTableRow(rowData, columnWidths);
            });
            
            this.printTableSeparator(columnWidths);
        });
    }
    
    async editChicken() {
        console.log("\n--- EDIT CHICKEN ---");
        try {
            const coopId = parseInt(await this.getInput("Coop ID: "));
            const chickenId = parseInt(await this.getInput("Chicken ID to edit: "));
            
            const coop = this.controller.getCoop(coopId);
            if (!coop) {
                console.log("Error: Coop not found");
                return;
            }
            
            const chicken = coop.getChicken(chickenId);
            if (!chicken) {
                console.log("Error: Chicken not found");
                return;
            }
            
            console.log(`\nEditing chicken: ${chicken.name}`);
            const name = (await this.getInput(`New name (${chicken.name}): `)) || chicken.name;
            const color = (await this.getInput(`New color (${chicken.color}): `)) || chicken.color;
            
            const ageInput = await this.getInput(`New age (${chicken.age}): `);
            const age = ageInput ? parseInt(ageInput) : chicken.age;
            
            const moltingInput = (await this.getInput(`Is molting? (y/n) [${chicken.isMolting ? 'y' : 'n'}]: `)).toLowerCase();
            const isMolting = moltingInput ? moltingInput === 'y' : chicken.isMolting;
            
            if (this.controller.updateChicken(coopId, chickenId, name, color, age, isMolting)) {
                console.log("Chicken updated successfully!");
            } else {
                console.log("Error: Could not update chicken");
            }
        } catch (error) {
            console.log("Error: IDs and age must be integers");
        }
    }
    
    async deleteChicken() {
        console.log("\n--- DELETE CHICKEN ---");
        try {
            const coopId = parseInt(await this.getInput("Coop ID: "));
            const chickenId = parseInt(await this.getInput("Chicken ID to delete: "));
            
            const confirm = (await this.getInput("Are you sure you want to delete this chicken? (y/n): ")).toLowerCase();
            if (confirm === 'y') {
                if (this.controller.deleteChicken(coopId, chickenId)) {
                    console.log("Chicken deleted successfully!");
                } else {
                    console.log("Error: Could not delete chicken (coop or chicken not found)");
                }
            } else {
                console.log("Operation cancelled");
            }
        } catch (error) {
            console.log("Error: IDs must be integers");
        }
    }
    
    async performChickenAction() {
        console.log("\n--- CHICKEN ACTIONS ---");
        try {
            const coopId = parseInt(await this.getInput("Coop ID: "));
            const chickenId = parseInt(await this.getInput("Chicken ID: "));
            
            console.log("\nAvailable actions:");
            console.log("1. Cluck");
            console.log("2. Wander");
            console.log("3. Eat");
            console.log("4. Drink");
            console.log("5. Poop");
            console.log("6. Lay Egg");
            
            const actionChoice = await this.getInput("Select an action (1-6): ");
            
            const actionsMap = {
                '1': 'cluck',
                '2': 'wander',
                '3': 'eat',
                '4': 'drink',
                '5': 'poop',
                '6': 'lay_egg'
            };
            
            if (actionsMap[actionChoice]) {
                const action = actionsMap[actionChoice];
                console.log("\n" + "=".repeat(30));
                if (this.controller.performChickenAction(coopId, chickenId, action)) {
                    console.log("Action performed successfully!");
                } else {
                    console.log("Error: Could not perform action (coop or chicken not found)");
                }
                console.log("=".repeat(30));
            } else {
                console.log("Error: Invalid option");
            }
        } catch (error) {
            console.log("Error: IDs must be integers");
        }
    }
    
    async run() {
        console.log("Starting Chicken Farm Simulator...");
        
        try {
            while (true) {
                this.showMenu();
                const choice = await this.getInput("Select an option (1-7): ");
                
                switch (choice) {
                    case '1':
                        await this.createCoop();
                        break;
                    case '2':
                        await this.addChicken();
                        break;
                    case '3':
                        this.showAllCoops();
                        break;
                    case '4':
                        await this.editChicken();
                        break;
                    case '5':
                        await this.deleteChicken();
                        break;
                    case '6':
                        await this.performChickenAction();
                        break;
                    case '7':
                        console.log("Thank you for using Chicken Farm Simulator!");
                        this.rl.close();
                        return;
                    default:
                        console.log("Error: Invalid option. Please select 1-7.");
                }
                
                await this.getInput("\nPress Enter to continue...");
            }
        } catch (error) {
            console.log("Unexpected error:", error.message);
            this.rl.close();
        }
    }
}