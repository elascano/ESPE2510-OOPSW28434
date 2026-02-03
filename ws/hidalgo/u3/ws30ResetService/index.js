const port = 3014
const express = require("express");
const app = express();
const mongoose = require("mongoose");
mongoose.connect(``);

const db = mongoose.connection;
db.on("error",(error)=>console.error(error));
db.once("open",()=>console.log("System connected to MongoDb Database"));
app.use(express.json());
const customerRouter=require("./routes/customerRoutes");
app.use("/computerstore",customerRouter);
app.listen(port,()=>console.log("Pastor´s Computers Store is running on port --> " + port));