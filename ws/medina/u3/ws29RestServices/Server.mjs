import express from "express";

const app = express();
const port = 4012;

app.get ( '/', ( req, res ) => {
    res.send ( 'Welcome to Medina Joseph Server' );
} );

app.listen ( port, () => {
    console.log ( `Medina´s Server is running at http://localhost:${ port }` );
}
);