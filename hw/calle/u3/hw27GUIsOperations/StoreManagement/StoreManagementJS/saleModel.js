const mongoose = require('mongoose');

const saleSchema = new mongoose.Schema({
    productName: String,
    unitPrice: Number,
    quantity: Number,
    totalPrice: Number
});

module.exports = mongoose.model('Sale', saleSchema, 'Sales');