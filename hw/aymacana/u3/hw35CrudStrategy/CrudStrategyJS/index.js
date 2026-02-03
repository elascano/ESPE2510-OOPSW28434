import readline from 'readline';
import { CustomerController } from './controllers/CustomerController.js';
import { Customer } from './models/Customer.js';
import { MongoStrategy } from './models/strategies/MongoStrategy.js';

class ConsoleApp {
    constructor() {
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        
        this.controller = new CustomerController();
        this.currentFormat = "JSON";
        
        process.on('SIGINT', () => this.shutdown());
        process.on('SIGTERM', () => this.shutdown());
    }

    shutdown() {
        console.log("\nClosing MongoDB connection...");
        const mongoStrategy = new MongoStrategy();
        mongoStrategy.close().then(() => {
            console.log("MongoDB connection closed.");
            process.exit(0);
        });
    }

    async start() {
        console.clear();
        console.log("========================================");
        console.log("    CRUD STRATEGY SYSTEM - CONSOLE");
        console.log("========================================\n");
        
        await this.mainMenu();
    }

    async mainMenu() {
        console.log("\n=== MAIN MENU ===");
        console.log("Current Format:", this.currentFormat);
        console.log("1. Add Customer");
        console.log("2. Read Customers");
        console.log("3. Update Customer");
        console.log("4. Delete Customer");
        console.log("5. Exit");
        
        const choice = await this.askQuestion("\nSelect an option (1-5): ");
        
        switch(choice) {
            case '1':
                await this.addCustomer();
                break;
            case '2':
                await this.readCustomers();
                break;
            case '3':
                await this.updateCustomer();
                break;
            case '4':
                await this.deleteCustomer();
                break;
            case '5':
                this.shutdown();
                return;
            default:
                console.log("Invalid option. Please try again.");
        }
        
        await this.mainMenu();
    }

    async addCustomer() {
        console.log("\n=== ADD CUSTOMER ===");
        
        console.log("\nSelect storage format:");
        console.log("1. JSON");
        console.log("2. CSV");
        console.log("3. MongoDB");
        console.log("4. Cancel");
        
        const formatChoice = await this.askQuestion("\nSelect format (1-4): ");
        
        let format;
        switch(formatChoice) {
            case '1':
                format = "JSON";
                break;
            case '2':
                format = "CSV";
                break;
            case '3':
                format = "MongoDB";
                break;
            case '4':
                console.log("Operation cancelled.");
                return;
            default:
                console.log("Invalid format selection.");
                return;
        }
        
        try {
            const name = await this.askQuestion("Name: ");
            const apartmentNumber = await this.askQuestion("Apartment Number: ");
            const email = await this.askQuestion("Email: ");
            const phone = await this.askQuestion("Phone: ");
            
            this.controller.setStorageType(format);
            this.currentFormat = format;
            
            console.log("\nAdding customer...");
            const success = await this.controller.addCustomerFromFields(name, apartmentNumber, email, phone);
            
            if (success) {
                console.log(`Customer added successfully to ${format}!`);
            } else {
                console.log("Error adding customer.");
            }
            
        } catch (error) {
            console.log(`Error: ${error.message}`);
        }
    }

    async readCustomers() {
        console.log("\n=== READ CUSTOMERS ===");
        
        console.log("\nSelect option:");
        console.log("1. Read from current format");
        console.log("2. Read from specific format");
        console.log("3. Read from all formats");
        console.log("4. Cancel");
        
        const choice = await this.askQuestion("\nSelect option (1-4): ");
        
        switch(choice) {
            case '1':
                await this.readFromCurrentFormat();
                break;
            case '2':
                await this.readFromSpecificFormat();
                break;
            case '3':
                await this.readFromAllFormats();
                break;
            case '4':
                console.log("Operation cancelled.");
                break;
            default:
                console.log("Invalid option.");
        }
    }

    async readFromCurrentFormat() {
        console.log(`\n=== CUSTOMERS (${this.currentFormat}) ===`);
        
        const customers = await this.controller.getAllCustomers();
        
        if (customers.length === 0) {
            console.log("No customers found.");
        } else {
            customers.forEach(customer => {
                console.log(customer.toString());
            });
            console.log(`\nTotal: ${customers.length} customers`);
        }
    }

    async readFromSpecificFormat() {
        console.log("\nSelect format:");
        console.log("1. JSON");
        console.log("2. CSV");
        console.log("3. MongoDB");
        console.log("4. Cancel");
        
        const formatChoice = await this.askQuestion("\nSelect format (1-4): ");
        
        let format;
        switch(formatChoice) {
            case '1':
                format = "JSON";
                break;
            case '2':
                format = "CSV";
                break;
            case '3':
                format = "MongoDB";
                break;
            case '4':
                console.log("Operation cancelled.");
                return;
            default:
                console.log("Invalid format selection.");
                return;
        }
        
        this.controller.setStorageType(format);
        this.currentFormat = format;
        
        await this.readFromCurrentFormat();
    }

