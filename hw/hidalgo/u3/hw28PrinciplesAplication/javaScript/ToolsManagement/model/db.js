const mongoose = require("mongoose");

let connected = false;

const MONGO_URI =
  "mongodb+srv://Mikael:<Mikael1897>@cluster0.fpyoe9m.mongodb.net/?appName=Cluster0";

async function connectDB() {
  if (connected) return;

  mongoose.set("strictQuery", true);
  await mongoose.connect(MONGO_URI);

  connected = true;
  console.log(" Connected to MongoDB Atlas");
}

module.exports = { connectDB };
