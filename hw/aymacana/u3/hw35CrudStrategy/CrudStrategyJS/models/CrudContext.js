import { JsonStrategy } from './strategies/JsonStrategy.js';
import { CsvStrategy } from './strategies/CsvStrategy.js';
import { MongoStrategy } from './strategies/MongoStrategy.js';
import { AddStrategy } from './strategies/AddStrategy.js';
import { DeleteStrategy } from './strategies/DeleteStrategy.js';
import { UpdateStrategy } from './strategies/UpdateStrategy.js';
import { ReadStrategy } from './strategies/ReadStrategy.js';

export class CrudContext {
    constructor(storageType = "JSON") {
        this.storageStrategy = this.createStorageStrategy(storageType);
        this.addStrategy = new AddStrategy(this.storageStrategy);
        this.deleteStrategy = new DeleteStrategy(this.storageStrategy);
        this.updateStrategy = new UpdateStrategy(this.storageStrategy);
        this.readStrategy = new ReadStrategy(this.storageStrategy);
    }

    createStorageStrategy(storageType) {
        const type = storageType.toUpperCase();
        
        switch(type) {
            case "JSON":
                return new JsonStrategy();
            case "CSV":
                return new CsvStrategy();
            case "MONGO":
            case "MONGODB":
                return new MongoStrategy();
            default:
                throw new Error(`Unsupported storage type: ${storageType}`);
        }
    }

    setStorageStrategy(storageType) {
        this.storageStrategy = this.createStorageStrategy(storageType);
        this.addStrategy.setStorageStrategy(this.storageStrategy);
        this.deleteStrategy.setStorageStrategy(this.storageStrategy);
        this.updateStrategy.setStorageStrategy(this.storageStrategy);
        this.readStrategy.setStorageStrategy(this.storageStrategy);
    }

    async addCustomer(customer) {
        return await this.addStrategy.execute(customer);
    }

    async deleteCustomer(customerId) {
        return await this.deleteStrategy.execute(customerId);
    }

    async updateCustomer(customerId, customer) {
        return await this.updateStrategy.execute(customerId, customer);
    }

    async getAllCustomers() {
        return await this.readStrategy.execute();
    }

    async getCustomerById(customerId) {
        return await this.readStrategy.executeById(customerId);
    }

    getCurrentStorageType() {
        return this.addStrategy.getStorageFormatName();
    }
}