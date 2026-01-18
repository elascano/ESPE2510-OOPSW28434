import express from "express";
const app = express(); //constructor
const port = 4013; //only number

app.get('/', (req,res) => {  // the "/" is the first path of my url
    res.send(' Welcome to Otavalo   Villarreal Arelys Maholy server!');
});
app.listen (port, () => {
    console.log(`Otavalo's is running on port ${port}`);
})