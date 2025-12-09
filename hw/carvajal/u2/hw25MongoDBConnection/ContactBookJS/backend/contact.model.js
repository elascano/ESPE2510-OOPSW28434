const mongoose = require("mongoose");

const ContactoSchema = new mongoose.Schema({
    nombre: String,
    apellido: String,
    cedula: { type: String, unique: true },
    telefono: String,
    email: String
});

module.exports = mongoose.model("Contacto", ContactoSchema);
