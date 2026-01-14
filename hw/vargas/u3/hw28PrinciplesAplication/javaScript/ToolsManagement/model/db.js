const mongoose = require("mongoose");

let connected = false;

const MONGO_URI =
  "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/ResourcesDB";

async function connectDB() {
  if (connected) return;

  mongoose.set("strictQuery", true);
  await mongoose.connect(MONGO_URI);

  connected = true;
  console.log(" Connected to MongoDB Atlas");
}

module.exports = { connectDB };
