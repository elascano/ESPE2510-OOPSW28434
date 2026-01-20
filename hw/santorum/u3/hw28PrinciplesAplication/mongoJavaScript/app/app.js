const express = require("express");
const path = require("path");
const connectDB = require("../controller/MongoConnection");
const toyRoutes = require("../routes/toyRoutes");

const app = express();

connectDB();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(express.static(path.join(__dirname, "../view")));

app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "../view/view.html"));
});

app.use("/toys", toyRoutes);

app.listen(3000, () => {
  console.log("Servidor corriendo en http://localhost:3000");
});
