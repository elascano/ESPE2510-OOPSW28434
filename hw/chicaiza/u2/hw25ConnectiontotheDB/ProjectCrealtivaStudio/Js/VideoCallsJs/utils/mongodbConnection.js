const { MongoClient } = require("mongodb");

let db;

async function connect() {
    const uri = "mongodb+srv://daniel:daniel2007@cluster0.v7buh9x.mongodb.net/CrealtivaStudioDB?retryWrites=true&w=majority";

    try {
        const client = new MongoClient(uri);
        await client.connect();
        db = client.db("CrealtivaStudioDB");
        console.log("Conectado a MongoDB Atlas");
    } catch (err) {
        console.error("Error conectando a MongoDB:", err);
    }
}

function getDb() {
    return db;
}

module.exports = { connect, getDb };
