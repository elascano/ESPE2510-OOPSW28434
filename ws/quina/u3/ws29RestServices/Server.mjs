import express from "express";
const app = express();
const port = 4015;

app.get('/', (req, res) => {
    res.send('Welcome to Maryuri Qui&ntilde;a my server!');
});
app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});