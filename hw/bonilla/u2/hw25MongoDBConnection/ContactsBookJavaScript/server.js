const express = require("express");
const app = express();
const path = require("path");
const { MongoClient } = require("mongodb");

app.use(express.json());
app.use(express.static(__dirname));

const password = encodeURIComponent("Arelis2006");
const uri = `mongodb+srv://Arelis:${password}@cluster0.qdn4zsf.mongodb.net/ContactsBook?retryWrites=true&w=majority&appName=Cluster0`;

const client = new MongoClient(uri, { serverSelectionTimeoutMS: 10000 });
let collection = null;
let connected = false;

async function connectDB() {
  try {
    await client.connect();
    const db = client.db("ContactsBook");
    collection = db.collection("ContactsJS");
    connected = true;
    console.log("MongoDB connected");
  } catch (error) {
    console.error("Error connecting to MongoDB:", error.message || error);
  }
}
connectDB();

app.use((req, res, next) => {
  if (!connected && req.path !== "/health") {
    return res.status(503).json({ error: "Service unavailable - DB not connected yet" });
  }
  next();
});

app.get("/health", (req, res) => {
  res.json({ status: connected ? "ok" : "connecting" });
});

app.get("/api/contacts", async (req, res) => {
  try {
    const docs = await collection.find({}).limit(100).toArray();
    res.json(docs);
  } catch (err) {
    res.status(500).json({ error: "Failed to list contacts" });
  }
});

app.post("/api/saveContact", async (req, res) => {
  try {
    const result = await collection.insertOne(req.body);
    res.json({ message: "Contact saved", insertedId: result.insertedId });
  } catch (error) {
    res.status(500).json({ error: "Failed to save contact", details: error.message });
  }
});

app.listen(3000, () => {
  console.log("Server running on http://localhost:3000");
});