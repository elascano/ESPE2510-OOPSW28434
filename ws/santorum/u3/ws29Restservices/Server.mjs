import express from "express";

const app = express();
const port = 4018;
const hostname = "localhost";

app.get("/", (req, res) => {
    res.send('Welcome to Thais Sant\&oacuterum\'s server!');
});

app.listen(port, () => {
    console.log(`Thais\' server is running on port  http://${hostname}:${port}/`);
});
