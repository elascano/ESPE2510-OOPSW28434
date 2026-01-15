const mongoose = require("mongoose");

const ToolSchema = new mongoose.Schema(
  {
    id: { type: String, required: true, trim: true, unique: true },
    name: { type: String, required: true, trim: true },

    price: { type: Number, required: true, min: 0 },
    ivaRate: { type: Number, required: true, min: 0 },
    priceWithIva: { type: Number, required: true, min: 0 },

    stock: { type: Number, required: true, min: 0 },
    description: { type: String, required: true, trim: true }
  },
  {
    timestamps: true,
    collection: "tools"
  }
);

module.exports = mongoose.model("Tool", ToolSchema);
