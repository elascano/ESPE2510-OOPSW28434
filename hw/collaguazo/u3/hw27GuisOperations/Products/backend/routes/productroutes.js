const express = require("express");
const router = express.Router();
const Product = require("../models/Product");

// GET /api/products → devuelve todos los products
router.get("/", async (req, res) => {
    try {
        const products = await Product.find();
        res.json(products);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// POST /api/products → crea un nuevo product
router.post("/", async (req, res) => {
    try {
        const product = new Product(req.body);
        const saved = await product.save();
        res.status(201).json(saved);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// DELETE /api/products/:id → elimina un product por id
router.delete("/:id", async (req, res) => {
    try {
        const product = await Product.findById(req.params.id);
        if (!product) {
            return res.status(404).json({ message: "Product no encontrado" });
        }

        await product.deleteOne();
        res.json({ message: "Product eliminado" });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

module.exports = router;
