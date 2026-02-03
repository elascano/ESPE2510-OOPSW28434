import express from "express";
const app = express();
const port = 4014; //always is a number

app.get('/',(req,res)=>{//the "/" is the path to my http
    res.send('Welcome to Jaime Mikael Hidalgo Nogales server!')
});

app.listen(port,()=>{
    console.log(`Mikael's Server is running on port ${port}`);
});