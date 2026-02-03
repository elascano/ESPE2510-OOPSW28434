const mongoose = require("mongoose");

const connectDB = async () => {
    try {
        await mongoose.connect(
            "mongodb+srv://Psblo:Pablo2006@cluster0.cadn1kx.mongodb.net/productdb?retryWrites=true&w=majority"
        );

        console.log("MongoDB Atlas conectado");
    } catch (error) {
        console.error("Error MongoDB:", error.message);
        process.exit(1);
    }
};

module.exports = connectDB;
