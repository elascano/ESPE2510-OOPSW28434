const fs = require('fs');
const Chicken = require('../ec.espe.edu.chickenFarmSimulator.model/chicken');
const ChickenCoop = require('../ec.espe.edu.chickenFarmSimulator.model/chickenCoops');
const ChickenFarmer = require('../ec.espe.edu.chickenFarmSimulator.model/chickenFarmer');
const ChickenFarmView = require('../ec.espe.edu.chickenFarmSimulator.view/chickenFarmSimulator');


class ChickenFarmController {
    constructor() {
        this.view = new ChickenFarmView(this);
        this.farmer = null;
        this.chickenIdCounter = 1;
        this.loadDataFromJson();
    }

    start() {
        this.view.showMainMenu();
    }
    
    handleMainMenu(choice) {
        switch (choice) {
            case '1':
                this.view.showCoopManagementMenu();
                break;
            case '2':
                this.simulateCoopDay();
                break;
            case '3':
                this.view.showFarmerManagementMenu();
                break;
            case '4':
                this.saveDataToJson();
                break;
            case '5':
                this.view.displayMessage('Leaving the program, bye');
                this.view.close();
                break;
            default:
                this.view.displayMessage('Invalid option. Please try again.');
                this.view.showMainMenu();
                break;
        }
    }

    handleCoopManagementMenu(choice) {
        switch (choice) {
            case '1':
                this.requestNewCoopCapacity();
                break;
            case '2':
                this.readCoops();
                break;
            case '3':
                this.requestCoopIdToUpdate();
                break;
            case '4':
                this.requestCoopIdToRemove();
                break;
            case '5':
                this.view.showMainMenu();
                break;
            default:
                this.view.displayMessage('Invalid option. Please try again.');
                this.view.showCoopManagementMenu();
                break;
        }
    }

    handleFarmerManagementMenu(choice) {
        switch (choice) {
            case '1':
                this.readFarmerInfo();
                break;
            case '2':
                this.requestFarmerNameUpdate();
                break;
            case '3':
                this.view.showMainMenu();
                break;
            default:
                this.view.displayMessage('Invalid option. Please try again.');
                this.view.showFarmerManagementMenu();
                break;
        }
    }

    handleChickenManagementMenu(choice, coopId) {
        const id = parseInt(coopId);
        const coop = this.farmer.findCoop(id);

        if (!coop) {
            this.view.displayMessage(`Chicken coop ID ${id} not found`);
            this.view.showCoopManagementMenu();
            return;
        }

        switch (choice) {
            case '1':
                this.requestNumberOfChickens(coop);
                break;
            case '2':
                this.readChickens(coop);
                break;
            case '3':
                this.requestChickenIdToUpdate(coop);
                break;
            case '4':
                this.requestChickenIdToRemove(coop);
                break;
            case '5':
                this.view.showCoopManagementMenu();
                break;
            default:
                this.view.displayMessage('Invalid option. Please try again.');
                this.view.showChickenManagementMenu(coopId);
                break;
        }
    }

    readFarmerInfo() {
        this.view.displayFarmerInfo(this.farmer);
        this.view.showFarmerManagementMenu();
    }
    
    requestFarmerNameUpdate() {
        this.view.requestFarmerName(this.farmer.getName(), (newName) => {
            const trimmedName = newName.trim();
            if (trimmedName) {
                this.farmer.setName(trimmedName);
                this.view.displayMessage(`Farmer name updated ${trimmedName}`);
            } else {
                this.view.displayMessage('Name not modified');
            }
            this.view.showFarmerManagementMenu();
        });
    }

    requestNewCoopCapacity() {
        this.view.requestNewCoopCapacity((capacity) => {
            const numCapacity = parseInt(capacity.trim());

            if (isNaN(numCapacity) || numCapacity <= 0) {
                this.view.displayMessage('Invalid option. Please try again.');
                this.requestNewCoopCapacity();
                return;
            }

            const newCoop = this.farmer.addCoop(numCapacity);
            this.view.displayMessage(`Chicken coop ID ${newCoop.getCoopCoopNumber()} created with the capacity of ${numCapacity} chickens.`);
            this.view.showCoopManagementMenu();
        });
    }

    readCoops() {
        const coops = this.farmer.getCoops();
        if (coops.length === 0) {
            this.view.displayMessage('There are no registered chicken coops');
            this.view.showCoopManagementMenu();
            return;
        }

        this.view.requestCoopIdToManageChickens(coops, (coopIdInput) => {
            if (coopIdInput.trim().toLowerCase() === 'n') {
                this.view.showCoopManagementMenu();
                return;
            }

            const id = parseInt(coopIdInput.trim());
            const coop = this.farmer.findCoop(id);

            if (coop) {
                this.view.showChickenManagementMenu(id);
            } else {
                this.view.displayMessage(`Chicken coop ID ${id} not found`);
                this.readCoops();
            }
        });
    }

