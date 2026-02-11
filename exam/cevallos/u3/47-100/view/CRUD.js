import MongoDBConnection from 'MongoDBConnection.js';

class SimpleCrud {
    constructor(collectionName) {
        this.collectionName = collectionName;
        this.collection = null;
    }

    async init() {
        if (!this.collection) {
            const db = await MongoDBConnection.getConnection();
            this.collection = db.collection(this.collectionName);
            console.log(`initialized: ${this.collectionName}`);
        }
    }


    async create(document) {
        await this.init();
        const result = await this.collection.insertOne(document);
        console.log(`created id: ${document.id}`);
        return document.id;
    }
}
export default SimpleCrud;