    async readFromAllFormats() {
        console.log("\n=== ALL CUSTOMERS (All Formats) ===\n");
        
        const formats = ["JSON", "CSV", "MongoDB"];
        let totalCustomers = 0;
        
        for (const format of formats) {
            this.controller.setStorageType(format);
            const customers = await this.controller.getAllCustomers();
            
            if (customers.length > 0) {
                console.log(`--- ${format} (${customers.length}) ---`);
                customers.forEach(customer => {
                    console.log(customer.toString());
                });
                console.log();
                totalCustomers += customers.length;
            }
        }
        
        console.log(`Total across all formats: ${totalCustomers} customers`);
    
        this.controller.setStorageType(this.currentFormat);
    }

    async updateCustomer() {
        console.log("\n=== UPDATE CUSTOMER ===");
        
        try {
            const customerId = parseInt(await this.askQuestion("Customer ID to update: "));
            
            if (isNaN(customerId)) {
                console.log("Invalid ID. Must be a number.");
                return;
            }
            
            console.log("\nSearching in all formats...");
            
            const formats = ["JSON", "CSV", "MongoDB"];
            let customerFound = null;
            let foundInFormat = null;
            
            for (const format of formats) {
                this.controller.setStorageType(format);
                const customer = await this.controller.getCustomerById(customerId);
                
                if (customer) {
                    customerFound = customer;
                    foundInFormat = format;
                    break;
                }
            }
            
            if (!customerFound) {
                console.log(`❌ Customer with ID ${customerId} not found in any format.`);
                return;
            }
            
            console.log(`\nCustomer found in ${foundInFormat} format:`);
            console.log(customerFound.toString());
            
            console.log("\nEnter new data (press Enter to keep current value):");
            
            const name = await this.askQuestion(`Name [${customerFound.name}]: `);
            const apartmentNumber = await this.askQuestion(`Apartment Number [${customerFound.apartmentNumber}]: `);
            const email = await this.askQuestion(`Email [${customerFound.email}]: `);
            const phone = await this.askQuestion(`Phone [${customerFound.phone}]: `);
            
            const finalName = name.trim() || customerFound.name;
            const finalApartment = apartmentNumber.trim() || customerFound.apartmentNumber;
            const finalEmail = email.trim() || customerFound.email;
            const finalPhone = phone.trim() || customerFound.phone;
            
            console.log(`\nUpdating customer in ${foundInFormat} format...`);
            this.controller.setStorageType(foundInFormat);
            
            const success = await this.controller.updateCustomer(
                customerId,
                finalName,
                finalApartment,
                finalEmail,
                finalPhone
            );
            
            if (success) {
                console.log(`Customer updated successfully in ${foundInFormat}!`);
                this.currentFormat = foundInFormat;
            } else {
                console.log("Error updating customer.");
            }
            
        } catch (error) {
            console.log(`Error: ${error.message}`);
        }
    }

    async deleteCustomer() {
        console.log("\n=== DELETE CUSTOMER ===");
        
        try {
            const customerId = parseInt(await this.askQuestion("Customer ID to delete: "));
            
            if (isNaN(customerId)) {
                console.log("Invalid ID. Must be a number.");
                return;
            }
            
            console.log("\nSearching in all formats...");
            
            const formats = ["JSON", "CSV", "MongoDB"];
            let customerFound = null;
            let foundInFormat = null;
            
            for (const format of formats) {
                this.controller.setStorageType(format);
                const customer = await this.controller.getCustomerById(customerId);
                
                if (customer) {
                    customerFound = customer;
                    foundInFormat = format;
                    break;
                }
            }
            
            if (!customerFound) {
                console.log(`Customer with ID ${customerId} not found in any format.`);
                return;
            }
            
            console.log(`\nCUSTOMER FOUND IN ${foundInFormat.toUpperCase()}`);
            console.log(customerFound.toString());
            console.log("\nWARNING: This action cannot be undone!");
            
            const confirm = await this.askQuestion("\nAre you sure you want to delete this customer? (yes/no): ");
            
            if (confirm.toLowerCase() === 'yes') {
                console.log(`\nDeleting from ${foundInFormat}...`);
                this.controller.setStorageType(foundInFormat);
                
                const success = await this.controller.deleteCustomer(customerId);
                
                if (success) {
                    console.log(` Customer deleted successfully from ${foundInFormat}!`);
                    this.currentFormat = foundInFormat;
                } else {
                    console.log("Error deleting customer.");
                }
            } else {
                console.log("Deletion cancelled.");
            }
            
        } catch (error) {
            console.log(`Error: ${error.message}`);
        }
    }

    askQuestion(question) {
        return new Promise((resolve) => {
            this.rl.question(question, (answer) => {
                resolve(answer.trim());
            });
        });
    }
}

const app = new ConsoleApp();
app.start().catch(console.error);