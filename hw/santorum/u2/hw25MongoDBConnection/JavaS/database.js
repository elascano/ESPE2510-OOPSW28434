const mongoose = require('mongoose');

const URI = 'mongodb+srv://thais:thais@cluster0.9yfzmcp.mongodb.net/ContactsDB?retryWrites=true&w=majority';

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