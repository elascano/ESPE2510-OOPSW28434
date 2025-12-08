const mongoose = require('mongoose');

const URI = 'mongodb+srv://Paulo:paulo2004@cluster0.9uxqgih.mongodb.net/ContactsBook';

mongoose.connect(URI)
    .then(db => console.log('DB is connected to ConectionMongoDB'))
    .catch(err => console.error(err));

const ContactSchema = new mongoose.Schema({
    id: { type: Number, default: 0 },
    firstName: { type: String, required: true },
    lastName: { type: String, required: true },
    birthDate: { type: String },
    age: { type: Number },
    type: { type: String },
    sex: { type: String },
    hobbies: [String],
    comments: { type: String }
});

module.exports = mongoose.model('Contact', ContactSchema, 'Contacts');