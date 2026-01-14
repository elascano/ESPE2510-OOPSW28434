const express = require('express');
const connectDB = require('./db');
const Pencil = require('./Pencil');
const app = express();

app.use(express.json());
app.use(express.static('public')); 
connectDB();

app.post('/api/pencils', async (req, res) => {
    try {
        const newPencil = new Pencil(req.body);
        await newPencil.save();
        res.status(201).json(newPencil);
    } catch (err) { res.status(400).json({ error: err.message }); }
});

app.get('/api/pencils', async (req, res) => {
    const pencils = await Pencil.find();
    res.json(pencils);
});

app.put('/api/pencils/:id', async (req, res) => {
    await Pencil.findOneAndUpdate({ id: req.params.id }, req.body);
    res.json({ message: "Actualizado" });
});

app.delete('/api/pencils/:id', async (req, res) => {
    await Pencil.findOneAndDelete({ id: req.params.id });
    res.json({ message: "Eliminado" });
});

app.listen(3000, () => console.log("Servidor en http://localhost:3000"));