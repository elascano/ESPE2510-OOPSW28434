const db = require('./Database');

class PatientModel {
    async getAll() {
    
        const collection = await db.getCollection("Patient");
        return await collection.find({}).toArray();
    }
}

module.exports = PatientModel;