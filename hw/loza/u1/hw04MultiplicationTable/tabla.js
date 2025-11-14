const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

rl.question('Ingresa un número del 1 al 10: ', (input) => {
  const numero = parseInt(input);
  for (let i = 1; i <= 10; i++) {
    for (let j = 1; j <= 10; j++) {
      if (i === numero) {
        console.log(`${i} x ${j} = ${i * j}`);
      }
    }
  }
  rl.close();
});
