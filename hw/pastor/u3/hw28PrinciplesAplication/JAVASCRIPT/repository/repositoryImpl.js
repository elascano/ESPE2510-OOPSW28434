const IGenericRepository = require('./iRepository');
const database = require('../config/database');
const GenericEntity = require('../model/genericEntity');

class RepositoryImpl extends IGenericRepository {
    constructor(collectionName) {
        super();
        this.collectionName = collectionName;
    }

    async create(entity) {
        const db = await database.connect();
        await db.collection(this.collectionName).insertOne(entity.toDocument());
    }

    async readAll() {
        const db = await database.connect();
        const docs = await db.collection(this.collectionName).find({}).toArray();
        
        return docs.map(doc => GenericEntity.fromDocument(doc, this.collectionName));
    }
}

module.exports = RepositoryImpl;