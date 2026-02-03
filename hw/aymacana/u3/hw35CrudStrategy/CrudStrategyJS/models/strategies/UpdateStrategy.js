export class UpdateStrategy {
    constructor(storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    async execute(customerId, updatedCustomer) {
        const existingCustomer = await this.storageStrategy.readById(customerId);
        if (!existingCustomer) {
            console.log(`Error: ID ${customerId} not found in ${this.storageStrategy.getFormatName()}`);
            return false;
        }
        
        updatedCustomer.id = customerId; // Keep the same ID
        return await this.storageStrategy.update(customerId, updatedCustomer);
    }

    setStorageStrategy(storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    getStorageFormatName() {
        return this.storageStrategy.getFormatName();
    }
}