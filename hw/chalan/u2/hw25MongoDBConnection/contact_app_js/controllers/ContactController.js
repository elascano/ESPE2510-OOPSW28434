const Contact = require("../models/Contact");

class ContactController {
    
    static generateJavaId() {
        return Math.floor(Math.random() * (99999999 - 10000000 + 1)) + 10000000;
    }

    static async create(req, res) {
        try {
            const contactData = {
                id: ContactController.generateJavaId(), 
                firstName: req.body.firstName, 
                lastName: req.body.lastName,
                birthDate: new Date(req.body.birthDate), 
                age: parseInt(req.body.age),
                typeOfContact: req.body.contactType, 
                sex: req.body.sex,
                hobbies: req.body.hobbies,
                comments: req.body.comments
            };
            
            const contact = new Contact(contactData); 
            
            await contact.save();
            res.json(contact);
        } catch (err) {
            res.status(500).json({ error: err.message });
        }
    }

    static async getAll(req, res) {
        try {
            const contacts = await Contact.find();
            res.json(contacts);
        } catch (err) {
            res.status(500).json({ error: err.message });
        }
    }

    static async update(req, res) {
        try {
            const updated = await Contact.findByIdAndUpdate(
                req.params.id,
                req.body,
                { new: true }
            );
            res.json(updated);
        } catch (err) {
            res.status(500).json({ error: err.message });
        }
    }

    static async delete(req, res) {
        try {
            await Contact.findByIdAndDelete(req.params.id);
            res.json({ message: "Eliminado" });
        } catch (err) {
            res.status(500).json({ error: err.message });
        }
    }
}

module.exports = ContactController;