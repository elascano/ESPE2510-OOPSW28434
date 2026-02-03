import fs from 'fs/promises';
import path from 'path';
import { Customer } from '../Customer.js';
import { CrudStrategy } from './CrudStrategy.js';

export class CsvStrategy extends CrudStrategy {
    constructor() {
        super();
        this.filePath = path.join(process.cwd(), 'data', 'customers.csv');
        this.ensureFileExists();
    }

    async ensureFileExists() {
        try {
            const dirPath = path.dirname(this.filePath);
            await fs.mkdir(dirPath, { recursive: true });
            
            try {
                await fs.access(this.filePath);
            } catch {
                await fs.writeFile(this.filePath, '');
            }
        } catch (error) {
            console.error(`Error ensuring CSV file exists: ${error.message}`);
        }
    }

    async add(customer) {
        try {
            const customers = await this.readAll();
            
            const existingCustomer = customers.find(c => c.id === customer.id);
            if (existingCustomer) {
                console.log(`Error: ID ${customer.id} already exists in CSV`);
                return false;
            }
            
            const csvLine = customer.toCsv();
            await fs.appendFile(this.filePath, csvLine + '\n');
            
            console.log(`Customer added successfully to CSV. ID: ${customer.id}`);
            return true;
            
        } catch (error) {
            console.error(`Error adding customer to CSV: ${error.message}`);
            return false;
        }
    }

    async delete(customerId) {
        try {
            const customers = await this.readAll();
            const initialCount = customers.length;
            
            const filteredCustomers = customers.filter(c => c.id !== customerId);
            
            if (filteredCustomers.length < initialCount) {
                const content = filteredCustomers.map(c => c.toCsv()).join('\n');
                await fs.writeFile(this.filePath, content);
                
                console.log(`Customer ${customerId} deleted from CSV. Remaining: ${filteredCustomers.length}`);
                return true;
            }
            
            console.log(`Customer ${customerId} not found in CSV`);
            return false;
            
        } catch (error) {
            console.error(`Error deleting from CSV: ${error.message}`);
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
                const content = customers.map(c => c.toCsv()).join('\n');
                await fs.writeFile(this.filePath, content);
                
                console.log(`Customer ${customerId} updated in CSV`);
                return true;
            }
            
            console.log(`Customer ${customerId} not found for update in CSV`);
            return false;
            
        } catch (error) {
            console.error(`Error updating in CSV: ${error.message}`);
            return false;
        }
    }

    async readAll() {
        try {
            const data = await fs.readFile(this.filePath, 'utf8');
            if (!data || data.trim() === '') {
                return [];
            }
            
            const lines = data.trim().split('\n');
            const customers = [];
            
            for (const line of lines) {
                if (line.trim()) {
                    const customer = Customer.fromCsv(line);
                    if (customer) {
                        customers.push(customer);
                    }
                }
            }
            
            console.log(`Loaded ${customers.length} customers from CSV`);
            return customers;
            
        } catch (error) {
            console.error(`Error reading CSV: ${error.message}`);
            return [];
        }
    }

    async readById(customerId) {
        const customers = await this.readAll();
        return customers.find(c => c.id === customerId) || null;
    }

    getFormatName() {
        return "CSV";
    }
}