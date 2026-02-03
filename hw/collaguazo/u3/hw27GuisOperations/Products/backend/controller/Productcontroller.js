const Product = require("../models/Product");

const createProduct = async (req, res) => {
    try {
        console.log(" BODY RECIBIDO:", req.body); 

        const product = new Product(req.body);
        await product.save();

        console.log(" PRODUCT GUARDADO EN ATLAS");

        res.status(201).json({ message: "Product guardado" });
    } catch (error) {
        console.error(" ERROR AL GUARDAR:", error);
        res.status(500).json({ error: error.message });
    }
};

const getProducts = async (req, res) => {
    const products = await Product.find();
    res.json(products);
};

module.exports = { createProduct, getProducts };
