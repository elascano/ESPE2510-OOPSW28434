const express = require('express');
const path = require('path');
const DataManager = require('./controller/DataManage.js'); // Ojo: en tu imagen dice "DataManage" sin la R final
const CsvStrategy = require('./controller/CsvPersistence.js');
const JsonStrategy = require('./controller/JsonPersistence.js');
const MongoStrategy = require('./controller/MongoPersistence.js');
const Store = require('./model/Store.js');

const app = express();
app.use(express.json());

app.use(express.static(path.join(__dirname, 'view')));

let manager = new DataManager(new CsvStrategy());

app.post('/set-strategy', (req, res) => {
    const { type } = req.body;
    if (type === 'MONGO') manager.setStrategy(new MongoStrategy());
    else if (type === 'JSON') manager.setStrategy(new JsonStrategy());
    else manager.setStrategy(new CsvStrategy());
    res.send({ status: "Strategy changed", type });
});

app.get('/store', async (req, res) => {
    res.json(await manager.loadAll());
});

app.post('/store', async (req, res) => {
    const { id, name, price, category } = req.body;
    await manager.create(new Store(id, name, price, category));
    res.send({ status: "Created" });
});

app.get('/store/:id', async (req, res) => {
    const item = await manager.find(req.params.id);
    item ? res.json(item) : res.status(404).send("Not found");
});

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'view', 'index.html'));
});

app.listen(3000, () => console.log("Servidor corriendo en http://localhost:3000"));