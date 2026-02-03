const getCollection = require('../utils/MongoConnection');
const Cellphone = require('../model/Cellphone');

class CellphoneController {
    constructor(strategy = null) {
        this.strategy = strategy; // Set strategy
    }

    async create(cellphone) {
        const col = await getCollection();
        await col.insertOne({
            id: cellphone.id,
            model: cellphone.model,
            price: cellphone.price
        });
    }

    async findAll() {
        const col = await getCollection();
        return await col.find({}, { projection: { _id: 0 } }).toArray();
    }

    async findById(id) {
        const col = await getCollection();
        const data = await col.findOne({ id: id }, { projection: { _id: 0 } });
        return data ? new Cellphone(data.id, data.model, data.price) : null;
    }

    async update(cellphone) {
        const col = await getCollection();
        if (this.strategy) {
            // Apply strategy
            await this.strategy.update(col, cellphone);
        } else {
            await col.updateOne(
                { id: cellphone.id },
                { $set: { model: cellphone.model, price: cellphone.price } }
            );
        }
    }

    async delete(id) {
        const col = await getCollection();
        await col.deleteOne({ id: String(id) });
    }
}
module.exports = CellphoneController;