class Contact {
    constructor(id, firstName, lastName, birthDate, age, typeOfContact, sex, hobbies, comments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = age;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
    }

    toString() {
        return `Contact { id=${this.id}, firstName=${this.firstName}, lastName=${this.lastName},
         birthDate=${this.birthDate}, age=${this.age}, type=${this.typeOfContact}, sex=${this.sex},
         hobbies=${this.hobbies}, comments=${this.comments} }`;
    }
}

module.exports = Contact;