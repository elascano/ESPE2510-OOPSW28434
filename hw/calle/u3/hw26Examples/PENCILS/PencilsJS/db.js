///npm init -y
//npm install express mongoose dotenv
//npm install express mongoose
const mongoose = require('mongoose');

const connectDB = async () => {
    try {
        await mongoose.connect('mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/PencilsDB');
        console.log("¡Conectado a MongoDB con éxito!");
    } catch (err) {
        console.error("Error de conexión:", err);
    }
};

module.exports = connectDB;