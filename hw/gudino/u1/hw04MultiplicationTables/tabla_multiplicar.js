const readline = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout
});

function imprimirTabla(numero) {
  console.log(`\nTabla de multiplicar del ${numero}:`);
  console.log("-".repeat(20));
  for (let i = 1; i <= 12; i++) {
    const resultado = numero * i;
    console.log(`${numero} x ${i} = ${resultado}`);
  }
}

function pedirNumero() {
  readline.question("Ingresa un número del 1 al 12 (0 para salir): ", (input) => {
    const num = parseInt(input);

    if (isNaN(num)) {
      console.log("Eso no es un número válido. Intenta de nuevo.");
      pedirNumero();
    } else if (num === 0) {
      console.log("¡Adiós!");
      readline.close();
    } else if (num >= 1 && num <= 12) {
      imprimirTabla(num);
      pedirNumero(); // vuelve a pedir otro número
    } else {
      console.log("Por favor, ingresa un número entre 1 y 12.");
      pedirNumero();
    }
  });
}

// Iniciar el programa
pedirNumero();
