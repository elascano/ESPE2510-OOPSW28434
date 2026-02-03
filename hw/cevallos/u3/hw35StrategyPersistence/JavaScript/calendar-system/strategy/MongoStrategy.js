const StorageStrategy = require('./StorageStrategy');
const Event = require('../model/Event');
const MongoConnection = require('../utils/MongoConnection');

class MongoStrategy extends StorageStrategy {
    constructor(collectionName = 'events') {
        super();
        this.collectionName = collectionName;
        this.collection = null;
        this.initConnection();
    }

    async initConnection() {
        this.collection = await MongoConnection.getCollection(this.collectionName);
    }

    async addEvent(event) {
        try {
            if (!this.collection) {
                await this.initConnection();
            }
            
            const result = await this.collection.insertOne(event.toObject());
            return result.insertedId !== null;
        } catch (error) {
            console.error('Error agregando evento a MongoDB:', error.message);
            return false;
        }
    }

    async updateEvent(event) {
        try {
            if (!this.collection) {
                await this.initConnection();
            }
            
            const result = await this.collection.updateOne(
                { id: event.id },
                { $set: event.toObject() },
                { upsert: true }
            );
            return result.modifiedCount > 0 || result.upsertedCount > 0;
        } catch (error) {
            console.error('Error actualizando evento en MongoDB:', error.message);
            return false;
        }
    }

    async deleteEvent(id) {
        try {
            if (!this.collection) {
                await this.initConnection();
            }
            
            const result = await this.collection.deleteOne({ id: id });
            return result.deletedCount > 0;
        } catch (error) {
            console.error('Error eliminando evento de MongoDB:', error.message);
            return false;
        }
    }

    async readEvent(id) {
        try {
            if (!this.collection) {
                await this.initConnection();
            }
            
            const result = await this.collection.findOne({ id: id });
            if (result) {
                // Remover _id de MongoDB
                const { _id, ...eventData } = result;
                return Event.fromObject(eventData);
            }
            return null;
        } catch (error) {
            console.error('Error leyendo evento de MongoDB:', error.message);
            return null;
        }
    }

    async getAllEvents() {
        try {
            if (!this.collection) {
                await this.initConnection();
            }
            
            const cursor = this.collection.find();
            const results = await cursor.toArray();
            return results.map(doc => {
                const { _id, ...eventData } = doc;
                return Event.fromObject(eventData);
            });
        } catch (error) {
            console.error('Error obteniendo todos los eventos de MongoDB:', error.message);
            return [];
        }
    }
}

module.exports = MongoStrategy;