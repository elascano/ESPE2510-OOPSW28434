const mongoose = require("mongoose");

const NotebookSchema = new mongoose.Schema({
    name: String,
    quantity: Number,
    price: Number,
    subtotal: Number,
    iva: Number,
    total: Number
});

module.exports = mongoose.model("Notebook", NotebookSchema);

