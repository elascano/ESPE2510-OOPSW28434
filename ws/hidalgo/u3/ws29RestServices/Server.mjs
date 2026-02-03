import express from "express";
const app = express();
const port = 4020;

app.get('/', (req, res) => {
    res.send('Welcome to Hidalgo´s server!');    
});
app.listen(port, () =>{ 
    console.log(`Mikael´s Server is running on port http://localhost:${port}/`);
});