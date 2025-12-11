const express = require("express");
const cors = require("cors");
const { connectDB } = require("./database/mongoConnection");
const contactRoutes = require("./routes/contactRoutes");
const path = require("path");

const app = express();

app.use(cors());
app.use(express.json());

connectDB();

app.use("/api/contacts", contactRoutes);

app.listen(3000, () => {
    console.log("server running in  en http://localhost:3000");
});

app.use(express.static(path.join(__dirname, "public")));