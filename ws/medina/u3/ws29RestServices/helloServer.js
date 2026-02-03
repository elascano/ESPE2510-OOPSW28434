const http = require ( 'node:http' );
const hostname = '127.0.0.1';
const port = 5012;
const server = http.createServer ( ( req, res ) => { //request and response

    res.statusCode = 200;
    res.setHeader ( 'Content-Type', 'text/html' );
    res.end ( 'Hello Web Developers from <i>Joseph Medina</i>' );

} );

server.listen ( port, hostname, () => {
    console.log ( `Joseph´ s Server running at http://${ hostname }:${ port }/` );
} );