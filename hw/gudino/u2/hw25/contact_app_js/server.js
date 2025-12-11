const express = require("express");
const { MongoClient } = require("mongodb");
const bodyParser = require("body-parser");
const path = require("path");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(bodyParser.json());

const uri = "mongodb+srv://Bryan:Bryan2000@cluster0.sx9cpnq.mongodb.net/?retryWrites=true&w=majority";

// Base de datos y colección
const dbName = "ContactsDB";
const collectionName = "contacts";

let collection;

// Sirve la interfaz
app.use(express.static(path.join(__dirname, "public")));

// Conectar a MongoDB
MongoClient.connect(uri)
  .then(client => {
    console.log("Conectado a MongoDB Atlas");
    const db = client.db(dbName);
    collection = db.collection(collectionName);
  })
  .catch(err => console.error("Error conectando MongoDB:", err));

// API para guardar contacto
app.post("/save", async (req, res) => {
  if (!collection) {
    return res.status(500).json({ success: false, message: "DB no conectada" });
  }

  try {
    const {
      name,
      birth_date,
      day,
      month,
      year,
      age,
      contact_type,
      sex,
      hobbies,
      comments
    } = req.body;

    const result = await collection.insertOne({
      name,
      birth_date,
      birth: { day, month, year },
      age: parseInt(age) || 0,
      contact_type,
      sex,
      hobbies,
      comments
    });

    res.json({ success: true, id: result.insertedId });
  } catch (err) {
    console.error("Error al guardar:", err);
    res.status(500).json({ success: false, error: err.message });
  }
});

// Ver contactos
app.get("/contacts", async (req, res) => {
  if (!collection) return res.json([]);
  const data = await collection.find().toArray();
  res.json(data);
});

// Iniciar servidor
const PORT = 3000;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en: http://localhost:${PORT}`);
});
