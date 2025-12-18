document.addEventListener('DOMContentLoaded', () => {
    const SERVER_URL = "http://localhost:3000/api/contacts";

    const idInput = document.getElementById('id');
    const firstNameInput = document.getElementById('firstName');
    const lastNameInput = document.getElementById('lastName');
    const birthDateInput = document.getElementById('birthDate');
    const ageInput = document.getElementById('age');
    const contactTypeSelect = document.getElementById('contactType');
    const sexInputs = document.querySelectorAll('input[name="sex"]');
    const hobbiesCheckboxes = document.querySelectorAll('#hobbiesBox input[type="checkbox"]');
    const commentsTextarea = document.getElementById('comments');
    const saveBtn = document.getElementById('saveBtn');
    const deleteBtn = document.getElementById('deleteBtn');
    const contactsTableBody = document.querySelector('#contactsTable tbody');
    const uploadBtn = document.getElementById('uploadBtn');

    let selectedContactId = null;

    const today = new Date().toISOString().split('T')[0];
    birthDateInput.value = today;
    calculateAge();

    loadContactsTable();

    function calculateAge() {
        const birthDateStr = birthDateInput.value;
        if (!birthDateStr) {
            ageInput.value = '';
            return;
        }

        const birthDate = new Date(birthDateStr);
        const today = new Date();
        
        let age = today.getFullYear() - birthDate.getFullYear();
        const monthDiff = today.getMonth() - birthDate.getMonth();
        
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }

        ageInput.value = age >= 0 ? age : 0;
    }

    birthDateInput.addEventListener('change', calculateAge);

    function validateNameField(value, fieldName) {
        const trimmedValue = value.trim();

        if (trimmedValue.includes(' ')) {
            alert(`Validation Error: The '${fieldName}' field must be a single word (no internal spaces allowed).`);
            return false;
        }

        if (/\d/.test(trimmedValue)) {
            alert(`Validation Error: The '${fieldName}' field cannot contain numbers.`);
            return false;
        }

        return true;
    }
    
    function getSelectedSex() {
        let selectedSex = '';
        sexInputs.forEach(radio => { if (radio.checked) selectedSex = radio.value; });
        return selectedSex;
    }
    
    function getSelectedHobbies() {
        const selectedHobbies = [];
        hobbiesCheckboxes.forEach(checkbox => { if (checkbox.checked) selectedHobbies.push(checkbox.value); });
        return selectedHobbies;
    }

    async function saveContact() {
        const firstName = firstNameInput.value.trim();
        const lastName = lastNameInput.value.trim();
        const birthDate = birthDateInput.value;

        if (!firstName || !lastName || !birthDate) {
            alert("Error: First Name, Last Name, and Birth Date are required.");
            return;
        }
        if (!validateNameField(firstName, "First Name")) return;
        if (!validateNameField(lastName, "Last Name")) return;
        
        const contactData = {
            firstName: firstName, 
            lastName: lastName,
            birthDate: birthDate, 
            age: parseInt(ageInput.value),
            contactType: contactTypeSelect.value, 
            sex: getSelectedSex(),
            hobbies: getSelectedHobbies(),
            comments: commentsTextarea.value.trim()
        };

        const confirmationMessage = `¿Desea guardar el siguiente contacto?\n\nContact{\n  First Name: ${contactData.firstName},\n  Last Name: ${contactData.lastName},\n  Birth Date: ${contactData.birthDate},\n  Age: ${contactData.age},\n  Type: ${contactData.contactType},\n  Sex: ${contactData.sex},\n  Hobbies: [${contactData.hobbies.join(', ')}],\n  Comments: '${contactData.comments}'\n}`;

        if (!confirm(confirmationMessage)) {
            alert("Contact saving process canceled.");
            return;
        }

        try {
            const response = await fetch(SERVER_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(contactData)
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.error || `HTTP error! status: ${response.status}`);
            }

            const savedContact = await response.json();
            alert(`Success: Contact saved successfully with ID: ${savedContact._id}`);
            
            idInput.value = savedContact._id;
            resetForm();
            loadContactsTable(); 

        } catch (error) {
            console.error("Database Error:", error);
            alert(`Database Error: Could not save contact. ${error.message}`);
        }
    }

    async function uploadFromCloud() {
        await loadContactsTable();
        alert("All contacts have been synchronized/loaded from the Cloud (MongoDB).");
    }

