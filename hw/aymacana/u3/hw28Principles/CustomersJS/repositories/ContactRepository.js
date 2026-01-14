const Contact = require('../models/Contact');

class ContactRepository {
    async findAll() {
        try {
            const contacts = await Contact.find().sort({ id: 1 });
            return contacts.map(contact => contact.toObjectWithCalculations());
        } catch (error) {
            throw new Error(`Error finding contacts: ${error.message}`);
        }
    }

    async findById(id) {
        try {
            const contact = await Contact.findOne({ id: id });
            if (!contact) {
                throw new Error(`Contact with ID ${id} not found`);
            }
            return contact.toObjectWithCalculations();
        } catch (error) {
            throw new Error(`Error finding contact by ID: ${error.message}`);
        }
    }
}

module.exports = ContactRepository;