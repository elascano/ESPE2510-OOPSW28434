const express = require("express");
const cors = require("cors");
const { MongoClient } = require("mongodb");

const app = express();
app.use(cors());
app.use(express.json());
app.use(express.static("public"));

const url = "mongodb+srv://thais:thais@cluster0.9yfzmcp.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
const client = new MongoClient(url);

app.post("/api/saveContact", async (req, res) => {
    try {
        await client.connect();

        const db = client.db("ContactsDB");     
        const collection = db.collection("Contacts");

        await collection.insertOne(req.body);

        res.json({ ok: true, message: "Guardado con éxito" });

    } catch (err) {
        console.error("Error:", err);
        res.status(500).json({ error: err.toString() });
    }
});

app.listen(3000, () => {
    console.log("Servidor corriendo en http://localhost:3000");
});
