class DataManager {
    constructor(strategy) {
        this.strategy = strategy;
    }

    setStrategy(strategy) {
        this.strategy = strategy;
    }

    async create(store) { return await this.strategy.create(store); }
    async find(id) { return await this.strategy.find(id); }
    async update(id, store) { return await this.strategy.update(id, store); }
    async delete(id) { return await this.strategy.delete(id); }
    async loadAll() { return await this.strategy.loadAll(); }
}

module.exports = DataManager;