const express = require("express");
const connectDB = require("./config/database");
const productRoutes = require("./routes/productroutes");
const cors = require("cors");

const app = express();

// Conexión a MongoDB
connectDB();

// Middleware
app.use(cors());
app.use(express.json());

// Montamos rutas
app.use("/api/products", productRoutes);

app.listen(3000, () => {
    console.log("Servidor corriendo en http://localhost:3000");
});
