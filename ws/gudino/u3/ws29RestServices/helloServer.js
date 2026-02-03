const http = require ( 'node:http');
const hostname = '127.0.0.1'; //10.9.8.169
const port = 5013; //is unique for say that, 5009 for the number of the list
const server = http.createServer((req,res) => {
    res.statusCode = 200; //200 in web meaning okey
    res.setHeader('Content-Type', 'text/html');
    res.end('Hello, <b> Web Developers! <b> from <i> Bryan Gudino </i>'); //for tilde &aacute 
});

server.listen(port, hostname, () => {
    console.log(`Bryan Server running at http://${hostname}:${port}/`);
});