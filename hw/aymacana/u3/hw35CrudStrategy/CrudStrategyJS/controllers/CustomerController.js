import { CrudContext } from '../models/CrudContext.js';
import { Customer } from '../models/Customer.js';
import { ValidationController } from './ValidationController.js';
import { IdGenerator } from '../utils/IdGenerator.js';

export class CustomerController {
    constructor(storageType = "JSON") {
        this.crudContext = new CrudContext(storageType);
    }

    setStorageType(storageType) {
        this.crudContext.setStorageStrategy(storageType);
    }

    getCurrentStorageType() {
        return this.crudContext.getCurrentStorageType();
    }

    async createCustomer(name, apartmentNumber, email, phone) {
        const validationErrors = ValidationController.validateAllFields(
            name, apartmentNumber, email, phone
        );
        
        if (validationErrors) {
            throw new Error(`Validation errors:\n${validationErrors}`);
        }
        
        const customerId = await IdGenerator.generateUniqueId();
        return new Customer(customerId, name, apartmentNumber, email, phone);
    }

    async addCustomer(customer) {
        return await this.crudContext.addCustomer(customer);
    }

    async addCustomerFromFields(name, apartmentNumber, email, phone) {
        try {
            const customer = await this.createCustomer(name, apartmentNumber, email, phone);
            return await this.addCustomer(customer);
        } catch (error) {
            throw error;
        }
    }

    async deleteCustomer(customerId) {
        return await this.crudContext.deleteCustomer(customerId);
    }

    async updateCustomer(customerId, name, apartmentNumber, email, phone) {
        try {
            const validationErrors = ValidationController.validateAllFields(
                name, apartmentNumber, email, phone
            );
            
            if (validationErrors) {
                throw new Error(`Validation errors:\n${validationErrors}`);
            }
            
            const updatedCustomer = new Customer(customerId, name, apartmentNumber, email, phone);
            return await this.crudContext.updateCustomer(customerId, updatedCustomer);
        } catch (error) {
            throw error;
        }
    }

    async getAllCustomers() {
        return await this.crudContext.getAllCustomers();
    }

    async getCustomerById(customerId) {
        return await this.crudContext.getCustomerById(customerId);
    }

    async getAllCustomersFormatted() {
        const customers = await this.getAllCustomers();
        let result = `=== CUSTOMERS (${this.getCurrentStorageType()}) ===\n`;
        
        if (customers.length === 0) {
            result += "No customers found.\n";
        } else {
            for (const customer of customers) {
                result += `${customer.toString()}\n`;
            }
            result += `Total: ${customers.length} customers\n`;
        }
        
        return result;
    }

    async getAllCustomersFromAllFormats() {
        const formats = ["JSON", "CSV", "MongoDB"];
        let allCustomers = [];
        
        for (const format of formats) {
            this.setStorageType(format);
            const customers = await this.getAllCustomers();
            allCustomers = allCustomers.concat(
                customers.map(c => ({
                    ...c,
                    source: format
                }))
            );
        }
        
        return allCustomers;
    }
}