    requestCoopIdToUpdate() {
        if (this.farmer.getCoops().length === 0) {
            this.view.displayMessage('There are no chicken coops to edit');
            this.view.showCoopManagementMenu();
            return;
        }
        this.view.requestCoopIdForAction('edit', (coopId) => {
            const id = parseInt(coopId.trim());
            const coop = this.farmer.findCoop(id);

            if (!coop) {
                this.view.displayMessage(`Chicken coop ID ${id} not found`);
                this.view.showCoopManagementMenu();
                return;
            }

            this.view.requestNewCoopCapacityValue(coop.getCapacity(), (newCapacity) => {
                const numCapacity = parseInt(newCapacity.trim());
                const currentChickens = coop.getChickens().length;

                if (isNaN(numCapacity) || numCapacity < currentChickens) {
                    this.view.displayMessage(`The new capacity must be a valid number, greater than or equal to ${currentChickens}`);
                    this.view.showCoopManagementMenu(); 
                    return;
                }

                this.farmer.updateCoop(id, { capacity: numCapacity });
                this.view.displayMessage(`Chicken coop ID ${id} update to capacity ${numCapacity}.`);
                this.view.showCoopManagementMenu();
            });
        });
    }

    requestCoopIdToRemove() {
        if (this.farmer.getCoops().length === 0) {
            this.view.displayMessage('There are no chicken coops to erase');
            this.view.showCoopManagementMenu();
            return;
        }
        this.view.requestCoopIdForAction('delete', (coopId) => {
            const id = parseInt(coopId.trim());

            if (this.farmer.removeCoop(id)) {
                this.view.displayMessage(`Chicken coop ID ${id} erased`);
            } else {
                this.view.displayMessage(`Chicken coop ID ${id} not found`);
            }
            this.view.showCoopManagementMenu();
        });
    }

    requestNumberOfChickens(coop) {
        const availableSpace = coop.getCapacity() - coop.getChickens().length;
        if (availableSpace <= 0) {
            this.view.displayMessage(`Chicken coop ${coop.getCoopCoopNumber()} is full`);
            this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
            return;
        }

        this.view.requestNumberOfChickens(coop.getCoopCoopNumber(), availableSpace, (count) => {
            const numChickens = parseInt(count.trim());
            
            if (isNaN(numChickens) || numChickens <= 0 || numChickens > availableSpace) {
                this.view.displayMessage(`Please enter a valid positive number less than or equal to ${availableSpace}.`);
                this.requestNumberOfChickens(coop);
                return;
            }

            this.requestChickenDetails(coop, numChickens, 1, []);
        });
    }

    requestChickenDetails(coop, totalCount, currentNumber, chickensToCreate) {
        this.view.requestChickenDetails(currentNumber, totalCount, this.chickenIdCounter, (details) => {
            const data = { 
                id: this.chickenIdCounter++, 
                name: details.name.trim(),
                color: details.color.trim(),
                age: parseInt(details.age.trim()),
                isMolting: details.isMoltingInput.trim().toLowerCase() === 's'
            };

            if (isNaN(data.age) || data.age < 0) {
                this.view.displayMessage('Invalid age. Please try again with a number.');
                this.chickenIdCounter--; 
                this.requestChickenDetails(coop, totalCount, currentNumber, chickensToCreate);
                return;
            }
            
            chickensToCreate.push(data);

            if (currentNumber < totalCount) {
                this.requestChickenDetails(coop, totalCount, currentNumber + 1, chickensToCreate);
            } else {
                this.processChickenCreation(coop, chickensToCreate);
            }
        });
    }

    processChickenCreation(coop, chickensToCreate) {
        this.view.displayMessage(`\n--- Creating and adding ${chickensToCreate.length} chicken to Chicken coop ${coop.getCoopCoopNumber()}... ---`);
        for (const data of chickensToCreate) {
            const newChicken = new Chicken(data.id, data.name, data.color, data.age, data.isMolting);
            if (coop.addChicken(newChicken)) {
                this.view.displayMessage(`Chicken ${data.name} (ID: ${data.id}) added.`);
            } else {
                this.view.displayMessage(`The chicken could not be added ${data.name}. Chicken coop is full`); 
                this.chickenIdCounter--; 
            }
        }
        this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
    }

    readChickens(coop) {
        this.view.displayChickens(coop.getCoopCoopNumber(), coop.getChickens(), coop.getCapacity());
        this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
    }

