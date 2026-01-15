import express from "express";
const app = express();
const port = 4001;

app.get('/', (req, res) => {
    res.send('Welcome to my server Paulo Ramos');
});

app.listen(port, () => {
    console.log(`Paulo´s server is running on port ${port}`);
});