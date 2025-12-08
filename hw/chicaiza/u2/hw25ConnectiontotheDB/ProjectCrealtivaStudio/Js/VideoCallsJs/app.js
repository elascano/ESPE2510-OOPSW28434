const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");
const VideoCall = require("./model/VideoCall");
const { connect } = require("./utils/mongodbConnection");

const app = express();
const PORT = 3000;

app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, "view")));

// Conectar a MongoDB
connect();

// Rutas API
app.post("/add", async (req, res) => {
    const { customerId, videoCallDate, hour, medium, note } = req.body;
    try {
        const vc = new VideoCall(customerId, videoCallDate, hour, medium, note);
        await vc.save();
        res.json({ message: "Videollamada guardada correctamente en la nube." });
    } catch (err) {
        console.error(err);
        res.status(500).json({ message: "Error al guardar videollamada." });
    }
});

app.get("/getAll", async (req, res) => {
    try {
        const calls = await VideoCall.getAll();
        res.json(calls);
    } catch (err) {
        console.error(err);
        res.status(500).json({ message: "Error al obtener videollamadas." });
    }
});

// Servir index.html
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "view", "index.html"));
});

// Iniciar servidor
app.listen(PORT, () => console.log(`Servidor corriendo en http://localhost:${PORT}`));
