<<<<<<< HEAD
class Contact{
    constructor(id, first_name, last_name, age, type_of_contact, sex, hobbies, comments){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.type_of_contact = type_of_contact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
    }

    toString(){
        return `ID: ${this.id} \nFirst Name: ${this.first_name} \nLast Name: ${this.last_name} \nAge: ${this.age} \nType of Contact: ${this.type_of_contact} \nSex: ${this.sex} \nHobbies: ${this.hobbies} \nComments: ${this.comments}`;
    }
    
    getFirstName(){
        return this.first_name;
    }

    setFirstName(first_name){
        this.first_name = first_name;
    }

    getLastName(){
        return this.last_name;  
    }

    setLastName(last_name){
        this.last_name = last_name;
    }

    getAge(){
        return this.age;

    }

    setAge(age){
        this.age = age;
    }

    getTypeOfContact(){
        return this.type_of_contact;
    }

    setTypeOfContact(type_of_contact){
        this.type_of_contact = type_of_contact;
    }

    getSex(){
        return this.sex;
    }   
    setSex(sex){   
        this.sex = sex;
    }

    getHobbies(){
        return this.hobbies;
    }

    setHobbies(hobbies){
        this.hobbies = hobbies;
    }
}

module.exports = Contact;
=======
const mongoose = require('mongoose');

const contactSchema = new mongoose.Schema({
    id: String,
    firstName: String,
    lastName: String,
    birthDate: Date,   
    age: Number,       
    typeOfContact: String,
    sex: String,
    hobbies: [String],
    comments: String
});

module.exports = mongoose.model('Contact', contactSchema, 'Contact');
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
