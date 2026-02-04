import express from "express";
const app = express(); //constructor
const port = 4009; //only number

app.get('/', (req,res) => {  // the "/" is the first path of my url
    res.send(' Welcome to Gudino Bryan server!');
});
app.listen (port, () => {
    console.log(`Gudino's is running on port ${port}`);
})