const mongoose = require('mongoose');

const URI = 'mongodb+srv://Paulo:paulo2004@cluster0.9uxqgih.mongodb.net/ContactsDB?retryWrites=true&w=majority';

mongoose
  .connect(URI)
  .then(() => console.log('MongoDB connected to DB: ContactsDB'))
  .catch(err => console.error('MongoDB connection error:', err));

const ContactSchema = new mongoose.Schema(
  {
    id: { type: Number, default: 0 },
    firstName: { type: String, required: true, trim: true },
    lastName: { type: String, required: true, trim: true },
    birthDate: { type: String, required: true },
    age: { type: Number, min: 0, max: 120, required: true },
    type: { type: String, enum: ['Family', 'Friend', 'Job', 'Unknown'], required: true },
    sex: { type: String, enum: ['Male', 'Female'], required: true },
    hobbies: [{ type: String }],
    comments: { type: String, default: '' }
  },
  {
    collection: 'Contacts'
  }
);

module.exports = mongoose.model('Contacts', ContactSchema);
