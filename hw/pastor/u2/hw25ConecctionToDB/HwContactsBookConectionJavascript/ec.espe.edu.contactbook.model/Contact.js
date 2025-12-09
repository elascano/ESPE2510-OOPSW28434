const mongoose = require('mongoose');

const contactSchema = new mongoose.Schema({
    id: String,
    firstName: String,
    lastName: String,
    birthDate: Date,   
    age: Number,       
    typeOfContact: String,
    sex: String,
    hobbies: [String],
    comments: String
});

module.exports = mongoose.model('Contact', contactSchema, 'Contact');