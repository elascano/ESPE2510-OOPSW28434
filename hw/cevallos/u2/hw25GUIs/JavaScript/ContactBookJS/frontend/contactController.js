class ContactController {
    constructor() {
        this.apiBaseUrl = 'http://localhost:3000/api';  
        this.contacts = [];
        
        console.log('🔧 ContactController inicializado');
        console.log('📡 Backend URL:', this.apiBaseUrl);
    }

    async loadContacts() {
        try {
            console.log('🔄 Cargando contactos desde backend...');
            const response = await fetch(`${this.apiBaseUrl}/contacts`);
            
            if (response.ok) {
                this.contacts = await response.json();
                console.log(`✅ Cargados ${this.contacts.length} contactos desde MongoDB`);
                
                this.updateContactsTable();
                
                this.updateContactCount();
            } else {
                console.error('Error loading contacts from backend');
                this.contacts = [];
            }
        } catch (error) {
            console.error('Network error loading contacts:', error);
            this.contacts = [];
        }
    }

    calculateAge(birthDateStr) {
        if (!birthDateStr) return 0;
        
        const birthDate = new Date(birthDateStr);
        const today = new Date();
        
        let age = today.getFullYear() - birthDate.getFullYear();
        const monthDiff = today.getMonth() - birthDate.getMonth();
        
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }
        
        return age;
    }

    async getNextContactId() {
    try {
        if (this.contacts.length === 0) {
            console.log('🔄 Obteniendo contactos para calcular próximo ID...');
            const response = await fetch(`${this.apiBaseUrl}/contacts`);
            
            if (response.ok) {
                const contacts = await response.json();
                this.contacts = contacts; 
                
                if (contacts.length === 0) {
                    return 1; 
                }
                
                const maxId = Math.max(...contacts.map(contact => contact.id));
                return maxId + 1;
            } else {
                return 1; 
            }
        } else {
            const maxId = Math.max(...this.contacts.map(contact => contact.id));
            return maxId + 1;
        }
    } catch (error) {
        console.error('Error obteniendo próximo ID:', error);
        return this.contacts.length + 1; 
    }
}

    async updateNextIdDisplay() {
        try {
            const nextId = await this.getNextContactId();
            document.getElementById('nextId').textContent = nextId;
        } catch (error) {
            console.error('Error actualizando Next ID:', error);
            document.getElementById('nextId').textContent = '1';
        }
    }

    updateContactsTable() {
        const tbody = document.getElementById('contactsBody');
        if (!tbody) return; 
        
        tbody.innerHTML = '';
        
        this.contacts.forEach(contact => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${contact.id}</td>
                <td>${contact.firstName}</td>
                <td>${contact.lastName}</td>
                <td>${contact.age}</td>
                <td>${contact.typeOfContact}</td>
                <td>${contact.sex}</td>
                <td>
                    <button class="btn-edit" onclick="window.contactController.editContact(${contact.id})">Edit</button>
                    <button class="btn-delete" onclick="window.contactController.deleteContact(${contact.id})">Delete</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    }

    findContactById(id) {
        return this.contacts.find(contact => contact.id === id) || null;
    }

    updateContactCount() {
        const countElement = document.getElementById('contactCount');
        if (countElement) {
            countElement.textContent = `(${this.contacts.length} contacts)`;
        }
    }

    async refreshContacts() {
        await this.loadContacts();
        await this.updateNextIdDisplay();
    }
    
    async saveContact(contactData, birthDateStr) {
        try {
            const age = this.calculateAge(birthDateStr);
            
            const nextId = await this.getNextContactId();
            
            const contactToSave = {
                id: nextId,
                firstName: contactData.firstName,
                lastName: contactData.lastName,
                age: age,
                birthDate: birthDateStr,
                typeOfContact: contactData.typeOfContact,
                sex: contactData.sex,
                hobbies: contactData.hobbies || [],
                comments: contactData.comments || ""
            };
            
            console.log('Enviando a backend:', contactToSave);
            
            const response = await fetch(`${this.apiBaseUrl}/contacts`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(contactToSave)
            });
            
            if (response.ok) {
                const savedContact = await response.json();
                this.contacts.push(savedContact);
                
                console.log('✅ Contacto guardado en MongoDB:', savedContact);
                
                this.updateContactsTable();
                this.updateContactCount();
                await this.updateNextIdDisplay();
                
                return {
                    success: true,
                    id: nextId,
                    age: age
                };
            } else {
                const errorData = await response.json();
                throw new Error(errorData.error || 'Failed to save contact');
            }
            
        } catch (error) {
            console.error('Error saving contact:', error);
            return {
                success: false,
                message: error.message
            };
        }
    }
    
    async editContact(id) {
        const contact = this.findContactById(id);
        if (!contact) {
            alert('Contact not found');
            return;
        }
        
        document.getElementById('firstName').value = contact.firstName;
        document.getElementById('lastName').value = contact.lastName;
        document.getElementById('birthDate').value = contact.birthDate;
        document.getElementById('age').textContent = `${contact.age} years`;
        document.getElementById('type').value = contact.typeOfContact;
        
        document.querySelector(`input[name="sex"][value="${contact.sex}"]`).checked = true;
        
        const checkboxes = document.querySelectorAll('.hobbies-grid input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.checked = contact.hobbies.includes(checkbox.value);
        });
        
        document.getElementById('comments').value = contact.comments || '';
        
        const saveBtn = document.getElementById('saveBtn');
        saveBtn.textContent = 'Update Contact';
        saveBtn.dataset.editingId = id;
        
        console.log('Editando contacto ID:', id);
    }

    async deleteContact(id) {
        if (!confirm(`Are you sure you want to delete contact ID ${id}?`)) {
            return;
        }
        
        try {
            const success = await this.deleteContactFromBackend(id);
            if (success) {
                console.log(`Contacto ${id} eliminado`);
                this.updateContactsTable();
                this.updateContactCount();
                await this.updateNextIdDisplay();
            } else {
                alert('Error deleting contact');
            }
        } catch (error) {
            console.error('Error deleting:', error);
            alert('Error deleting contact');
        }
    }

    async deleteContactFromBackend(id) {
        try {
            const response = await fetch(`${this.apiBaseUrl}/contacts/${id}`, {
                method: 'DELETE'
            });
            
            if (response.ok) {
                this.contacts = this.contacts.filter(contact => contact.id !== id);
                return true;
            } else {
                const errorData = await response.json();
                throw new Error(errorData.error || 'Failed to delete contact');
            }
        } catch (error) {
            console.error('Error deleting contact:', error);
            return false;
        }
    }

    async updateContact(contact) {
        try {
            const response = await fetch(`${this.apiBaseUrl}/contacts/${contact.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    firstName: contact.firstName,
                    lastName: contact.lastName,
                    age: contact.age,
                    typeOfContact: contact.typeOfContact,
                    sex: contact.sex,
                    hobbies: contact.hobbies,
                    comments: contact.comments
                })
            });
            
            if (response.ok) {
                const index = this.contacts.findIndex(c => c.id === contact.id);
                if (index !== -1) {
                    this.contacts[index] = await response.json();
                }
                return true;
            } else {
                const errorData = await response.json();
                throw new Error(errorData.error || 'Failed to update contact');
            }
        } catch (error) {
            console.error('Error updating contact:', error);
            return false;
        }
    }

    searchContactsByName(name) {
        const searchTerm = name.toLowerCase();
        
        return this.contacts
            .filter(contact => 
                contact.firstName.toLowerCase().includes(searchTerm) ||
                contact.lastName.toLowerCase().includes(searchTerm)
            )
            .map(data => new Contact(
                data.id,
                data.firstName,
                data.lastName,
                data.age,
                data.typeOfContact,
                data.sex,
                data.hobbies || [],
                data.comments || ""
            ));
    }

    countContacts() {
        return this.contacts.length;
    }
}