const mongoose = require("mongoose");

const connectDB = async () => {
    try {
        await mongoose.connect(
            "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contac"
        );
        console.log("Mongo conect!");
    } catch (err) {
        console.error("error to conect:", err);
    }
};

module.exports = { connectDB };
