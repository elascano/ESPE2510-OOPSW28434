const mongoose = require("mongoose");

const ProductSchema = new mongoose.Schema({
    name: String,
    quantity: Number,
    price: Number,
    subtotal: Number,
    iva: Number,
    total: Number
});

module.exports = mongoose.model("Product", ProductSchema);
