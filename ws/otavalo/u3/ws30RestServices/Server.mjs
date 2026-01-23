import express from "express";
const app = express();
const port = 4013;

app.get('/', (req, res) => {
    res.send('Welcome to Otavalo´s server!');    
});
app.listen(port, () =>{ 
    console.log(`Otavalo's Server is running on port http://localhost:${port}/`);
});