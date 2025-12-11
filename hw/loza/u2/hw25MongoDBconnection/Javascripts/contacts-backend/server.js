const express = require("express");
const cors = require("cors");
const { MongoClient } = require("mongodb");

const app = express();
app.use(cors());
app.use(express.json());


const uri = "mongodb+srv://Steven:Steven2001@cluster0.mp8muds.mongodb.net/?appName=Cluster0";  

const client = new MongoClient(uri);


const DB_NAME = "contactsdb";

app.post("/api/contacts", async (req, res) => {
    try {
        await client.connect();

       
        const db = client.db(DB_NAME);

        
        const collection = db.collection("contacts");

        const contact = req.body;

        const result = await collection.insertOne(contact);

        res.json({
            message: "Contacto guardado en MongoDB Atlas",
            id: result.insertedId
        });

    } catch (error) {
        console.error(error);
        res.status(500).json({ error: "Error al guardar en MongoDB" });
    }
});

// Servidor
app.listen(3000, () => {
    console.log("Servidor corriendo en http://localhost:3000");
});
