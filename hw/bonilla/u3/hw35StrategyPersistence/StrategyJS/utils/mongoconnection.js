import { MongoClient } from 'mongodb';

class MongoConnection {
    static instance;

    constructor() {
        const uri = 'mongodb+srv://Arelis:Arelis2006@cluster0.qdn4zsf.mongodb.net';
        this.client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true });
        this.db = this.client.db('ParkingDB');
    }

    static async getInstance() {
        if (!MongoConnection.instance) {
            const conn = new MongoConnection();
            await conn.client.connect();
            console.log('MongoDB connection successful');
            MongoConnection.instance = conn;
        }
        return MongoConnection.instance;
    }

    getDatabase() {
        return this.db;
    }

    getCollection(name) {
        return this.db.collection(name);
    }
}

export default MongoConnection;