const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const path = require('path');
const Contact = require('./ec.espe.edu.contactbook.model/Contact');
const app = express();

const CONNECTION_STRING = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/ContactBook";

mongoose.connect(CONNECTION_STRING)
    .then(() => console.log('--> DB CONNECTED'))
    .catch(err => console.error(err));

app.set('view engine', 'ejs'); 
app.set('views', path.join(__dirname, 'ec.espe.edu.contactbook.view'));
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/', (req, res) => {
    res.render('index', { contact: null, message: null, isEditing: false });
});

app.post('/save', async (req, res) => {
    try {
        const { id, firstName, lastName, birthDate, typeOfContact, sex, hobbies, comments } = req.body;

        if (!id || !firstName || !lastName || !birthDate || typeOfContact === 'Select') {
            return res.render('index', { 
                contact: req.body, 
                message: "Error: Required fields are missing.", 
                isEditing: false 
            });
        }

        const nameRegex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;

        if (!nameRegex.test(firstName)) {
            return res.render('index', { 
                contact: req.body, 
                message: "Error: First Name must contain only letters.",
                isEditing: false 
            });
        }

        if (!nameRegex.test(lastName)) {
            return res.render('index', { 
                contact: req.body, 
                message: "Error: Last Name must contain only letters.",
                isEditing: false 
            });
        }

        const existing = await Contact.findOne({ id: id });
        if (existing) {
            return res.render('index', { 
                contact: req.body, 
                message: "Error: ID already exists.",
                isEditing: false 
            });
        }

        let age = calculateAge(birthDate);

        await Contact.create({
            id, firstName, lastName, birthDate, age, typeOfContact, sex, hobbies, comments
        });

        res.render('index', { contact: null, message: "Contact SAVED successfully!", isEditing: false });

    } catch (err) {
        res.render('index', { contact: req.body, message: "Error saving: " + err.message, isEditing: false });
    }
});

app.post('/search', async (req, res) => {
    try {
        const { id } = req.body; 
        
        if (!id) {
            return res.render('index', { contact: null, message: "Please enter an ID to search.", isEditing: false });
        }

        const foundContact = await Contact.findOne({ id: id });

        if (foundContact) {
            res.render('index', { contact: foundContact, message: "Contact FOUND.", isEditing: true });
        } else {
            res.render('index', { contact: null, message: "ID not found.", isEditing: false });
        }
    } catch (err) {
        res.render('index', { contact: null, message: "Error searching: " + err.message, isEditing: false });
    }
});

app.post('/update', async (req, res) => {
    try {
        const { id, firstName, lastName, birthDate, typeOfContact, sex, hobbies, comments } = req.body;
        
        const nameRegex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;

        if (!nameRegex.test(firstName)) {
            return res.render('index', { 
                contact: req.body, 
                message: "Error: First Name must contain only letters.",
                isEditing: true 
            });
        }
        if (!nameRegex.test(lastName)) {
            return res.render('index', { 
                contact: req.body, 
                message: "Error: Last Name must contain only letters.",
                isEditing: true 
            });
        }

        let age = calculateAge(birthDate);

        await Contact.updateOne({ id: id }, {
            firstName, lastName, birthDate, age, typeOfContact, sex, hobbies, comments
        });

        res.render('index', { contact: null, message: "Contact UPDATED successfully.", isEditing: false });

    } catch (err) {
        res.render('index', { contact: req.body, message: "Error updating: " + err.message, isEditing: true });
    }
});

app.post('/delete', async (req, res) => {
    try {
        const { id } = req.body;
        await Contact.deleteOne({ id: id });
        res.render('index', { contact: null, message: "Contact DELETED successfully.", isEditing: false });
    } catch (err) {
        res.render('index', { contact: req.body, message: "Error deleting: " + err.message, isEditing: true });
    }
});

app.post('/reset', (req, res) => {
    res.redirect('/');
});

function calculateAge(birthDateStr) {
    if (!birthDateStr) return 0;
    const birth = new Date(birthDateStr);
    const diff = Date.now() - birth.getTime();
    return Math.abs(new Date(diff).getUTCFullYear() - 1970);
}

app.listen(3000, () => console.log('Server running on port 3000'));