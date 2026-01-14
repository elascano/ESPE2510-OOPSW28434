import { MongoClient } from 'mongodb';

export class ConnectionDB {
    constructor() {
        this.uri = "mongodb+srv://Josue:Josue2006@cluster0.da07rsq.mongodb.net/?appName=Cluster0";
        this.client = new MongoClient(this.uri);
        this.db = null;
        this.collection = null;
    }

    async connect() {
        await this.client.connect();
        this.db = this.client.db("tienda_db");
        this.collection = this.db.collection("productos");
    }

    async getAll() {
        return await this.collection.find({}).toArray();
    }

    async insert(productData) {
        return await this.collection.insertOne(productData);
    }

    // NUEVO: Método para eliminar
    async deleteById(id) {
        return await this.collection.deleteOne({ _id: id });
    }

    // NUEVO: Método para actualizar
    // Dentro de la clase ConnectionDB en controller/ConnectionDB.js
    async updateById(id, newData) {
    // Es vital que el filtro sea { _id: id }
    // Usamos $set para que no borre los demás campos del producto
    const result = await this.collection.updateOne(
        { _id: id }, 
        { $set: newData }
    );
    return result;
    }

    async close() {
        await this.client.close();
    }
}