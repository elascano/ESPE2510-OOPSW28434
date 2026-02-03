const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5005;
const server = http.createServer((req, res) => {
    res.StatusCode = 200;
    res.setHeader('Content-Type', 'text/html');
    res.end('Hello, <b>Web Developers!</b> from <i>Mateo Cevallos</i>');
});

server.listen(port, hostname, () => {
    console.log(`Mateo's Server running at http://${hostname}:${port}/`);
});