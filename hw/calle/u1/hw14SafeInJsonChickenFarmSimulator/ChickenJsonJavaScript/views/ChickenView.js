import readline from 'readline';

export { ChickenView };

class ChickenView {
    constructor(controller) {
        this.controller = controller;
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
    }

    showMenu() {
        console.log('\n=== CHICKEN MANAGEMENT SYSTEM ===');
        console.log('1. Insert chicken');
        console.log('2. List chickens');
        console.log('3. Delete chicken');
        console.log('4. Update chicken');
        console.log('5. Find chickens');
        console.log('6. Exit');
    }

    question(prompt) {
        return new Promise((resolve) => {
            this.rl.question(prompt, resolve);
        });
    }

    async getMenuChoice() {
        const choice = await this.question('Select an option: ');
        return parseInt(choice);
    }

    async getChickenData(suggestedId = null) {
        try {
            const idInput = suggestedId ? ` [${suggestedId}]` : '';
            const idInputValue = await this.question(`Chicken ID${idInput}: `);
            const id = parseInt(idInputValue || suggestedId);
            const name = await this.question('Name: ');
            const color = await this.question('Color: ');
            const age = parseInt(await this.question('Age: '));
            const isMoltingInput = (await this.question('Is molting?: ')).toLowerCase();
            const isMolting = isMoltingInput === 'true' || isMoltingInput === 'false';
            
            return { id, name, color, age, isMolting };
        } catch (error) {
            console.log('Error: Please enter valid data');
            return null;
        }
    }

    showChickens(chickens) {
        if (!chickens || chickens.length === 0) {
            console.log('No chickens registered.');
            return;
        }

        console.log('ID\tName\t\tColor\t\tAge\tMolting');
        console.log('-'.repeat(60));
        chickens.forEach(chicken => {
            console.log(`${chicken.id}\t${chicken.name}\t\t${chicken.color}\t\t${chicken.age}\t${chicken.isMolting}`);
        });
    }

    async getChickenId() {
        try {
            const id = await this.question('Chicken ID: ');
            return parseInt(id);
        } catch (error) {
            return -1;
        }
    }

    async getSearchName() {
        return await this.question('Name to search: ');
    }

    showMessage(message) {
        console.log(`\n${message}`);
    }

    showError(message) {
        console.log(`\nError: ${message}`);
    }

    async waitForEnter() {
        await this.question('Press Enter to continue...');
    }

    async run() {
        let running = true;

        while (running) {
            console.clear();
            this.showMenu();
            const choice = await this.getMenuChoice();

            switch (choice) {
                case 1:
                    await this.handleInsert();
                    break;
                case 2:
                    await this.handleList();
                    break;
                case 3:
                    await this.handleDelete();
                    break;
                case 4:
                    await this.handleUpdate();
                    break;
                case 5:
                    await this.handleFind();
                    break;
                case 6:
                    this.showMessage('Goodbye!');
                    running = false;
                    break;
                default:
                    this.showError('Invalid option');
                    await this.waitForEnter();
            }
        }
        this.rl.close();
    }

    async handleInsert() {
        const suggestedId = this.controller.getNextAvailableId();
        const chickenData = await this.getChickenData(suggestedId);
        
        if (chickenData) {
            const { id, name, color, age, isMolting } = chickenData;
            if (this.controller.insertChicken(id, name, color, age, isMolting)) {
                this.showMessage('Chicken inserted successfully!');
            } else {
                this.showError('Could not insert chicken (duplicate ID or invalid data)');
            }
        }
        await this.waitForEnter();
    }

    async handleList() {
        const chickens = this.controller.listChickens();
        this.showChickens(chickens);
        await this.waitForEnter();
    }

    async handleDelete() {
        const chickenId = await this.getChickenId();
        if (chickenId !== -1) {
            if (this.controller.deleteChicken(chickenId)) {
                this.showMessage('Chicken deleted successfully!');
            } else {
                this.showError('Chicken not found with that ID');
            }
        } else {
            this.showError('Invalid ID');
        }
        await this.waitForEnter();
    }

    async handleUpdate() {
        const chickenId = await this.getChickenId();
        if (chickenId !== -1) {
            const existingChicken = this.controller.getChickenById(chickenId);
            if (existingChicken) {
                console.log(`Current chicken: ${existingChicken.toString()}`);
                console.log('Enter new data:');
                
                const name = await this.question(`Name [${existingChicken.name}]: `) || existingChicken.name;
                const color = await this.question(`Color [${existingChicken.color}]: `) || existingChicken.color;
                
                try {
                    const ageInput = await this.question(`Age [${existingChicken.age}]: `);
                    const age = ageInput ? parseInt(ageInput) : existingChicken.age;

                    const isMoltingInput = await this.question(`Is molting?  [${existingChicken.isMolting ? 'true' : 'false'}]: `).toLowerCase();
                    const isMolting = isMoltingInput ? (isMoltingInput === 'true') : existingChicken.isMolting;

                    if (this.controller.updateChicken(chickenId, name, color, age, isMolting)) {
                        this.showMessage('Chicken updated successfully!');
                    } else {
                        this.showError('Error updating chicken');
                    }
                } catch (error) {
                    this.showError('Invalid age');
                }
            } else {
                this.showError('Chicken not found with that ID');
            }
        } else {
            this.showError('Invalid ID');
        }
        await this.waitForEnter();
    }

    async handleFind() {
        const searchName = await this.getSearchName();
        if (searchName.trim()) {
            const chickens = this.controller.findChickens(searchName);
            this.showChickens(chickens);
        } else {
            this.showError('Please enter a name to search');
        }
        await this.waitForEnter();
    }
}