const db = require('./Database');

class ProfessorModel {
    async getAll() {
        const collection = await db.getCollection("Professors");
        return await collection.find({}).toArray();
    }

}

module.exports = ProfessorModel;