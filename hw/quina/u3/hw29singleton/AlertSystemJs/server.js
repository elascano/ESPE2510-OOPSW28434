const express = require('express');
const path = require('path');
const app = express();
const alarmController = require('./controller/alarmController');

app.use(express.json());

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'view', 'index.html'));
});

app.post('/api/update', alarmController.updateAndCheck);

app.listen(3000, () => console.log('Servidor en http://localhost:3000'));