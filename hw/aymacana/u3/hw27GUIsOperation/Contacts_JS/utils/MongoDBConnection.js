// utils/MongoDBConnection.js
import { MongoClient } from 'mongodb';

class MongoDBConnection {
    static instance;
    static client;
    static database;

    static async getConnection() {
        if (!this.database) {
            try {
                const uri = "mongodb+srv://Mateo:Mateo2006@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0";
                
                this.client = new MongoClient(uri);
                await this.client.connect();
                
                this.database = this.client.db("TestDB");
                
                await this.database.command({ ping: 1 });
                console.log("✅ Conectado a MongoDB");
                
                return this.database;
                
            } catch (error) {
                console.error("Error de conexión a MongoDB:", error);
                throw error;
            }
        }
        return this.database;
    }

    static async closeConnection() {
        if (this.client) {
            await this.client.close();
            this.database = null;
            this.client = null;
            console.log("Conexión cerrada");
        }
    }

    static isConnected() {
        return this.database !== null;
    }
}

export default MongoDBConnection;