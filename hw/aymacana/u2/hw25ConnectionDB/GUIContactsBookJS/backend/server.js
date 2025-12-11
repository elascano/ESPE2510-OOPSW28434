const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const app = express();
app.use(cors({
    origin: ['http://127.0.0.1:5500', 'http://localhost:5500'],
    methods: ['GET', 'POST', 'PUT', 'DELETE', 'OPTIONS'],
    allowedHeaders: ['Content-Type', 'Authorization'],
    credentials: true
}));

app.use(cors());

app.use(express.json());

app.use((req, res, next) => {
    console.log(`${new Date().toISOString()} - ${req.method} ${req.url}`);
    next();
});

mongoose.connect('mongodb+srv://Mateo:Mateo21032006@cluster0.t4qmrfv.mongodb.net/ContacsBook', {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
.then(() => {
    console.log('Conectado a MongoDB Atlas');
    console.log('Base de datos:', mongoose.connection.name);
    console.log('Host:', mongoose.connection.host);
})
.catch(err => {
    console.error('Error conectando a MongoDB:', err.message);
    console.error('Error completo:', err);
});


const contactSchema = new mongoose.Schema({
    id: { type: Number, required: true, unique: true },
    firstName: { type: String, required: true },
    lastName: { type: String, required: true },
    age: { type: Number, required: true },
    birthDate: { type: String, required: true },
    typeOfContact: { type: String, required: true },
    sex: { type: String, required: true },
    hobbies: [String],
    comments: String
}, {
    collection: 'Contacts', 
    timestamps: false, 
    versionKey: false 
});

const Contact = mongoose.model('Contact', contactSchema);

app.get('/api/contacts', async (req, res) => {
    try {
        const contacts = await Contact.find().sort({ id: 1 });
        res.json(contacts);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

app.get('/api/contacts/next-id', async (req, res) => {
    try {
        const lastContact = await Contact.findOne().sort({ id: -1 });
        const nextId = lastContact ? lastContact.id + 1 : 1;
        res.json({ nextId });
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

app.post('/api/contacts', async (req, res) => {
     try {
        console.log('='.repeat(50));
        console.log('NUEVO CONTACTO RECIBIDO');
        console.log('Datos recibidos:', JSON.stringify(req.body, null, 2));
        console.log('Conectando a MongoDB Atlas...');
        
        const requiredFields = ['id', 'firstName', 'lastName', 'age', 'birthDate', 'typeOfContact', 'sex'];
        const missingFields = requiredFields.filter(field => !req.body[field]);
        
        if (missingFields.length > 0) {
            console.error('Campos faltantes:', missingFields);
            return res.status(400).json({ error: `Campos faltantes: ${missingFields.join(', ')}` });
        }
        
        console.log('Todos los campos presentes');
        
        const contact = new Contact(req.body);
        console.log('Contacto creado en memoria:', contact);
        
        console.log('Guardando en MongoDB Atlas...');
        const savedContact = await contact.save();
        
        console.log('CONTACTO GUARDADO EXITOSAMENTE EN MONGODB');
        console.log('Datos guardados:', JSON.stringify(savedContact, null, 2));
        console.log('ID del documento:', savedContact._id);
        console.log('='.repeat(50));
        
        res.status(201).json(savedContact);
        
    } catch (error) {
        console.error('='.repeat(50));
        console.error('ERROR AL GUARDAR CONTACTO');
        console.error('Error detallado:', error.message);
        console.error('Error completo:', error);
        
        if (error.code === 11000) {
            console.error('Error de duplicado: Ya existe un contacto con este ID');
            res.status(400).json({ error: 'ID duplicado' });
        } else if (error.name === 'ValidationError') {
            console.error('Error de validación:', error.errors);
            res.status(400).json({ error: error.message });
        } else {
            console.error('Error desconocido');
            res.status(500).json({ error: error.message });
        }
        
        console.log('='.repeat(50));
    }
});

app.delete('/api/contacts/:id', async (req, res) => {
    try {
        const result = await Contact.deleteOne({ id: parseInt(req.params.id) });
        if (result.deletedCount === 0) {
            return res.status(404).json({ error: 'Contact not found' });
        }
        res.json({ success: true });
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

app.put('/api/contacts/:id', async (req, res) => {
    try {
        const contactId = parseInt(req.params.id);
        const updatedData = req.body;
        
        const contact = await Contact.findOneAndUpdate(
            { id: contactId },
            updatedData,
            { new: true, runValidators: true }
        );
        
        if (!contact) {
            return res.status(404).json({ error: 'Contact not found' });
        }
        
        res.json(contact);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});