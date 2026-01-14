// controller/SoccerTeamController.js
import SimpleCrud from './SimpleCrud.js';
import Contact from '../model/Contact.js';
import IdGenerator from '../utils/idGenerator.js';

class ContactController {
    constructor() {
        this.crud = new SimpleCrud('Contacts');
    }

    // CREATE
    async addTeam(name, phone, email, addres) {
        try {
            // Generar ID autoincremental
            const nextId = await IdGenerator.getNextId('Contacts');
            
            // Crear equipo
            const contact = new Contact(
                nextId,
                name,
                phone,
                email,
                addres
            );
            
            // Guardar en MongoDB
            const doc = contact.toDocument();
            const newId = await this.crud.create(doc);
            
            return {
                success: true,
                message: ` Contacto agregado con ID: ${newId}`,
                data: contact
            };
            
        } catch (error) {
            return {
                success: false,
                message: ` Error al agregar contacto: ${error.message}`,
                data: null
            };
        }
    }
}

export default ContactController;