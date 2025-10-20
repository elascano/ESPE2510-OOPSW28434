let menu = 1;

while (menu === 1) {
  console.log("===============================");
  console.log("     TABLAS DE MULTIPLICAR     ");
  console.log("===============================");

  let numero = parseInt(prompt(" Ingresa un número:"));
  console.log(`\nTabla del ${numero}:`);
  console.log("-------------------------------");

  for (let i = 1; i <= 12; i++) {
    console.log(`${numero} x ${i} = ${numero * i}`);
  }

  console.log("-------------------------------");
  menu = parseInt(prompt("¿Quieres probar otro número? (1=Sí / 2=No):"));
}

console.log("\nGracias por usar el programa ");
