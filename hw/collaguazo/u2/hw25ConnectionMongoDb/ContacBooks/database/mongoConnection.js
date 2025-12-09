const mongoose = require("mongoose");

const connectDB = async () => {
    try {
        await mongoose.connect("mongodb+srv://Psblo:Pablo2006@cluster0.cadnlkx.mongodb.net/Contact?retryWrites=true&w=majority")
        console.log("Mongo conect!");
    } catch (err) {
        console.error("error to conect:", err);
    }
};

module.exports = { connectDB };