const { MongoClient } = require('mongodb');

async function getCollection() {
    const client = new MongoClient("mongodb://localhost:27017");
    await client.connect();
    const db = client.db("cellphoneStore");
    return db.collection("cellphones");
}
module.exports = getCollection;