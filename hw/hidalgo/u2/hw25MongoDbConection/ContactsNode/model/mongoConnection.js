const { MongoClient } = require("mongodb");

const uri = "mongodb+srv://Mikael:Mikael1897@cluster0.fpyoe9m.mongodb.net/MiAplicacionDB?appName=Cluster0";
const DATABASE_NAME = "MiAplicacionDB"; // Usamos el nombre de tu base de datos

let client = null;

async function getDatabase() {
  if (!client) {

    client = new MongoClient(uri); 
    await client.connect();
  }
  
  return client.db(DATABASE_NAME);
}

async function closeConnection() {
  if (client) {
    await client.close();
    client = null;
  }
}

module.exports = { getDatabase, closeConnection };