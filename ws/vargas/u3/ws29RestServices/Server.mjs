import express from "express";
const app = express();
const port = 4020;

app.get('/', (req, res) => {
    res.send('Welcome to Vargas´s server!');    
});
app.listen(port, () =>{ 
    console.log(`Vargas´s Server is running on port http://localhost:${port}/`);
});