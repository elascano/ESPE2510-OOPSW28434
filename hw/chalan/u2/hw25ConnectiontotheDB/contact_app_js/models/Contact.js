const mongoose = require("mongoose");

const ContactSchema = new mongoose.Schema({
    id: { type: Number, required: true, unique: true }, 
    firstName: { type: String, required: true }, 
    lastName: { type: String, required: true },
    birthDate: { type: Date }, 
    age: { type: Number },
    typeOfContact: { type: String }, 
    sex: { type: String },
    hobbies: { type: [String], default: [] }, 
    comments: { type: String, default: "" }
});

module.exports = mongoose.model("Contact", ContactSchema);