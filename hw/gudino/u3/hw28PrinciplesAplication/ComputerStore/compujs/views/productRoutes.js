const express = require("express");

function productRoutes(controller) {
    const router = express.Router();

    router.post("/", async (req, res) => {
        try {
            const { name, make, basePrice } = req.body;

            const product =
                await controller.addProduct(
                    name,
                    make,
                    Number(basePrice)
                );

            res.status(201).json(product);
        } catch (e) {
            res.status(500).json({ error: e.message });
        }
    });

    router.get("/", async (req, res) => {
        try {
            res.json(await controller.getAllProducts());
        } catch (e) {
            res.status(500).json({ error: e.message });
        }
    });

    router.get("/total", async (req, res) => {
        try {
            res.json({ total: await controller.getTotalSum() });
        } catch (e) {
            res.status(500).json({ error: e.message });
        }
    });

    return router;
}

module.exports = productRoutes;
