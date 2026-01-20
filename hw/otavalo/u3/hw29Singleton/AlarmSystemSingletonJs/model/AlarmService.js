const Database = require('../controller/Database');

class AlarmService {
    static #instance = null;
    #minStock = 0;

    constructor() {
        if (AlarmService.#instance) return AlarmService.#instance;
        AlarmService.#instance = this;
    }

    static async getInstance() {
        if (!this.#instance) {
            this.#instance = new AlarmService();
            await this.#instance.loadMinStockFromDb();
        }
        return this.#instance;
    }

    async loadMinStockFromDb() {
        try {
            const db = await Database.getDatabase();
            const config = db.collection("Config");
            let doc = await config.findOne({ type: "alarm_config" });

            if (!doc) {
                await config.insertOne({ type: "alarm_config", minStock: 10 });
                this.#minStock = 10;
            } else {
                this.#minStock = doc.minStock;
            }
            return this.#minStock;
        } catch (error) {
            console.error("Database connection failed:", error);
            this.#minStock = 10; // Valor por defecto si falla la red
        }
    }

    async updateMinStock(newStock) {
        this.#minStock = newStock;
        const db = await Database.getDatabase();
        await db.collection("Config").updateOne(
            { type: "alarm_config" },
            { $set: { minStock: newStock } },
            { upsert: true }
        );
    }

    isLowStock(product) {
        return product.stock < this.#minStock;
    }

    getMinStock() {
        return this.#minStock;
    }
}

module.exports = AlarmService;