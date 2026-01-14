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
            console.log(`Repositorio inicializado: ${this.collectionName}`);
        }
    }

    // CREATE
    async create(document) {
        await this.init();
        const result = await this.collection.insertOne(document);
        console.log(` CREADO con ID: ${document.id}`);
        return document.id;
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