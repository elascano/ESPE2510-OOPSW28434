const readline = require('readline');

class ContactView {
    constructor(contactController) {
        this.contactController = contactController;
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
    }

    displayWelcome() {
        console.clear();
        console.log('='.repeat(60));
        console.log('CONTACT MANAGEMENT SYSTEM');
        console.log('='.repeat(60));
        console.log('Database: oop');
        console.log('Collection: Customers');
        console.log('='.repeat(60) + '\n');
    }

    displayMenu() {
        console.log('\nMAIN MENU:');
        console.log('1. View all contacts');
        console.log('2. Search contact by ID');
        console.log('3. Exit');
    }

    async getInput(prompt) {
        return new Promise((resolve) => {
            this.rl.question(prompt, (answer) => {
                resolve(answer);
            });
        });
    }

    async handleSearchById() {
        const id = await this.getInput('Enter contact ID: ');
        if (id && !isNaN(id)) {
            await this.contactController.displayContactById(parseInt(id));
        } else {
            console.log('Invalid ID');
        }
    }

    async pressToContinue() {
        await this.getInput('\nPress Enter to continue...');
    }

    async run() {
        this.displayWelcome();
        
        let running = true;
        
        while (running) {
            try {
                this.displayMenu();
                const choice = await this.getInput('\nSelect option (1-3): ');
                
                switch (choice) {
                    case '1':
                        await this.contactController.displayAllContacts();
                        break;
                    case '2':
                        await this.handleSearchById();
                        break;
                    case '3':
                        running = false;
                        break;
                    default:
                        console.log('Invalid option');
                }
                
                if (running && (choice === '1' || choice === '2')) {
                    await this.pressToContinue();
                    console.log('\n');
                }
                
            } catch (error) {
                console.error(`Error: ${error.message}`);
                await this.pressToContinue();
            }
        }
        
        this.rl.close();
        console.log('\nThank you for using Contact Management System!');
    }
}

module.exports = ContactView;