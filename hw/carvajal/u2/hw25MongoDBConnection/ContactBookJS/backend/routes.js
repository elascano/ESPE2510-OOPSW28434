const express = require("express");
const router = express.Router();
const Contacto = require("./contact.model");

// Crear
router.post("/contactos", async (req, res) => {
    const contacto = new Contacto(req.body);
    await contacto.save();
    res.send({ message: "Guardado" });
});

// Listar
router.get("/contactos", async (req, res) => {
    const lista = await Contacto.find();
    res.send(lista);
});

// Buscar por cédula
router.get("/contactos/:cedula", async (req, res) => {
    const contacto = await Contacto.findOne({ cedula: req.params.cedula });
    res.send(contacto);
});

// Actualizar
router.put("/contactos/:cedula", async (req, res) => {
    await Contacto.findOneAndUpdate({ cedula: req.params.cedula }, req.body);
    res.send({ message: "Actualizado" });
});

// Eliminar
router.delete("/contactos/:cedula", async (req, res) => {
    await Contacto.findOneAndDelete({ cedula: req.params.cedula });
    res.send({ message: "Eliminado" });
});

module.exports = router;
