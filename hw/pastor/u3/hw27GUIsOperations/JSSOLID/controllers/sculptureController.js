const Sculpture = require('../models/Sculpture');

// ================================================================
//  SECCIÓN DE LÓGICA DE NEGOCIO (BUSINESS RULES) - [EXAMEN]
//  [SRP]: Funciones puras que calculan cosas. No saben de BD.
// ================================================================

// Constante reusable
const IVA_RATE = 0.15;

// [REUSABLE] Cálculo del precio final
const calculateIva = (price) => {
    // [A] Abstracción: La fórmula está oculta aquí
    let result = price * (1 + IVA_RATE);
    return Math.round(result * 100) / 100; // Redondeo a 2 decimales
};

// [HELPER] Reparación de datos (Si vienen nulos de BD antigua)
const fixSculptureData = (doc) => {
    // Convertimos el documento de Mongoose a objeto JS normal
    let sculpture = doc.toObject(); 

    // Si falta el precio con IVA, lo calculamos al vuelo
    if (sculpture.priceWithIva === null || sculpture.priceWithIva === undefined) {
        sculpture.priceWithIva = calculateIva(sculpture.price);
    }
    return sculpture;
};

// ================================================================
//  SECCIÓN DE PERSISTENCIA (CRUD) - Mongo Logic
// ================================================================

// --- CREATE ---
exports.createSculpture = async (req, res) => {
    try {
        // Desestructuración: Sacamos los datos que vienen del frontend
        const { id, name, price, materials } = req.body;
        
        // 1. Aplicar Regla de Negocio
        const finalPrice = calculateIva(Number(price));
        
        // 2. Crear Objeto
        const newSculpture = new Sculpture({
            id, 
            name, 
            price, 
            materials, // Guardamos la lista
            priceWithIva: finalPrice
        });

        // 3. Guardar en BD
        await newSculpture.save();
        res.json({ success: true, message: "Sculpture Saved" });
    } catch (err) {
        res.status(400).json({ success: false, message: "Error saving (Check ID)" });
    }
};

// --- READ ALL ---
exports.getAllSculptures = async (req, res) => {
    try {
        const sculptures = await Sculpture.find();
        // Usamos el helper 'fixSculptureData' para asegurar que nada vaya nulo
        const fixedList = sculptures.map(s => fixSculptureData(s));
        res.json(fixedList);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
};

// --- FIND BY ID ---
exports.getSculptureById = async (req, res) => {
    try {
        const sculpture = await Sculpture.findOne({ id: req.params.id });
        if (sculpture) {
            res.json(fixSculptureData(sculpture));
        } else {
            res.status(404).json({ message: "Not found" });
        }
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
};

// --- UPDATE ---
exports.updateSculpture = async (req, res) => {
    const { id } = req.params;
    const { name, price, materials } = req.body;

    try {
        // 1. RECALCULAR Regla de Negocio (Crítico al editar)
        const finalPrice = calculateIva(Number(price));

        // 2. Actualizar en BD
        const result = await Sculpture.updateOne(
            { id: id }, 
            { $set: { name, price, materials, priceWithIva: finalPrice } }
        );

        // Verificación real de cambio (matchedCount)
        if (result.matchedCount > 0) {
            res.json({ success: true, message: "Sculpture Updated" });
        } else {
            res.status(404).json({ success: false, message: "ID not found" });
        }
    } catch (err) {
        res.status(500).json({ success: false, message: err.message });
    }
};

// --- DELETE ---
exports.deleteSculpture = async (req, res) => {
    const { id } = req.params;
    try {
        const result = await Sculpture.deleteOne({ id: id });
        
        if (result.deletedCount > 0) {
            res.json({ success: true, message: "Deleted" });
        } else {
            res.status(404).json({ success: false, message: "ID not found" });
        }
    } catch (err) {
        res.status(500).json({ success: false, message: err.message });
    }
};