const mongoose = require("mongoose");

const ProductSchema = new mongoose.Schema(
  {
    name: {
      type: String,
      required: true,
      trim: true
    },
    price: {
      type: Number,
      required: true,
      min: 0
    },
    ivaRate: {
      type: Number,
      required: true,
      min: 0
    },
    priceWithIva: {
      type: Number,
      required: true,
      min: 0
    }
  },
  {
    timestamps: true,
    collection: "products"
  }
);

module.exports = mongoose.model("ResourcesDB", ProductSchema);
