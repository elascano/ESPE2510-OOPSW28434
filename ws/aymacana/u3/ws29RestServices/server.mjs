import express from "express";
const app = express();
const port = 4001;

app.get('/', (req, res) => {
    res.send('Welcome to Mateo`s server!');
});
app.listen(port , () => {
    console.log(`Mateo's is running on port http://localhost:${port}/`);
});