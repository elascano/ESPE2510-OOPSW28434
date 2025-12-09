const { getDb } = require("../utils/mongodbConnection");
const JsonManager = require("../utils/jsonManager");

class Contact {
    constructor(firstName, lastName, birthDate, typeOfContact, sex, hobbies, comments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
        this.age = this.calculateAge();
        this.createdAt = new Date();
    }

    calculateAge() {
        const birth = new Date(this.birthDate);
        const today = new Date();
        let age = today.getFullYear() - birth.getFullYear();
        const month = today.getMonth() - birth.getMonth();

        if (month < 0 || (month === 0 && today.getDate() < birth.getDate())) {
            age--;
        }
        return age;
    }

    async save() {
        const db = getDb();

        // Guardar en MongoDB
        await db.collection("Contacts").insertOne(this);

        // Guardar también en JSON mediante el utilitario
        JsonManager.saveContact(this);
    }

    static async getAll() {
        const db = getDb();
        return await db.collection("Contacts").find().toArray();
    }
}

module.exports = Contact;
