const db = require('./Database');

class ProfessorModel {
    calculateBonus(salary) {
        return parseFloat(salary) * 0.15;
    }

    async insertProfessor(name, idNum, department, salary) {
        const collection = await db.getCollection("professors");
        const bonus = this.calculateBonus(salary);
        const data = {
            name: name,
            id_number: idNum,
            subject: department, 
            base_salary: parseFloat(salary),
            bonus: parseFloat(bonus.toFixed(2)) 
        };
        const result = await collection.insertOne(data);
       
        return result;
    }

    async getAll() {
        const collection = await db.getCollection("professors");
        const professors = await collection.find({}).toArray();
        
        return professors;
    }
}

module.exports = ProfessorModel;