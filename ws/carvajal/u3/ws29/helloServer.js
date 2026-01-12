const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5004;
const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/plain');
  res.end('Hello, <Web> Developers! </b> from <i>Josue Carvajal </i>\n');
});
server.listen(port, hostname, () => {
  console.log(`Josue´s Server running at http://${hostname}:${port}/`);
});