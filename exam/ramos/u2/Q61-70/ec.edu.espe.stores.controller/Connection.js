const { MongoClient } = require('mongodb');

const uri = "mongodb+srv://Paulo:paulo2004@cluster0.9uxqgih.mongodb.net/Stores?retryWrites=true&w=majority";
const client = new MongoClient(uri);

async function conectarDB() {
  try {
    await client.connect();
    console.log("Connected to mongo");
    const db = client.db("Stores");

    async function Delete() {
    await client.connect();
    const db = client.db("miDB");
    const usuarios = db.collection("stores");

    const result = await usuarios.deleteOne({ id: new ObjectId("1") });
    console.log(result.deletedCount);

    await client.close();
}

Delete();
  } catch (e) {
    console.error("Error al conectar:", e);
  } finally {
    await client.close();
  }
}

conectarDB();