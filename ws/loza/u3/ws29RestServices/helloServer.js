const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5011;
const server = http.createServer((req, res) => {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'text/html');
    res.end('Hello, <b>Web Developers!</b> from <i>Steven Loza</i>');
});
server.listen(port, hostname, () => {
    console.log(`Steven server running at http://${hostname}:${port}`);
});