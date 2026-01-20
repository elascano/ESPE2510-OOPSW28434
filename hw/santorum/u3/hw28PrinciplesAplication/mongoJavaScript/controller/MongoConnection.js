const mongoose = require("mongoose");

const connectDB = async () => {
  try {
    await mongoose.connect(
      "mongodb+srv://thais:thais@cluster0.9yfzmcp.mongodb.net/ToyShopDB"
    );
    console.log("MongoDB conectado a ToyShopDB");
  } catch (error) {
    console.error("Error al conectar MongoDB", error);
    process.exit(1);
  }
};

module.exports = connectDB;
