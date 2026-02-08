import { MongoClient } from "mongodb";

const uri = "mongodb+srv://Bryan:B2000@cluster0.sx9cpnq.mongodb.net/";
const client = new MongoClient(uri);

export async function saveResult(result) {
    try {
        await client.connect();
        const db = client.db("Prueba");
        const collection = db.collection("Pruebita");

        await collection.insertOne(result);
    } finally {
        await client.close();
    }
}
