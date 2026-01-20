const express = require('express');
const ProductController = require('./controller/ProductController');
const app = express();

app.use(express.json());
app.use(express.static('view'));

const uri = "mongodb+srv://maryuri:maryuri2007@cluster0.iektq66.mongodb.net/";
const controller = new ProductController(uri, "StoreDB", "Product");

app.get('/api/products', async (req, res) => res.json(await controller.getInventory()));
app.get('/api/products/:id', async (req, res) => res.json(await controller.searchProduct(req.params.id)));
app.post('/api/products', async (req, res) => {
    await controller.saveProduct(req.body.id, req.body.name, req.body.price);
    res.sendStatus(201);
});
app.put('/api/products', async (req, res) => {
    await controller.modifyProduct(req.body.id, req.body.name, req.body.price);
    res.sendStatus(200);
});
app.delete('/api/products/:id', async (req, res) => {
    await controller.removeProduct(req.params.id);
    res.sendStatus(200);
});

app.listen(3000, () => console.log('Server running on http://localhost:3000'));