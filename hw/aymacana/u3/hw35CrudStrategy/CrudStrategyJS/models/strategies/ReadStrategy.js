export class ReadStrategy {
    constructor(storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    async execute() {
        return await this.storageStrategy.readAll();
    }

    async executeById(customerId) {
        return await this.storageStrategy.readById(customerId);
    }

    setStorageStrategy(storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    getStorageFormatName() {
        return this.storageStrategy.getFormatName();
    }
}