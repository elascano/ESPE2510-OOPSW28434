const express = require("express");
const path = require("path");

const ProductController = require("./controllers/ProductController");
const { SimpleTaxCalculator } = require("./models/TaxCalculator");
const MongoProductRepository = require("./repositories/MongoProductRepository");
const productRoutes = require("./views/productRoutes");

const app = express();
app.use(express.json());

app.use(express.static(path.join(__dirname, "public")));

const controller = new ProductController(
    new SimpleTaxCalculator(),
    new MongoProductRepository()
);

app.use("/products", productRoutes(controller));

app.listen(3000, () =>
    console.log("Server running on http://localhost:3000")
);
