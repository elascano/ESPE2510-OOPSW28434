import express from "express";
const app = express();
const port = 4002;

app.get('/', (req, res) => {
    res.send('Welcome to Bonilla Arelis server!');
});
app.listen(port, () => {
    console.log(`Arelis server running on port http://localhost:${port}`);
});