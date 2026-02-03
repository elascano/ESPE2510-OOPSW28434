import express from "express";
const app = express();
const port = 4009;

app.get('/', (req, res) => {
    res.send('Welcome to Gudino´s server!');    
});
app.listen(port, () =>{ 
    console.log(`Gudino's Server is running on port http://localhost:${port}/`);
});