const Notebook = require("../models/Notebook");

const createNotebook = async (req, res) => {
    try {
        console.log(" BODY RECIBIDO:", req.body); 

        const notebook = new Notebook(req.body);
        await notebook.save();

        console.log(" NOTEBOOK GUARDADO EN ATLAS");

        res.status(201).json({ message: "Notebook guardado" });
    } catch (error) {
        console.error(" ERROR AL GUARDAR:", error);
        res.status(500).json({ error: error.message });
    }
};

const getNotebooks = async (req, res) => {
    const notebooks = await Notebook.find();
    res.json(notebooks);
};

module.exports = { createNotebook, getNotebooks };


