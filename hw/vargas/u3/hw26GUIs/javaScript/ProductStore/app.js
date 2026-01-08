const express = require("express");
const path = require("path");
const ProductController = require("./controller/productController");

const app = express();
const PORT = 3000;

app.use(express.json());
app.use(express.static(path.join(__dirname, "view")));

const productController = new ProductController();

app.get("/api/products", (req, res) => productController.getAll(req, res));
app.get("/api/products/:id", (req, res) => productController.getById(req, res));
app.post("/api/products", (req, res) => productController.create(req, res));
app.put("/api/products/:id", (req, res) => productController.update(req, res));
app.delete("/api/products/:id", (req, res) => productController.remove(req, res));

app.listen(PORT, "0.0.0.0", () => {
  console.log(` http://localhost:${PORT}`);
});
