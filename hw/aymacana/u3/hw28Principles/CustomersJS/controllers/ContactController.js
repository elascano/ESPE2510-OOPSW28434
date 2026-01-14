const Table = require('cli-table3');

class ContactController {
    constructor(contactService) {
        this.contactService = contactService;
    }

    async displayAllContacts() {
        try {
            const contacts = await this.contactService.getAllContacts();
            
            console.log('\n' + '='.repeat(120));
            console.log('CONTACTS LIST');
            console.log('='.repeat(120));
            
            if (contacts.length === 0) {
                console.log('No contacts found');
                return;
            }

            const table = new Table({
                head: ['ID', 'Full Name', 'Email', 'Type', 'Discount', 'Total Sale', 'Final Price', 'Savings'],
                colWidths: [8, 20, 25, 10, 10, 12, 12, 12]
            });

            contacts.forEach(contact => {
                table.push([
                    contact.id,
                    contact.fullName,
                    contact.email,
                    contact.type,
                    `${contact.discount}%`,
                    this.contactService.formatCurrency(contact.totalSale),
                    this.contactService.formatCurrency(contact.finalPrice),
                    this.contactService.formatCurrency(contact.savings)
                ]);
            });

            console.log(table.toString());
            console.log(`\nTotal contacts: ${contacts.length}`);
            
        } catch (error) {
            console.error(`Error: ${error.message}`);
        }
    }

    async displayContactById(id) {
        try {
            const contact = await this.contactService.getContactById(id);
            
            console.log('\n' + '='.repeat(50));
            console.log('CONTACT DETAILS');
            console.log('='.repeat(50));
            
            console.log(`ID: ${contact.id}`);
            console.log(`Full Name: ${contact.fullName}`);
            console.log(`Email: ${contact.email}`);
            console.log(`Type: ${contact.type}`);
            console.log(`Discount: ${contact.discount}%`);
            console.log(`Total Sale: ${this.contactService.formatCurrency(contact.totalSale)}`);
            console.log(`Final Price: ${this.contactService.formatCurrency(contact.finalPrice)}`);
            console.log(`Savings: ${this.contactService.formatCurrency(contact.savings)}`);
            
            console.log('='.repeat(50));
            
        } catch (error) {
            console.error(`Error: ${error.message}`);
        }
    }
}

module.exports = ContactController;