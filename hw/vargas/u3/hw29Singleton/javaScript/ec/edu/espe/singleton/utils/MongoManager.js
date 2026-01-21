import { MongoClient, ObjectId } from 'mongodb';

export class MongoManager {
    constructor() {
        const uri = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/";
        this.client = new MongoClient(uri);
        this.database = null;
    }

    async connect() {
        if (!this.database) {
            try {
                await this.client.connect();
                this.database = this.client.db("toamedicalDB");
            } catch (e) {
                console.error("Error conectando a MongoDB:", e);
            }
        }
    }

    async insert(collectionName, document) {
        await this.connect();
        const collection = this.database.collection(collectionName);
        await collection.insertOne(document);
        console.log(`Documento insertado correctamente en: ${collectionName}`);
    }

    async find(collectionName, filter = {}) {
        await this.connect();
        const collection = this.database.collection(collectionName);
        return await collection.find(filter).toArray();
    }

    async getEmail(collectionName, idFieldName, idValue) {
        await this.connect();
        const collection = this.database.collection(collectionName);
        const result = await collection.findOne({ [idFieldName]: idValue });
        
        return result?.email || null;
    }

    async getInfo(collectionName, searchField, searchValue, targetField) {
        await this.connect();
        try {
            const collection = this.database.collection(collectionName);
            const result = await collection.findOne({ [searchField]: searchValue });
            
            return result && result[targetField] ? String(result[targetField]) : null;
        } catch (e) {
            console.error(e);
            return null;
        }
    }

    createDateDocument(dateJs, hour, minute) {
        if (!dateJs) return null;

        const pad = (n) => String(n).padStart(2, '0');
        return {
            date: dateJs.toISOString().split('T')[0],
            day: dateJs.getDate(),
            month: dateJs.getMonth() + 1, 
            year: dateJs.getFullYear(),
            hour: hour,
            minute: minute,
            time: `${pad(hour)}:${pad(minute)}`
        };
    }

    async close() {
        if (this.client) {
            await this.client.close();
            console.log("Conexión cerrada.");
        }
    }
}