const mongoose = require("mongoose");
const Product = require("./productModel");

class MongoRepository {
  constructor() {
    this.connected = false;
    this.mongoUri =
      process.env.MONGO_URI ||
      "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/ResourcesDB";
  }

  async connect() {
    if (this.connected) return;

    try {
      console.log("Using mongoDB URI", this.mongoUri);
      mongoose.set("strictQuery", true);

      await mongoose.connect(this.mongoUri);

      this.connected = true;
      console.log("Conected to MongoDB (Atlas)");
    } catch (err) {
      console.error(" Error conecting to MongoDB:", err?.message || err);
      throw err;
    }
  }

  async createProduct(productData) {
    await this.connect();
    return await new Product(productData).save();
  }

  async getAllProducts() {
    await this.connect();
    return await Product.find().sort({ createdAt: -1 }).lean();
  }

  async getProductById(id) {
    await this.connect();
    return await Product.findById(id).lean();
  }

  async updateProduct(id, productData) {
    await this.connect();
    return await Product.findByIdAndUpdate(id, productData, { new: true, runValidators: true }).lean();
  }

  async deleteProduct(id) {
    await this.connect();
    return await Product.findByIdAndDelete(id).lean();
  }
}

module.exports = MongoRepository;
