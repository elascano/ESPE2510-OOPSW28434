export class AddStrategy {
    constructor(storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    async execute(customer) {
        const existingCustomers = await this.storageStrategy.readAll();
        const existingCustomer = existingCustomers.find(c => c.id === customer.id);
        
        if (existingCustomer) {
            console.log(`Error: ID ${customer.id} already exists in ${this.storageStrategy.getFormatName()}`);
            return false;
        }
        
        return await this.storageStrategy.add(customer);
    }

    setStorageStrategy(storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    getStorageFormatName() {
        return this.storageStrategy.getFormatName();
    }
}