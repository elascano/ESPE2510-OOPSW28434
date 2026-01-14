import express from 'express';
import cors from 'cors';
import { ConnectionDB } from './controller/ConnectionDB.js';

const app = express();
app.use(cors());
app.use(express.json());

const db = new ConnectionDB();

async function startServer() {
    try {
        await db.connect();
        console.log(">>> [OK]: MongoDB Atlas Conectado");

        app.get('/api/products', async (req, res) => {
            const list = await db.getAll();
            res.json(list);
        });

        app.post('/api/products', async (req, res) => {
            await db.insert(req.body);
            res.status(201).json({ message: "Guardado" });
        });

        app.delete('/api/products/:id', async (req, res) => {
            await db.deleteById(req.params.id);
            res.json({ message: "Eliminado" });
        });

        // En server.js
        app.put('/api/products/:id', async (req, res) => {
        try {
            const productId = req.params.id;
            const updatedFields = req.body;

            const result = await db.updateById(productId, updatedFields);

            if (result.matchedCount > 0) {
            res.json({ message: "Actualizado correctamente" });
            } else {
               // Esto pasa si el ID que envías no existe en la base de datos
               res.status(404).json({ message: "Producto no encontrado" });
            }
        } catch (error) {
            console.error("Error en el servidor:", error);
            res.status(500).json({ error: "Fallo interno al actualizar" });
        }
        });

        app.listen(3000, () => console.log(">>> [OK]: Servidor en puerto 3000"));
    } catch (e) {
        console.error("Error de inicio:", e);
    }
}

startServer();