const readline = require('readline');

class ChickenFarmView {
    constructor(controller) {
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        this.controller = controller; 
    }

    showMainMenu() {
        console.log('\n======================================');
        console.log(' FARM SIMULATOR MENU');
        console.log('======================================');
        console.log('1. Manage Farm ');
        console.log('2. Simulate Daily Actions '); 
        console.log('3. Manage Current Farmer');
        console.log('4. Save Data ');
        console.log('5. Exit');
        console.log('======================================');
        this.rl.question('Select an option: ', (choice) => this.controller.handleMainMenu(choice.trim()));
    }

    showCoopManagementMenu() {
        console.log('\n======================================');
        console.log(' CHICKEN COOP MANAGEMENT ');
        console.log('======================================');
        console.log('1. Create Coop');
        console.log('2. Read/Show Coops ');
        console.log('3. Edit Coop ');
        console.log('4. Delete Coop');
        console.log('5. Back to Main Menu');
        console.log('======================================');
        this.rl.question('Select an option: ', (choice) => this.controller.handleCoopManagementMenu(choice.trim()));
    }
    
    showFarmerManagementMenu() {
        console.log('\n======================================');
        console.log(' FARMER MANAGEMENT');
        console.log('======================================');
        console.log('1. View Current Farmer Info');
        console.log('2. Edit Farmer Name');
        console.log('3. Back to Main Menu');
        console.log('======================================');
        this.rl.question('Select an option: ', (choice) => this.controller.handleFarmerManagementMenu(choice.trim()));
    }

    showChickenManagementMenu(coopId) {
        console.log(`\n======================================`);
        console.log(` CHICKEN MANAGEMENT - COOP ID ${coopId}`);
        console.log('======================================');
        console.log('1. Create Chicken(s)');
        console.log('2. Read/Show Chickens');
        console.log('3. Edit Chicken');
        console.log('4. Delete Chicken');
        console.log('5. Back to Coop Menu');
        console.log('======================================');
        this.rl.question('Select an option: ', (choice) => this.controller.handleChickenManagementMenu(choice.trim(), coopId));
    }

    requestFarmerName(currentName, callback) {
        this.rl.question(`Enter new Farmer Name (Current: ${currentName}): `, callback);
    }
    
    requestNewCoopCapacity(callback) {
        this.rl.question('Enter maximum capacity for the new coop: ', callback);
    }

    requestCoopIdForAction(actionName, callback) {
        this.rl.question(`Enter the Coop ID to ${actionName}: `, callback);
    }
    
    requestCoopIdToManageChickens(coopsList, callback) {
        console.log('\n--- FARMER\'S CHICKEN COOPS ---');
        coopsList.forEach(coop => console.log(`  ${coop.toString()}`));
        console.log('-------------------------------');
        this.rl.question('Do you want to manage chickens in a coop? (Enter Coop ID or "n" to go back): ', callback);
    }

    requestNewCoopCapacityValue(currentCapacity, callback) {
        this.rl.question(`Enter the new capacity for the Coop (Current: ${currentCapacity}): `, callback);
    }

    requestNumberOfChickens(coopId, availableSpace, callback) {
        this.rl.question(`How many chickens to add to Coop ${coopId}? (Max: ${availableSpace}): `, callback);
    }
    
    requestChickenDetails(currentNumber, totalCount, chickenId, detailsCallback) {
        console.log(`\n--- Details for Chicken ${currentNumber} of ${totalCount} (ID: ${chickenId}) ---`);
        this.rl.question('Name: ', (name) => {
            this.rl.question('Color: ', (color) => {
                this.rl.question('Age (years): ', (age) => {
                    this.rl.question('Is it molting (y/n)?: ', (isMoltingInput) => {
                        detailsCallback({ name, color, age, isMoltingInput });
                    });
                });
            });
        });
    }

    requestChickenIdToUpdate(callback) {
        this.rl.question('Enter the ID of the chicken to edit: ', callback);
    }

    requestChickenUpdates(chicken, callback) {
        console.log(`\nEditing Chicken ${chicken.getName()} (ID: ${chicken.getId()}). Leave blank to keep current value.`);
        
        this.rl.question(`New Name (Current: ${chicken.getName()}): `, (name) => {
            this.rl.question(`New Color (Current: ${chicken.getColor()}): `, (color) => {
                this.rl.question(`New Age (Current: ${chicken.getAge()}): `, (age) => {
                    this.rl.question(`Is it Molting? (y/n) (Current: ${chicken.getIsMolting() ? 'Yes' : 'No'}): `, (isMoltingInput) => {
                        callback({ name, color, age, isMoltingInput });
                    });
                });
            });
        });
    }

    requestChickenIdToRemove(callback) {
        this.rl.question('Enter the ID of the chicken to delete: ', callback);
    }

    displayMessage(message) {
        console.log(message);
    }

    displayChickens(coopId, chickens, capacity) {
        console.log(`\n--- CHICKENS IN COOP ${coopId} (${chickens.length}/${capacity}) ---`);
        if (chickens.length === 0) {
            console.log('No chickens in this coop.');
        } else {
            chickens.forEach(chicken => {
                console.log(`  ${chicken.toString()}`);
            });
        }
        console.log('----------------------------------------------------');
    }
    
    displayFarmerInfo(farmer) {
        console.log('\n======================================');
        console.log(`  FARMER INFORMATION: ${farmer.getName()}`);
        console.log('======================================');
        console.log(`ID: ${farmer.getId()}`);
        console.log(`Name: ${farmer.getName()}`);
        console.log(`Total Coops: ${farmer.getCoops().length}`);
        console.log('======================================');
    }

    close() {
        this.rl.close();
    }
}

module.exports = ChickenFarmView;