const fs = require("fs"); // Módulo para manejar archivos

// ====== 1. Definir los datos ======
const personas = [
  { id: 1, nombre: "Ana", edad: 24 },
  { id: 2, nombre: "Carlos", edad: 29 },
  { id: 3, nombre: "Lucía", edad: 33 },
  { id: 4, nombre: "José", edad: 28 }
];

// ====== 2. Crear el contenido del CSV con punto y coma (;) ======
let contenido = "id;nombre;edad\n"; // Encabezado
personas.forEach((p) => {
  contenido += `${p.id};${p.nombre};${p.edad}\n`;
});

// ====== 3. Guardar el archivo CSV ======
fs.writeFileSync("personas.csv", contenido, "utf8");

console.log("✅ Archivo 'personas.csv' creado correctamente.");
