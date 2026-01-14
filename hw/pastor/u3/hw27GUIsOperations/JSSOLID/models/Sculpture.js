const mongoose = require('mongoose');

// [SRP] Responsabilidad Única: Definir la ESTRUCTURA de la escultura.
// No calcula nada, solo dice qué datos son válidos.

const SculptureSchema = new mongoose.Schema({
    // Definimos tipos de datos estrictos
    id: { type: String, required: true, unique: true },
    name: { type: String, required: true },
    price: { type: Number, required: true },
    
    // CAMBIO: colors -> materials (Lista de Strings)
    materials: { type: [String], default: [] }, 
    
    priceWithIva: { type: Number }
});

module.exports = mongoose.model('Sculpture', SculptureSchema);