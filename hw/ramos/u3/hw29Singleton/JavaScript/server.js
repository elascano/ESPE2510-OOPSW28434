const express = require('express');
const bodyParser = require('body-parser');
const MainController = require('./controller/MainController');

const app = express();
const controller = new MainController();

app.use(bodyParser.json());
app.use(express.static('public'));

// API
app.get('/tasks', (req, res) => {
    res.json(controller.getTasks());
});

app.post('/tasks', (req, res) => {
    controller.addTask(req.body.name, req.body.dueDate);
    res.sendStatus(200);
});

app.post('/alert-days', (req, res) => {
    controller.updateAlertDays(req.body.days);
    res.sendStatus(200);
});

app.listen(3000, () => {
    console.log('Servidor corriendo en http://localhost:3000');
});
