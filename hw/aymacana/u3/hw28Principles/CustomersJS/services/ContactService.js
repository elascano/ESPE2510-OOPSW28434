class ContactService {
    constructor(contactRepository) {
        this.contactRepository = contactRepository;
    }

    async getAllContacts() {
        return await this.contactRepository.findAll();
    }

    async getContactById(id) {
        return await this.contactRepository.findById(id);
    }

    formatCurrency(amount) {
        return new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
            minimumFractionDigits: 2
        }).format(amount);
    }
}

module.exports = ContactService;