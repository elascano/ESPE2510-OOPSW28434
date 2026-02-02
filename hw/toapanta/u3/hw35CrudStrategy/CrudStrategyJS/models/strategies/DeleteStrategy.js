export class DeleteStrategy {
    constructor(storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    async execute(customerId) {
        const customer = await this.storageStrategy.readById(customerId);
        if (!customer) {
            console.log(`Error: ID ${customerId} not found in ${this.storageStrategy.getFormatName()}`);
            return false;
        }
        
        return await this.storageStrategy.delete(customerId);
    }

    setStorageStrategy(storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    getStorageFormatName() {
        return this.storageStrategy.getFormatName();
    }
}