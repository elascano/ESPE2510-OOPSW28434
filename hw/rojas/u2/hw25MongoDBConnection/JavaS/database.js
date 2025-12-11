const mongoose = require('mongoose');

<<<<<<< HEAD
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
=======
const URI = 'mongodb+srv://Josue:Josue2006@cluster0.da07rsq.mongodb.net/ConectionMongoDB?retryWrites=true&w=majority&appName=Cluster0';

mongoose
  .connect(URI)
  .then(() => console.log('MongoDB connected to DB: ConectionMongoDB'))
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
    collection: 'JsContactsBook'
  }
);

module.exports = mongoose.model('Contact', ContactSchema);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
