const http = require('node:http');
const hostname = '127.0.0.1';
//const hostname = '10.9.8.137';
const port = 5015;
const server = http.createServer((req,res) => {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'text/html');
    res.end('Hello, <b>Web Developers!</b> from <i> Maryuri Qui&ntilde;a</i>');
});
server.listen(port, hostname, () =>{
    console.log(`Maryuri's Server running at http://${hostname}:${port}/`);

});
