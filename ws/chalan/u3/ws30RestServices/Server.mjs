import express from "express";
const app= express();
const port = 4006;

app.get('/', (req, res)=> {
res.send('welcome to kevin  Chalan Server');
});
app.listen(port, ()=> {
    console.log(`Kevin' s server runing on port http://localhost:${port}`);
});