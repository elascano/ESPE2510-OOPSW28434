const express = require('express');
const cors = require('cors');
const path = require('path');

const RepositoryImpl = require('./repository/repositoryImpl');
const CalculateService = require('./service/serviceImpl');
const ProductController = require('./controller/productController');

const app = express();
app.use(express.json()); 
app.use(cors());
app.use(express.static('public'));

const repository = new RepositoryImpl("products");

const service = new CalculateService(repository);

const controller = new ProductController(service);

app.get('/api/products', (req, res) => controller.getTableData(req, res));
app.post('/api/products', (req, res) => controller.addProduct(req, res));

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Sistema JS corriendo en http://localhost:${PORT}`);
});