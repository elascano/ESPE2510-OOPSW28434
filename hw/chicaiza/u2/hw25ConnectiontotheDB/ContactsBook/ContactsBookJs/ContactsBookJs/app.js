const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");
const Contact = require("./model/Contact");
const { connect } = require("./utils/mongodbConnection");
const JsonManager = require("./utils/jsonManager"); // ← AGREGAR ESTO

const app = express();
const PORT = 3000;

app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, "view")));

connect();

// SAVE CONTACT
app.post("/add", async (req, res) => {
    console.log("REQ BODY:", req.body);

    const { firstName, lastName, birthDate, typeOfContact, sex, hobbies, comments } = req.body;

    try {
        const c = new Contact(firstName, lastName, birthDate, typeOfContact, sex, hobbies, comments);
        await c.save();                // Guardar en MongoDB
        JsonManager.saveContact(c);    // ← Guardar también en contacts.json

        res.json({ message: "Contact saved successfully." });
    } catch (err) {
        console.error(err);
        res.status(500).json({ message: "Error saving contact." });
    }
});

// GET CONTACTS
app.get("/getAll", async (req, res) => {
    try {
        const contacts = await Contact.getAll();
        res.json(contacts);
    } catch (err) {
        res.status(500).json({ message: "Error loading contacts." });
    }
});

// VIEW
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "view", "index.html"));
});

app.listen(PORT, () => console.log(`Server running at http://localhost:${PORT}`));
