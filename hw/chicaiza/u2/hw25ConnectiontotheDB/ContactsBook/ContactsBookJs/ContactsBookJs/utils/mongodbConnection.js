const { MongoClient } = require("mongodb");

let db;

async function connect() {
    const uri = "mongodb+srv://daniel:daniel2007@cluster0.v7buh9x.mongodb.net/?retryWrites=true&w=majority";

    try {
        const client = new MongoClient(uri);
        await client.connect();
        db = client.db("ContactsDB");
        console.log("Connected to ContactsDB");
    } catch (err) {
        console.error("Mongo connection error:", err);
    }
}

function getDb() {
    return db;
}

module.exports = { connect, getDb };
