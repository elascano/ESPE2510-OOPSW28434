const mongoose = require("mongoose");

const ToySchema = new mongoose.Schema(
  {
    id: Number,
    name: String,
    price: Number,
    priceIva: Number
  },
  { versionKey: false }
);

module.exports = mongoose.model("Toy", ToySchema, "toys");
