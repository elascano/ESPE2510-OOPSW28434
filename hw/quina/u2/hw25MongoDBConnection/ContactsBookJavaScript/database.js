const mongoose = require('mongoose');

mongoose.connect(
    'mongodb+srv://maryuri:maryuri2007@cluster0.iektq66.mongodb.net/ContactsBookDB?retryWrites=true&w=majority&appName=Cluster0'
)
.then(() => console.log('MongoDB Connected'))
.catch(err => console.error('Connection error:', err));

const ContactSchema = new mongoose.Schema({
    firstName: String,
    lastName: String,
    birthDate: String,
    age: Number,
    type: String,
    sex: String,
    hobbies: [String],
    comments: String
});

module.exports = mongoose.model('Contact', ContactSchema);
