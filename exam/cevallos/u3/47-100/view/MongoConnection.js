const { MongoClient } = require('mongodb');

class MongoConnection {
    static instance = null;
    static client = null;
    static database = null;

    static async connect() {
        if (!MongoConnection.client) {
            try {
                const uri = 'mongodb+srv://Mateo:<db_password>@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0';
                MongoConnection.client = new MongoClient(uri);

                await MongoConnection.client.connect();
                MongoConnection.database = MongoConnection.client.db('strategyCevallos');

                console.log('connected');
            } catch (error) {
                console.error('Error conecting to MongoDB:', error.message);
                throw error;
            }
        }
        return MongoConnection.database;
    }

    static async getCollection(collectionName) {
        if (!MongoConnection.database) {
            await MongoConnection.connect();
        }
        return MongoConnection.database.collection(collectionName);
    }

    static async close() {
        if (MongoConnection.client) {
            await MongoConnection.client.close();
            MongoConnection.client = null;
            MongoConnection.database = null;
            console.log('closed');
        }
    }

    static getStatus() {
        return {
            connected: !!MongoConnection.client,
            database: MongoConnection.database ? MongoConnection.database.databaseName : null
        };
    }
}

module.exports = MongoConnection;