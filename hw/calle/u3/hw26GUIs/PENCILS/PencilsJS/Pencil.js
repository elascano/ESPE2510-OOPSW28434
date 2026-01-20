const mongoose = require('mongoose');

const PencilSchema = new mongoose.Schema({
    id: { type: String, required: true, unique: true },
    brand: String,
    color: String,
    price: Number
});

module.exports = mongoose.model('Pencil', PencilSchema);