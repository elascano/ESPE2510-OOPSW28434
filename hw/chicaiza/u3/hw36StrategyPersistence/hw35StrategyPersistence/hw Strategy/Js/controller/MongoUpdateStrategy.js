const UpdateStrategy = require('./UpdateStrategy');

class MongoUpdateStrategy extends UpdateStrategy {
    async update(collection, cellphone) {
        // Strategy implementation
        await collection.updateOne(
            { id: cellphone.id },
            { $set: { model: cellphone.model, price: cellphone.price } }
        );
    }
}
module.exports = MongoUpdateStrategy;