// Importar la clase Pollo
const Gallina = require("./Modelo_de_pollo");

console.log(" Bienvenido al sistema de gallinas de la granja ");

// Crear una instancia de gallina
let gallina = new Gallina(1, "Maria", "cafe", 2, false);

// Mostrar el propietario
console.log(`El propietario del ave es: ${gallina.obtenerDueño()}`);

// Modificar atributos de la gallina
gallina.asignarID(1);
gallina.asignarNombre("Maria");
gallina.asignarColor("Marrón y blanco");
gallina.asignarEdad(6);
gallina.definirMuda(true);

// Mostrar información actualizada
console.log("Detalles actualizados de la gallina:");
console.log(gallina.toString());
