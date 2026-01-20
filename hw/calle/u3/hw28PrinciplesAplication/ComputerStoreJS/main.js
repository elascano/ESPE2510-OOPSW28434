const express = require('express');
const path = require('path');
const MongoRepository = require('./repository/mongoRepository');
const TaxService = require('./service/taxService');
const ProductController = require('./controller/productController');

const app = express();
app.use(express.json());
app.use(express.static('public')); 

const repo = new MongoRepository();
const service = new TaxService();
const controller = new ProductController(repo, service);

app.post('/api/save', (req, res) => controller.handleAddProduct(req, res));

app.listen(3000, () => {
    console.log("Server running at http://localhost:3000");
});