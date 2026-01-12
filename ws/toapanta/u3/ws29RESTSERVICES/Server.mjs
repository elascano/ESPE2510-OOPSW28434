import express from "express";
const app = express();
const port =4019;

app.get('/',(req,res)=>{
    res.send('Welcome to Adrian Toapanta server!');
});
app.listen(port,() => {
    console.log(`Server adrian is running on: http://localhost:${port}`);
});