// model/Contact.js
class Contact {
    constructor(id, name, phone, email, addres) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.addres = addres;
        this.arbitration = this.calculateArbitration();
    }

    calculateArbitration() {
        if (this.addres > 4) {
            return 10 / this.addres;
        }
        return 0;
    }

    static fromDocument(doc) {
        const contact = new Contact(
            doc.id,
            doc.name,
            doc.phone,
            doc.email,
            doc.addres
        );
        return contact;
    }

    toDocument() {
        return {
            id: this.id,
            name: this.name,
            phone: this.phone,
            email: this.email,
            addres: this.addres,
            arbitration: this.arbitration
        };
    }

    toString() {
        return `ID: ${this.id} | ${this.name} | ${this.phone} | ${this.email} | ${this.addres} | $${this.arbitration.toFixed(2)} c/u`;
    }

    displayDetails() {
        return `
╔════════════════════════════════════════╗
║           CONTACT                      ║
╠════════════════════════════════════════╣
║ ID: ${this.id.toString().padEnd(34)} ║
║ Nombre: ${this.name.padEnd(32)} ║
║ Telefono: ${this.phone.padEnd(29)} ║
║ Emial: ${this.email.padEnd(25)} ║
║ Direccion: ${this.addres.toString().padEnd(26)} ║
║ Arbitraje por jugador: $${this.arbitration.toFixed(2).padEnd(19)} ║
║ Costo total arbitraje: $10.00${"".padEnd(18)} ║
╚════════════════════════════════════════╝`;
    }
}

export default Contact;