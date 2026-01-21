const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();

app.use(express.json());
app.use(express.static('.')); 

const JSON_FILE = path.join(__dirname, 'rent.json');

app.get('/api/rent', (req, res) => {
    const data = JSON.parse(fs.readFileSync(JSON_FILE, 'utf8'));
    res.json(data);
});

app.post('/api/rent', (req, res) => {
    const newRent = { monthlyRent: req.body.monthlyRent };
    fs.writeFileSync(JSON_FILE, JSON.stringify(newRent, null, 2));
    res.json({ success: true });
});

app.listen(3000, () => console.log('Servidor en http://localhost:3000'));