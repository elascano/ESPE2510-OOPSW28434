const express = require("express");
const path = require("path");
const toolRoutes = require("./controller/toolRoutes"); 

const app = express();
const PORT = 3000;

app.use(express.json());

app.use(express.static(path.join(__dirname, "view")));

app.use("/api/tools", toolRoutes);

app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "view", "index.html"));
});

app.listen(PORT, "0.0.0.0", () => {
  console.log(` Server running at http://localhost:${PORT}`);
});