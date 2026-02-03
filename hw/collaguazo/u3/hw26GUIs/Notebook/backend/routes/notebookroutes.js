const express = require("express");
const router = express.Router();
const Notebook = require("../models/Notebook");

// GET /api/notebooks → devuelve todos los notebooks
router.get("/", async (req, res) => {
    try {
        const notebooks = await Notebook.find();
        res.json(notebooks);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// POST /api/notebooks → crea un nuevo notebook
router.post("/", async (req, res) => {
    try {
        const notebook = new Notebook(req.body);
        const saved = await notebook.save();
        res.status(201).json(saved);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// DELETE /api/notebooks/:id → elimina un notebook por id
router.delete("/:id", async (req, res) => {
    try {
        const notebook = await Notebook.findById(req.params.id);
        if (!notebook) return res.status(404).json({ message: "Notebook no encontrado" });

        await notebook.remove();
        res.json({ message: "Notebook eliminado" });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

module.exports = router;
