const mongoose = require('mongoose');

const URI = 'mongodb+srv://Josue:Josue2006@cluster0.da07rsq.mongodb.net/ConectionMongoDB?retryWrites=true&w=majority';

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

module.exports = mongoose.model('Contact', ContactSchema, 'JsContactsBook');