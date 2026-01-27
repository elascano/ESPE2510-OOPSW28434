const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5006;
const server = http.createServer((req, res)=> {
    res.statuscode = 200;
    res.setHeader('Content-Type', 'text/html');
    res.end('Hello,<b> Web Developres?</b> from<i> Kevin Chalan</i>');
});
server.listen(port,hostname, () => {
    console.log(`Kevin' s server runing at http://${hostname}:${port}/`);
});