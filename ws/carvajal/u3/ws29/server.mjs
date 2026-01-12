import express from "express";
const app = express();
const port = 4004;

app.get('/', (req, res) => {
  res.send('Welcome to Josue Carvajal');

});
app.listen(port, () => {
  console.log(`Josue´s Express Server running on port $http://localhost:4004/ ${port}/`)
});