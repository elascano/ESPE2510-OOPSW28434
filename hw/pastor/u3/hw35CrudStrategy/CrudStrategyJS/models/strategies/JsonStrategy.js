import fs from 'fs/promises';
import path from 'path';
import { Customer } from '../Customer.js';
import { CrudStrategy } from './CrudStrategy.js';

export class JsonStrategy extends CrudStrategy {
    constructor() {
        super();
        this.filePath = path.join(process.cwd(), 'data', 'customers.json');
        this.ensureFileExists();
    }

    async ensureFileExists() {
        try {
            const dirPath = path.dirname(this.filePath);
            await fs.mkdir(dirPath, { recursive: true });
            
            try {
                await fs.access(this.filePath);
            } catch {
                await fs.writeFile(this.filePath, JSON.stringify([], null, 4));
            }
        } catch (error) {
            console.error(`Error ensuring JSON file exists: ${error.message}`);
        }
    }

    async add(customer) {
        try {
            const customers = await this.readAll();
            
            const existingCustomer = customers.find(c => c.id === customer.id);
            if (existingCustomer) {
                console.log(`Error: ID ${customer.id} already exists in JSON`);
                return false;
            }
            
            customers.push(customer);
            
            const customersJson = customers.map(c => c.toJson());
            await fs.writeFile(this.filePath, JSON.stringify(customersJson, null, 4));
            
            console.log(`Customer added successfully to JSON. ID: ${customer.id}`);
            return true;
            
        } catch (error) {
            console.error(`Error adding customer to JSON: ${error.message}`);
            return false;
        }
    }

    async delete(customerId) {
        try {
            const customers = await this.readAll();
            const initialCount = customers.length;
            
            const filteredCustomers = customers.filter(c => c.id !== customerId);
            
            if (filteredCustomers.length < initialCount) {
                const customersJson = filteredCustomers.map(c => c.toJson());
                await fs.writeFile(this.filePath, JSON.stringify(customersJson, null, 4));
                
                console.log(`Customer ${customerId} deleted from JSON. Remaining: ${filteredCustomers.length}`);
                return true;
            }
            
            console.log(`Customer ${customerId} not found in JSON`);
            return false;
            
        } catch (error) {
            console.error(`Error deleting from JSON: ${error.message}`);
            return false;
        }
    }

    async update(customerId, customer) {
        try {
            const customers = await this.readAll();
            let updated = false;
            
            for (let i = 0; i < customers.length; i++) {
                if (customers[i].id === customerId) {
                    customer.id = customerId; 
                    customers[i] = customer;
                    updated = true;
                    break;
                }
            }
            
            if (updated) {
                const customersJson = customers.map(c => c.toJson());
                await fs.writeFile(this.filePath, JSON.stringify(customersJson, null, 4));
                
                console.log(`Customer ${customerId} updated in JSON`);
                return true;
            }
            
            console.log(`Customer ${customerId} not found for update in JSON`);
            return false;
            
        } catch (error) {
            console.error(`Error updating in JSON: ${error.message}`);
            return false;
        }
    }

    async readAll() {
        try {
            const data = await fs.readFile(this.filePath, 'utf8');
            if (!data || data.trim() === '') {
                return [];
            }
            
            const customersJson = JSON.parse(data);
            const customers = customersJson.map(c => Customer.fromJson(c));
            
            console.log(`Loaded ${customers.length} customers from JSON`);
            return customers;
            
        } catch (error) {
            console.error(`Error reading JSON: ${error.message}`);
            return [];
        }
    }

    async readById(customerId) {
        const customers = await this.readAll();
        return customers.find(c => c.id === customerId) || null;
    }

    getFormatName() {
        return "JSON";
    }
}