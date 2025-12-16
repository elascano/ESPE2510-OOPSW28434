// backend/app.js
const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors'); 
const TVSetDAO = require('./TVSetDAO');
const mongoose = require('mongoose'); 

const app = express();
const PORT = 3000;
const dao = new TVSetDAO();

app.use(cors()); 
app.use(bodyParser.json());


app.get('/api/tvsets', async (req, res) => {
    try {
        const tvSets = await dao.readAll();

        const tvSetsWithRatio = tvSets.map(tv => ({

            ...tv.toObject(), 
            ratio: tv.getRatio() 
        }));
        res.status(200).json(tvSetsWithRatio);
    } catch (error) {
        res.status(500).json({ message: 'Error al listar TV Sets', error: error.message });
    }
});


app.post('/api/tvsets', async (req, res) => {
    try {
        const newTVSet = await dao.create(req.body);
        res.status(201).json(newTVSet);
    } catch (error) {
        res.status(400).json({ message: 'Error de validación al crear TV Set', error: error.message });
    }
});


app.delete('/api/tvsets/:id', async (req, res) => {
    try {
        const id = parseInt(req.params.id);
        if (isNaN(id)) return res.status(400).json({ message: 'ID inválido.' });

        const deleted = await dao.delete(id);
        
        if (deleted) {
            res.status(200).json({ message: `TV Set con ID ${id} eliminado.` });
        } else {
            res.status(404).json({ message: `TV Set con ID ${id} no encontrado.` });
        }
    } catch (error) {
        res.status(500).json({ message: 'Error al eliminar TV Set', error: error.message });
    }
});


app.listen(PORT, () => {
    console.log(`Servidor Express corriendo en http://localhost:${PORT}`);
});