const http = require ('node:http');
const hostname= '127.0.0.1';
const port = 5021;

const server = http.createServer((req, res)=> { //request and repose
    res.statusCode = 200;
    res.setHeader('Content-Type', 'text/html');
    res.end('Hello, <b>Web Developers!</b> from <i> Steven Loza </i>');

});
server.listen(port,hostname,()=>{
console.log ( `Steven´ s Server running at http://${ hostname }:${ port }/` );
});