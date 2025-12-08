class Contact {

    constructor(id, firstName, lastName, age, typeOfContact, sex, hobbies = [], comments) {
        this._id = id;
        this._firstName = firstName;
        this._lastName = lastName;
        this._age = age;
        this._typeOfContact = typeOfContact;
        this._sex = sex;
        this._hobbies = hobbies;
        this._comments = comments;
    }

    get id() {
        return this._id;
    }
    set id(id) {
        this._id = id;
    }

    get firstName() {
        return this._firstName;
    }
    set firstName(firstName) {
        this._firstName = firstName;
    }

    get lastName() {
        return this._lastName;
    }
    set lastName(lastName) {
        this._lastName = lastName;
    }

    get age() {
        return this._age;
    }
    set age(age) {
        this._age = age;
    }

    get typeOfContact() {
        return this._typeOfContact;
    }
    set typeOfContact(typeOfContact) {
        this._typeOfContact = typeOfContact;
    }

    get sex() {
        return this._sex;
    }
    set sex(sex) {
        this._sex = sex;
    }

    get hobbies() {
        return this._hobbies;
    }
    set hobbies(hobbies) {
        this._hobbies = hobbies;
    }

    get comments() {
        return this._comments;
    }
    set comments(comments) {
        this._comments = comments;
    }

    toString() {
        return `Contact{\n` +
               `id=${this._id}, \n` +
               `firstName=${this._firstName}, \n` +
               `lastName=${this._lastName}, \n` +
               `age=${this._age}, \n` +
               `typeOfContact=${this._typeOfContact}, \n` +
               `sex=${this._sex}, \n` +
               `hobbies=${JSON.stringify(this._hobbies)}, \n` +
               `comments=${this._comments}}`;
    }

}
module.exports = Contact;