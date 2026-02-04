const MongoRepository = require("../model/mongoRepository");
const mongoose = require("mongoose");

class ProductController {
  constructor() {
    this.repo = new MongoRepository();
  }

  isValidId(id) {
    return mongoose.Types.ObjectId.isValid(id);
  }

  calcPriceWithIva(price, ivaRate) {
    return Number((price * (1 + ivaRate)).toFixed(2));
  }

  async create(req, res) {
    try {
      const { name, price, ivaRate } = req.body;

      if (!name || typeof name !== "string") {
        return res.status(400).json({ error: "name must be string." });
      }
      if (typeof price !== "number" || Number.isNaN(price)) {
        return res.status(400).json({ error: "price must be number." });
      }
      if (typeof ivaRate !== "number" || Number.isNaN(ivaRate)) {
        return res.status(400).json({ error: "ivaRate must be number." });
      }

      const priceWithIva = this.calcPriceWithIva(price, ivaRate);

      const saved = await this.repo.createProduct({
        name: name.trim(),
        price,
        ivaRate,
        priceWithIva
      });

      return res.status(201).json(saved);
    } catch (err) {
      console.error("CREATE ERROR:", err);
      return res.status(500).json({
        error: "Error creating product.",
        details: String(err?.message || err)
      });
    }
  }

  async getAll(req, res) {
    try {
      const products = await this.repo.getAllProducts();
      return res.json(products);
    } catch (err) {
      console.error("GET ALL ERROR:", err);
      return res.status(500).json({
        error: "Error getting products.",
        details: String(err?.message || err)
      });
    }
  }

  async getById(req, res) {
    try {
      const { id } = req.params;
      if (!this.isValidId(id)) {
        return res.status(400).json({ error: "ID invalid." });
      }

      const product = await this.repo.getProductById(id);
      if (!product) {
        return res.status(404).json({ error: "Product not found." });
      }

      return res.json(product);
    } catch (err) {
      console.error("GET BY ID ERROR:", err);
      return res.status(500).json({
        error: "Error getting product.",
        details: String(err?.message || err)
      });
    }
  }

  async update(req, res) {
    try {
      const { id } = req.params;
      if (!this.isValidId(id)) {
        return res.status(400).json({ error: "ID invalid." });
      }

      const { name, price, ivaRate } = req.body;

      if (!name || typeof name !== "string") {
        return res.status(400).json({ error: "name must be string." });
      }
      if (typeof price !== "number" || Number.isNaN(price)) {
        return res.status(400).json({ error: "price must be number." });
      }
      if (typeof ivaRate !== "number" || Number.isNaN(ivaRate)) {
        return res.status(400).json({ error: "ivaRate must be number." });
      }

      const priceWithIva = this.calcPriceWithIva(price, ivaRate);

      const updated = await this.repo.updateProduct(id, {
        name: name.trim(),
        price,
        ivaRate,
        priceWithIva
      });

      if (!updated) {
        return res.status(404).json({ error: "Product not found." });
      }

      return res.json(updated);
    } catch (err) {
      console.error("UPDATE ERROR:", err);
      return res.status(500).json({
        error: "Error updating product.",
        details: String(err?.message || err)
      });
    }
  }

  async remove(req, res) {
    try {
      const { id } = req.params;
      if (!this.isValidId(id)) {
        return res.status(400).json({ error: "ID invalid." });
      }

      const deleted = await this.repo.deleteProduct(id);
      if (!deleted) {
        return res.status(404).json({ error: "Product not found." });
      }

      return res.json({ ok: true, deletedId: id });
    } catch (err) {
      console.error("DELETE ERROR:", err);
      return res.status(500).json({
        error: "Error deleting product.",
        details: String(err?.message || err)
      });
    }
  }
}

module.exports = ProductController;
