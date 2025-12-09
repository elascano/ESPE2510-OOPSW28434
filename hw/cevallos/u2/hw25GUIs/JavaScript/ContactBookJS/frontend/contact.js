class Contact {
    constructor(id, firstName, lastName, age, typeOfContact, sex, hobbies, comments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.typeOfContact = typeOfContact;
        this.sex = sex; 
        this.hobbies = hobbies || [];
        this.comments = comments || "";
    }

    toString() {
        const hobbiesStr = this.hobbies.length > 0 ? this.hobbies.join(", ") : "None";
        return `Contact:
Id: ${this.id}
FirstName: ${this.firstName}
LastName: ${this.lastName}
Age: ${this.age}
TypeOfContact: ${this.typeOfContact}
Sex: ${this.sex}
Hobbies: ${hobbiesStr}
Comments: ${this.comments}`;
    }

    toTableRow() {
        return `
            <tr data-id="${this.id}">
                <td>${this.id}</td>
                <td>${this.firstName}</td>
                <td>${this.lastName}</td>
                <td>${this.age}</td>
                <td>${this.typeOfContact}</td>
                <td>${this.sex}</td>
                <td class="contact-actions">
                    <button class="btn-edit" onclick="editContact(${this.id})">Edit</button>
                    <button class="btn-delete" onclick="deleteContact(${this.id})">Delete</button>
                </td>
            </tr>
        `;
    }

    toConfirmationMessage() {
        const confirmationText = `
                    CONTACT INFORMATION
• ID:              ${this.id}
• First Name:      ${this.firstName}
• Last Name:       ${this.lastName}
• Age:             ${this.age} years
• Type:            ${this.typeOfContact}
• Gender:          ${this.sex}

• Hobbies:         ${this.hobbies.length > 0 ? this.hobbies.map((h, i) => `\n                  
    ${i + 1}. ${h}`).join('') : 'None'}

• Comments:        ${this.comments || 'None'}

ID to be assigned: ${this.id}
        `;
        return confirmationText;
    }
}