import { MongoDBManager } from "../utils/MongoDBManager.js";

export class GenericController {
    constructor() {
        this.mongo = new MongoDBManager();
    }

    getNextId() {
        return this.mongo.getNextId();
    }

    getAll() {
        return this.mongo.findAll();
    }

    getById(id) {
        return this.mongo.findById(id);
    }

    create(entity) {
        return this.mongo.insert(entity);
    }

    update(id, entity) {
        return this.mongo.update(id, entity);
    }

    delete(id) {
        return this.mongo.delete(id);
    }
}