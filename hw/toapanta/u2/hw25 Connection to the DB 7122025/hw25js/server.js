const express = require('express');
const mongoose = require('mongoose');
const Patient = require('./patientModel'); 

const app = express();
const PORT = 3000;


const MONGO_URI = "mongodb://localhost:27017/PatientRegist";
const DATABASE_NAME = "PatientRegist";


app.use(express.json());


mongoose.connect(MONGO_URI, {
    serverSelectionTimeoutMS: 5000, 
})
.then(() => console.log('Conexión a MongoDB exitosa.'))
.catch(err => console.error('Error de conexión a MongoDB:', err.message));


app.post('/api/patients', async (req, res) => {
    try {
        const data = req.body;
        

        const patient_id_int = parseInt(data.patient_id);
        if (isNaN(patient_id_int)) {
            return res.status(400).json({ error: "El ID del paciente debe ser un número entero válido." });
        }



        const newPatient = new Patient({
            patient_id: patient_id_int,
            fullName: data.fullName,
            birthDate: data.birthDate, 
            age: parseInt(data.age),
            gender: data.gender,
            phone: data.phone,
            address: data.address,
            email: data.email,
  
        });


        const savedPatient = await newPatient.save();
        

        res.status(201).json({ 
            message: "Información guardada con éxito en MongoDB.",
            documentId: savedPatient._id 
        });

    } catch (error) {
        
        console.error("Error al guardar paciente:", error);
        if (error.code === 11000) {
            return res.status(409).json({ error: "Error: El ID del paciente ya existe." });
        }
        res.status(500).json({ error: "Ocurrió un error al guardar en MongoDB." });
    }
});


app.listen(PORT, () => {
    console.log(`Servidor Express escuchando en http://localhost:${PORT}`);
});