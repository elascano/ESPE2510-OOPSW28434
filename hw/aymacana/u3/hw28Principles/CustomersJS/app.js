const DatabaseConnection = require('./config/database');
const ContactRepository = require('./repositories/ContactRepository');
const ContactService = require('./services/ContactService');
const ContactController = require('./controllers/ContactController');
const ContactView = require('./views/ContactView');

async function main() {
    try {
        console.log('Starting Contact Management System...\n');
        
        const database = DatabaseConnection;
        await database.connect();
        
        const contactRepository = new ContactRepository();
        const contactService = new ContactService(contactRepository);
        const contactController = new ContactController(contactService);
        const contactView = new ContactView(contactController);
        
        await contactView.run();
        
        await database.disconnect();
        
    } catch (error) {
        console.error('Application error:', error.message);
        process.exit(1);
    }
}

process.on('SIGINT', async () => {
    const database = require('./config/database');
    await database.disconnect();
    process.exit(0);
});

main();