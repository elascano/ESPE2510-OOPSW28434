class ContactApp {
    constructor() {
        this.controller = window.contactController || new ContactController();
        this.currentContactData = null;
        this.currentBirthDate = null;
        
        window.app = this;
        
        this.initialize();
    }

    async initialize() {
        console.log('🚀 ContactApp inicializado');
    
        this.initDatePicker();
        
        this.setupEventListeners();
        
        await this.updateNextId();
        this.updateAge();
        
        setTimeout(() => this.verifyBackendConnection(), 2000);
    }

    async verifyBackendConnection() {
        try {
            console.log('Verificando conexión con backend...');
            const response = await fetch(`${this.controller.apiBaseUrl}/contacts`);
            console.log('Backend respondiendo:', response.status);
            
            if (response.ok) {
                const contacts = await response.json();
                console.log(`Contactos en MongoDB: ${contacts.length}`);
                
                if (contacts.length > 0) {
                    console.log('Primer contacto en DB:', {
                        id: contacts[0].id,
                        nombre: `${contacts[0].firstName} ${contacts[0].lastName}`,
                        colección: 'contacts',
                        db: 'ContacsBook'
                    });
                }
            }
        } catch (error) {
            console.error('No se puede conectar al backend:', error);
            alert('No se puede conectar al servidor backend. Por favor ejecuta: cd backend && node server.js');
        }
    }

    initDatePicker() {
        try {
            flatpickr("#birthDate", {
                dateFormat: "Y-m-d",
                maxDate: "today",
                onChange: () => this.updateAge()
            });
            
            const defaultDate = new Date();
            defaultDate.setFullYear(defaultDate.getFullYear() - 25);
            document.getElementById('birthDate').value = defaultDate.toISOString().split('T')[0];
            this.updateAge();
        } catch (error) {
            console.error('Error inicializando date picker:', error);
        }
    }

    setupEventListeners() {
        document.getElementById('saveBtn').addEventListener('click', async (e) => {
            e.preventDefault();
            await this.saveContact();
        });
        
        document.getElementById('confirmYes').addEventListener('click', async () => {
            await this.confirmSave();
        });
        
        document.getElementById('confirmNo').addEventListener('click', () => {
            this.closeModal();
        });
        
        document.getElementById('confirmationModal').addEventListener('click', (e) => {
            if (e.target.id === 'confirmationModal') {
                this.closeModal();
            }
        });
        
        const otherCheckbox = document.getElementById('otherCheckbox');
        const otherHobbyInput = document.getElementById('otherHobby');
        
        otherCheckbox.addEventListener('change', () => {
            otherHobbyInput.disabled = !otherCheckbox.checked;
            if (!otherCheckbox.checked) {
                otherHobbyInput.value = '';
            }
        });
    }
    
    validateForm() {
        let isValid = true;
        this.clearErrors();
        
        const firstName = document.getElementById('firstName').value.trim();
        if (!firstName) {
            this.showError('firstName', 'First Name is required');
            isValid = false;
        } else if (/\d/.test(firstName)) {
            this.showError('firstName', 'First Name cannot contain numbers');
            isValid = false;
        } else if (/\s/.test(firstName)) {
            this.showError('firstName', 'First Name cannot contain spaces');
            isValid = false;
        }
        
        const lastName = document.getElementById('lastName').value.trim();
        if (!lastName) {
            this.showError('lastName', 'Last Name is required');
            isValid = false;
        } else if (/\d/.test(lastName)) {
            this.showError('lastName', 'Last Name cannot contain numbers');
            isValid = false;
        } else if (/\s/.test(lastName)) {
            this.showError('lastName', 'Last Name cannot contain spaces');
            isValid = false;
        }
        
        const birthDate = document.getElementById('birthDate').value;
        if (!birthDate) {
            this.showError('birthDate', 'Birth Date is required');
            isValid = false;
        } else {
            const dateObj = new Date(birthDate);
            const today = new Date();
            const minDate = new Date();
            minDate.setFullYear(today.getFullYear() - 150);
            
            if (dateObj > today) {
                this.showError('birthDate', 'Birth Date cannot be in the future');
                isValid = false;
            } else if (dateObj < minDate) {
                this.showError('birthDate', 'Birth Date cannot be more than 150 years ago');
                isValid = false;
            }
        }
        
        const type = document.getElementById('type').value;
        if (!type) {
            this.showError('type', 'Please select a contact type');
            isValid = false;
        }
        
        const otherCheckbox = document.getElementById('otherCheckbox');
        const otherHobby = document.getElementById('otherHobby').value.trim();
        if (otherCheckbox.checked && !otherHobby) {
            this.showError('otherHobby', 'Please enter the other hobby');
            isValid = false;
        }
        
        return isValid;
    }
    
    showError(fieldId, message) {
        const errorElement = document.getElementById(`${fieldId}Error`);
        if (errorElement) {
            errorElement.textContent = message;
            errorElement.style.display = 'block';
        }
        
        const field = document.getElementById(fieldId);
        if (field) {
            field.style.borderColor = '#e74c3c';
        }
    }
    
    clearErrors() {
        const errorElements = document.querySelectorAll('.error-message');
        errorElements.forEach(element => {
            element.textContent = '';
            element.style.display = 'none';
        });
        
        const fields = document.querySelectorAll('input, select');
        fields.forEach(field => {
            field.style.borderColor = '';
        });
    }
    
    async saveContact() {
        if (!this.validateForm()) {
            this.showAlert('Please fix the errors in the form', 'error');
            return;
        }
        
        const contactData = this.getFormData();
        const birthDate = document.getElementById('birthDate').value;
        
        try {
            const nextId = await this.controller.getNextContactId();
            
            const age = this.controller.calculateAge(birthDate);
            
            const tempContact = new Contact(
                nextId,
                contactData.firstName,
                contactData.lastName,
                age,
                contactData.typeOfContact,
                contactData.sex,
                contactData.hobbies,
                contactData.comments
            );
            
            this.showConfirmationModal(tempContact, contactData, birthDate);
            
        } catch (error) {
            console.error('Error preparing contact:', error);
            this.showAlert('Error preparing contact data', 'error');
        }
    }
    
    getFormData() {
        const hobbies = [];
        document.querySelectorAll('.hobbies-grid input[type="checkbox"]:checked').forEach(checkbox => {
            hobbies.push(checkbox.value);
        });
        
        const otherCheckbox = document.getElementById('otherCheckbox');
        const otherHobby = document.getElementById('otherHobby').value.trim();
        if (otherCheckbox.checked && otherHobby) {
            hobbies.push(otherHobby);
        }
        
        return {
            firstName: document.getElementById('firstName').value.trim(),
            lastName: document.getElementById('lastName').value.trim(),
            typeOfContact: document.getElementById('type').value,
            sex: document.querySelector('input[name="sex"]:checked').value,
            hobbies: hobbies,
            comments: document.getElementById('comments').value.trim()
        };
    }
    
    showConfirmationModal(tempContact, contactData, birthDate) {
        this.currentContactData = contactData;
        this.currentBirthDate = birthDate;
        
        const detailsElement = document.getElementById('confirmationDetails');
        detailsElement.textContent = `📝 NEW CONTACT\n\n${tempContact}\n\nAre you sure you want to save this contact?`;
        
        const modal = document.getElementById('confirmationModal');
        modal.style.display = 'flex';
    }
    
    closeModal() {
        const modal = document.getElementById('confirmationModal');
        modal.style.display = 'none';
    }
    
    async confirmSave() {
        this.closeModal();
        
        try {
            const result = await this.controller.saveContact(this.currentContactData, this.currentBirthDate);
            
            if (result.success) {
                this.showAlert(
                    `Contact saved successfully!\nName: ${this.currentContactData.firstName} ${this.currentContactData.lastName}\nID: ${result.id}\nAge: ${result.age} years`,
                    'success'
                );
                this.clearForm();
                await this.updateNextId(); 
            } else {
                this.showAlert(`Error: ${result.message}`, 'error');
            }
        } catch (error) {
            console.error('Error saving contact:', error);
            this.showAlert('Error saving contact to server', 'error');
        }
    }
    
    async updateNextId() {
        try {
            const nextId = await this.controller.getNextContactId();
            document.getElementById('nextId').textContent = nextId;
        } catch (error) {
            console.error('Error updating next ID:', error);
            document.getElementById('nextId').textContent = 'Error';
        }
    }
    
    updateAge() {
        const birthDate = document.getElementById('birthDate').value;
        if (birthDate) {
            const age = this.controller.calculateAge(birthDate);
            document.getElementById('age').textContent = `${age} years`;
        }
    }
    
    clearForm() {
        document.getElementById('contactForm').reset();
        
        document.getElementById('type').value = '';
        document.querySelector('input[name="sex"][value="Male"]').checked = true;
        document.getElementById('otherCheckbox').checked = false;
        document.getElementById('otherHobby').value = '';
        document.getElementById('otherHobby').disabled = true;
        
        const defaultDate = new Date();
        defaultDate.setFullYear(defaultDate.getFullYear() - 25);
        document.getElementById('birthDate').value = defaultDate.toISOString().split('T')[0];
        
        this.clearErrors();
        
        this.updateAge();
        
        document.getElementById('firstName').focus();
    }
    
    showAlert(message, type = 'info') {
        alert(message); 
    }
}

document.addEventListener('DOMContentLoaded', () => {
    console.log('DOM cargado');
    
    if (typeof ContactController === 'undefined') {
        console.error('❌ ContactController no está definido');
        alert('Error: ContactController no cargado. Verifica los scripts.');
        return;
    }
    
    new ContactApp();
});