// public/script.js

    async function loadContactsTable() {
        contactsTableBody.innerHTML = ''; 
        try {
            const response = await fetch(SERVER_URL);
            if (!response.ok) throw new Error("Failed to fetch contacts");
            const contacts = await response.json();

            contacts.forEach(contact => {
                const row = contactsTableBody.insertRow();
                
                // 1. Manejo de Nombres (Universal)
                // Primero intentamos leer los campos individuales (Java/JS)
                let firstName = contact.firstName;
                let lastName = contact.lastName;

                // Si no se encuentran, intentamos dividir el campo 'name' (Python)
                if (!firstName && contact.name) {
                    const parts = contact.name.trim().split(' ', 2);
                    firstName = parts[0] || 'N/A';
                    lastName = parts[1] || '';
                }
                
                // 2. Manejo de Edad (Universal)
                const age = contact.age || contact.age_years || 'N/A'; 
                
                // 3. Manejo de Tipo (Universal)
                // Revisa typeOfContact (Java) o contactType (Python/JS)
                const contactType = contact.typeOfContact || contact.contactType || 'N/A';

                row.insertCell().textContent = contact._id;
                row.cells[0].style.display = 'none';

                // Usamos los campos unificados para la tabla
                row.insertCell().textContent = firstName || 'N/A';
                row.insertCell().textContent = lastName || '';
                row.insertCell().textContent = age; 
                row.insertCell().textContent = contactType;
                row.insertCell().textContent = contact.sex || 'N/A';
                
                row.addEventListener('click', () => selectContactRow(row, contact._id));
            });
        } catch (error) {
            console.error("Error loading contacts:", error);
            alert(`Error loading contacts: ${error.message}`);
        }
    }
    
    function selectContactRow(row, id) {
        document.querySelectorAll('#contactsTable tbody tr').forEach(r => r.style.backgroundColor = 'white');
        
        row.style.backgroundColor = '#d1e4ff';
        selectedContactId = id;
        deleteBtn.disabled = false;
        idInput.value = id; 
    }

    function resetForm() {
        selectedContactId = null;
        deleteBtn.disabled = true;
        idInput.value = '';
        firstNameInput.value = '';
        lastNameInput.value = '';
        commentsTextarea.value = '';
        birthDateInput.value = new Date().toISOString().split('T')[0];
        calculateAge(); 
        contactTypeSelect.value = 'Family';
        document.querySelector('input[name="sex"][value="Male"]').checked = true;
        hobbiesCheckboxes.forEach(checkbox => { checkbox.checked = false; });
    }

    deleteBtn.addEventListener('click', async () => {
        if (!selectedContactId) {
            alert("Please select a contact to delete.");
            return;
        }
        
        const contactName = document.getElementById('contactsTable').querySelector(`tr[style*='d1e4ff']`).cells[1].textContent;
        
        if (!confirm(`Are you sure you want to delete contact ${contactName} (ID: ${selectedContactId})?`)) {
            return;
        }

        try {
            const response = await fetch(`${SERVER_URL}/${selectedContactId}`, {
                method: 'DELETE'
            });

            if (!response.ok) throw new Error("Failed to delete contact");

            alert(`Contact ${contactName} deleted successfully.`);
            resetForm();
            loadContactsTable();

        } catch (error) {
            console.error("Error deleting contact:", error);
            alert(`Error deleting contact: ${error.message}`);
        }
    });

    saveBtn.addEventListener('click', saveContact);
    uploadBtn.addEventListener('click', uploadFromCloud);
});