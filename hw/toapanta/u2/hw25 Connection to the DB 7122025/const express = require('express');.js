const express = require('express');
const mongoose = require('mongoose');
const Patient = require('./patientModel'); // Importa el modelo de paciente

const app = express();
const PORT = 3000;

// Configuración de MongoDB (cambia esta URI por la de tu Atlas o la local)
// Si usas tu URI local, asegúrate de que el servidor mongod esté corriendo.
// Ejemplo Atlas: const MONGO_URI = "mongodb+srv://Cesar:TU_CONTRASEÑA_REAL@cluster0.tgbv2qc.mongodb.net/PatientRegist";
const MONGO_URI = "mongodb://localhost:27017/PatientRegist";
const DATABASE_NAME = "PatientRegist";

// Middleware para parsear el cuerpo de las peticiones JSON
app.use(express.json());

// --- Conexión a MongoDB ---
mongoose.connect(MONGO_URI, {
    serverSelectionTimeoutMS: 5000, // Timeout de 5 segundos
})
.then(() => console.log('Conexión a MongoDB exitosa.'))
.catch(err => console.error('Error de conexión a MongoDB:', err.message));

// --- RUTA POST para Guardar Pacientes ---
app.post('/api/patients', async (req, res) => {
    try {
        const data = req.body;
        
        // **Manejo de Tipos y Validaciones (Similar a la lógica Python):**
        // 1. Convertir patient_id a Number (aunque Express.json y Mongoose ayudan, esto es para asegurar)
        const patient_id_int = parseInt(data.patient_id);
        if (isNaN(patient_id_int)) {
            return res.status(400).json({ error: "El ID del paciente debe ser un número entero válido." });
        }

        // 2. Mongoose convierte automáticamente la cadena de fecha ISO (ej: "2005-06-18")
        //    a un objeto Date de MongoDB, resolviendo el problema de Python.

        // Crea una nueva instancia del modelo Patient
        const newPatient = new Patient({
            patient_id: patient_id_int,
            fullName: data.fullName,
            birthDate: data.birthDate, // Debe venir como una cadena ISO 8601 (e.g., "2005-06-18T00:00:00.000Z")
            age: parseInt(data.age),
            gender: data.gender,
            phone: data.phone,
            address: data.address,
            email: data.email,
            // registration_date usará el default (Date.now)
        });

        // Guarda el documento en la base de datos
        const savedPatient = await newPatient.save();
        
        // Respuesta exitosa
        res.status(201).json({ 
            message: "Información guardada con éxito en MongoDB.",
            documentId: savedPatient._id 
        });

    } catch (error) {
        // Manejo de errores de validación, duplicados (si patient_id es unique), etc.
        console.error("Error al guardar paciente:", error);
        if (error.code === 11000) { // Código de error de duplicado de MongoDB
            return res.status(409).json({ error: "Error: El ID del paciente ya existe." });
        }
        res.status(500).json({ error: "Ocurrió un error al guardar en MongoDB." });
    }
});

// --- Iniciar Servidor ---
app.listen(PORT, () => {
    console.log(`Servidor Express escuchando en http://localhost:${PORT}`);
});