import { MongoClient } from "mongodb";
import { Config } from "./Config.js";

export class MongoDBManager {
    constructor() {
        this.client = new MongoClient(Config.mongoUri);
        this.db = null;
        this.collection = null;
    }

    async connect() {
        if (!this.db) {
            await this.client.connect();
            this.db = this.client.db(Config.dbName);
            this.collection = this.db.collection(Config.collectionName);
        }
    }

    async getNextId() {
        await this.connect();

        const last = await this.collection
            .find({})
            .sort({ id: -1 })
            .limit(1)
            .toArray();

        if (last.length === 0) {
            return "1";
        }

        return (parseInt(last[0].id) + 1).toString();
    }

    async findAll() {
        await this.connect();
        return await this.collection.find({}).toArray();
    }

    async findById(id) {
        await this.connect();
        return await this.collection.findOne({ id: id });
    }

    async insert(entity) {
        await this.connect();
        return await this.collection.insertOne(entity);
    }

    async update(id, entity) {
        await this.connect();
        return await this.collection.updateOne(
            { id: id },
            { $set: entity }
        );
    }

    async delete(id) {
        await this.connect();
        return await this.collection.deleteOne({ id: id });
    }
}