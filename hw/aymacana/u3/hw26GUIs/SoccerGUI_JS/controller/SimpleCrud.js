// controller/SimpleCrud.js
import MongoDBConnection from '../utils/MongoDBConnection.js';

class SimpleCrud {
    constructor(collectionName) {
        this.collectionName = collectionName;
        this.collection = null;
    }

    async init() {
        if (!this.collection) {
            const db = await MongoDBConnection.getConnection();
            this.collection = db.collection(this.collectionName);
            console.log(`📁 Repositorio inicializado: ${this.collectionName}`);
        }
    }

    // CREATE
    async create(document) {
        await this.init();
        const result = await this.collection.insertOne(document);
        console.log(`✅ CREADO con ID: ${document.id}`);
        return document.id;
    }

    // READ
    async read(id) {
        await this.init();
        return await this.collection.findOne({ id: id });
    }

    async readAll() {
        await this.init();
        const cursor = this.collection.find().sort({ id: 1 });
        return await cursor.toArray();
    }

    // UPDATE
    async update(id, document) {
        await this.init();
        document.id = id; // Asegurar que el ID no cambie
        const result = await this.collection.replaceOne({ id: id }, document);
        const success = result.modifiedCount > 0;
        console.log(success ? `✅ ACTUALIZADO ID: ${id}` : `❌ No encontrado ID: ${id}`);
        return success;
    }

    // DELETE
    async delete(id) {
        await this.init();
        const result = await this.collection.deleteOne({ id: id });
        const success = result.deletedCount > 0;
        console.log(success ? `✅ ELIMINADO ID: ${id}` : `❌ No encontrado ID: ${id}`);
        return success;
    }

    // EXISTS
    async exists(id) {
        await this.init();
        const doc = await this.read(id);
        return doc !== null;
    }

    // COUNT
    async count() {
        await this.init();
        return await this.collection.countDocuments();
    }

    // FIND BY FIELD
    async findByField(fieldName, value) {
        await this.init();
        const cursor = this.collection.find({ [fieldName]: value });
        return await cursor.toArray();
    }

    // GET MAX ID
    async getMaxId() {
        await this.init();
        const result = await this.collection
            .aggregate([
                {
                    $group: {
                        _id: null,
                        maxId: { $max: "$id" }
                    }
                }
            ])
            .toArray();
        
        if (result.length > 0 && result[0].maxId) {
            return result[0].maxId;
        }
        return 0;
    }
}

export default SimpleCrud;