    requestChickenIdToUpdate(coop) {
        if (coop.getChickens().length === 0) {
            this.view.displayMessage('There are no chickens to edit.');
            this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
            return;
        }
        this.view.requestChickenIdToUpdate((chickenId) => {
            const id = parseInt(chickenId.trim());
            const chicken = coop.findChicken(id);

            if (!chicken) {
                this.view.displayMessage(`Chicken ID ${id} not found`);
                this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
                return;
            }

            this.view.requestChickenUpdates(chicken, (updates) => {
                const finalUpdates = {};

                if (updates.name.trim()) finalUpdates.name = updates.name.trim();
                if (updates.color.trim()) finalUpdates.color = updates.color.trim();

                const numAge = parseInt(updates.age.trim());
                if (updates.age.trim() && !isNaN(numAge) && numAge >= 0) {
                    finalUpdates.age = numAge;
                } else if (updates.age.trim() && (isNaN(numAge) || numAge < 0)) {
                    this.view.displayMessage('Age invalid. Edition cancelled');
                    this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
                    return;
                }
                
                const trimmedInput = updates.isMoltingInput.trim().toLowerCase();
                if (trimmedInput === 's') finalUpdates.isMolting = true;
                if (trimmedInput === 'n') finalUpdates.isMolting = false;

                if (coop.updateChicken(id, finalUpdates)) {
                    this.view.displayMessage(`Chicken ID ${id} updated`);
                }
                this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
            });
        });
    }

    requestChickenIdToRemove(coop) {
        if (coop.getChickens().length === 0) {
            this.view.displayMessage('There are no chickens to erase.');
            this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
            return;
        }
        this.view.requestChickenIdToRemove((chickenId) => {
            const id = parseInt(chickenId.trim());

            if (coop.removeChicken(id)) {
                this.view.displayMessage(`Chicken ID ${id} erased from Chicken coop ${coop.getCoopCoopNumber()}.`);
            } else {
                this.view.displayMessage(`Chicken ID ${id} not found in the Chicken coop ${coop.getCoopCoopNumber()}.`);
            }
            this.view.showChickenManagementMenu(coop.getCoopCoopNumber());
        });
    }

    simulateCoopDay() {
        let totalEggs = 0;
        this.view.displayMessage('\n--- Simulate ChickenCoop Day---');
        
        const coops = this.farmer.getCoops();
        
        if (coops.length === 0) {
            this.view.displayMessage('Chicken coop not found');
        } else {
            coops.forEach(coop => {
                const eggsLaid = coop.simulateCoopDay(); 
                totalEggs += eggsLaid;
            });
             this.view.displayMessage(`\nToday a grand total of ${totalEggs} eggs** were lais across the entire farm`);
        }
        this.view.showMainMenu();
    }

    saveDataToJson() {
        const filename = 'farmData.json';
        const dataToSave = {
            farmer: {
                id: this.farmer.getId(),
                name: this.farmer.getName(),
            },
            coops: this.farmer.getCoops().map(coop => ({
                coopId: coop.getCoopCoopNumber(),
                capacity: coop.getCapacity(),
                totalEggs: coop.getTotalEggs(),
                chickens: coop.getChickens().map(chicken => ({
                    id: chicken.getId(),
                    name: chicken.getName(),
                    color: chicken.getColor(),
                    age: chicken.getAge(),
                    isMolting: chicken.getIsMolting(),
                    eggsProduced: chicken.getEggsProduced(),
                }))
            })),
            nextChickenId: this.chickenIdCounter,
            nextCoopId: this.farmer.getNextCoopId()
        };

        try {
            const jsonContent = JSON.stringify(dataToSave, null, 2);
            fs.writeFileSync(filename, jsonContent);

            this.view.displayMessage('\n======================================');
            this.view.displayMessage(`DATA SAVED IN: ${filename}`);
            this.view.displayMessage('======================================\n');
            
        } catch (error) {
            this.view.displayMessage(`ERROR saving the JSON file: ${error.message}`);
        }

        this.view.showMainMenu();
    }

    loadDataFromJson(){
        const filename = 'farmData.json'
        if (!fs.existsSync(filename)) {
            this.view.displayMessage("No saved data found. Starting a new farm");
            this.farmer = new ChickenFarmer(1, 'Mathews');
            this.chickenIdCounter = 1;
            return;
        }

        try{
            const fileContent = fs.readFileSync(filename, 'utf8');
            const data = JSON.parse(fileContent);
            const farmerData = data.farmer;
            this.farmer = new ChickenFarmer(farmerData.id, farmerData.name);

            const coopsData = data.coops || [];
            coopsData.forEach(coopData => {
                const newCoop = new ChickenCoop(coopData.coopId, coopData.capacity);
                newCoop.setTotalEggs(coopData.totalEggs || 0);

                const chickensData = coopData.chickens || [];
                chickensData.forEach(chickenData => {
                    const newChicken = new Chicken(
                        chickenData.id,
                        chickenData.name,
                        chickenData.color,
                        chickenData.age,
                        chickenData.isMolting
                    );
                    newChicken.setEggsProduced(chickenData.eggsProduced || 0);
                    newCoop.addChicken(newChicken);
                });

                this.farmer.getCoops().push(newCoop);
            });

            this.chickenIdCounter = data.nextChickenId || 1;
            this.farmer.setNextCoopId(data.nextCoopId || 1);

            this.view.displayMessage(`Data successfully loaded from ${filename}`);

        } catch (error) {
            this.view.displayMessage(`ERROR loading data: ${error.message}. Starting a new farm.`);
            this.farmer = new ChickenFarmer(1, 'Mathews');
            this.chickenIdCounter = 1;
        }
    }
}

module.exports = ChickenFarmController;