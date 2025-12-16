const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const routes = require("./routes");

const app = express();
app.use(cors());
app.use(express.json());

mongoose.connect("mongodb+srv://Gabriel:Gabriel2007@cluster0.dgdm9az.mongodb.net/?appName=Cluster0");

app.use("/api", routes);

app.listen(3000, () => console.log("Servidor corriendo en http://localhost:3000"));
