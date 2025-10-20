const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function preguntar(pregunta) {
  return new Promise(resolve => rl.question(pregunta, respuesta => resolve(respuesta)));
}

async function main() {
  while (true) {
    let numeroIngresado = parseInt(await preguntar("Enter the table number you want to view: "));

    console.log(`\nMultiplication table of the number ${numeroIngresado}\n`);
    for (let i = 1; i <= 12; i++) {
      console.log(`${numeroIngresado} * ${i} = ${numeroIngresado * i}`);
    }

    console.log("\nDo you want to re-enter another number?");
    console.log("- Enter 1 to re-enter another number");
    console.log("- Enter 0 to exit");

    let numeroRepeticion;
    while (true) {
      numeroRepeticion = parseInt(await preguntar("Enter an option: "));
      if (numeroRepeticion !== 0 && numeroRepeticion !== 1) {
        console.log("ERROR, invalid option. Try again");
      } else {
        break;
      }
    }

    if (numeroRepeticion === 1) {
      continue;
    } else {
      console.log("Leaving...");
      rl.close();
      break;
    }
  }
}

main();
