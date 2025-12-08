const mongoose = require('mongoose');

 
const patientSchema = new mongoose.Schema({
 
    patient_id: {
        type: Number,
        required: true,
        unique: true 
        
    },
    fullName: {
        type: String,
        required: true
    },
    
    birthDate: {
        type: Date,
        required: true
    },
    age: {
        type: Number
    },
    gender: {
        type: String
    },
    phone: {
        type: String
    },
    address: {
        type: String
    },
    email: {
        type: String
    },
    registration_date: {
        type: Date,
        default: Date.now
    }
});

const Patient = mongoose.model('Patient', patientSchema, 'Patient'); // 'Patient' es el nombre de la colección
module.exports = Patient;