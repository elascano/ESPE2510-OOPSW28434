const db = require('./Database');
const IComputersWarehouse = require('./IComputersWarehouse');

class Computer extends IComputersWarehouse {

    async insert(computerData) {
        try {
            const collection = await db.getCollection("computers");
            return await collection.insertOne(computerData);
        } catch (error) {
            console.error("Insert error:", error);
            throw error;
        }
    }

    async getAll() {
        try {
            const collection = await db.getCollection("computers");
            return await collection.find({}).toArray();
        } catch (error) {
            console.error("GetAll error:", error);
            return [];
        }
    }
}

module.exports = Computer;
