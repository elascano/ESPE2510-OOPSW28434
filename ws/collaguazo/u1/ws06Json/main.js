import fs from "fs";
import readline from "readline";
import { Chicken } from "./chicken.js";
import { HenHouse } from "./henhouse.js";

const DATA_DIR = "./data";
const HENHOUSES_PATH = "./data/henhouses.json";

// Crear carpeta si no existe
if (!fs.existsSync(DATA_DIR)) {
  fs.mkdirSync(DATA_DIR);
}

// Crear archivo JSON si no existe
if (!fs.existsSync(HENHOUSES_PATH)) {
  fs.writeFileSync(HENHOUSES_PATH, "[]", "utf8");
}

function readHenHouses() {
  return JSON.parse(fs.readFileSync(HENHOUSES_PATH, "utf8"));
}

function saveHenHouses(henhouses) {
  fs.writeFileSync(HENHOUSES_PATH, JSON.stringify(henhouses, null, 2), "utf8");
}

function addHenHouse(rl, callback) {
  const henhouses = readHenHouses();

  rl.question("\nNombre del nuevo gallinero: ", name => {
    const id = henhouses.length + 1;
    const newHenHouse = new HenHouse(id, name);

    henhouses.push(newHenHouse.toJSON());
    saveHenHouses(henhouses);

    console.log(`Gallinero "${name}" creado.`);
    callback();
  });
}

function addChickenToHenHouse(rl, callback) {
  const henhouses = readHenHouses();

  if (henhouses.length === 0) {
    console.log("\nNo hay gallineros creados.");
    return callback();
  }

  console.log("\nGallineros disponibles:");
  henhouses.forEach(h => console.log(`[${h.id}] ${h.name}`));

  rl.question("ID del gallinero: ", hid => {
    const henHouse = henhouses.find(h => h.id == hid);

    if (!henHouse) {
      console.log("Gallinero no encontrado.");
      return callback();
    }

    rl.question("Nombre de la gallina: ", name => {
      rl.question("Color de plumas: ", featherColor => {
        rl.question("Edad: ", ageStr => {
          rl.question("¿Está mudando plumas? (s/n): ", moltingStr => {
            const molting = moltingStr.toLowerCase() === "s";
            const age = parseInt(ageStr);
            const id = (henHouse.chickens?.length || 0) + 1;

            const chicken = new Chicken(id, name, featherColor, age, molting);

            if (!henHouse.chickens) henHouse.chickens = [];
            henHouse.chickens.push(chicken.toJSON());

            saveHenHouses(henhouses);

            console.log(`Gallina "${name}" agregada al gallinero "${henHouse.name}".`);
            callback();
          });
        });
      });
    });
  });
}

function showHenHouses(callback) {
  const henhouses = readHenHouses();
  console.log("\n=== Gallineros registrados ===");

  if (henhouses.length === 0) {
    console.log("No hay gallineros.");
  } else {
    henhouses.forEach(h => {
      console.log(`\n${h.name} (ID ${h.id})`);
      if (!h.chickens || h.chickens.length === 0) {
        console.log("  - Sin gallinas.");
      } else {
        h.chickens.forEach(ch =>
          console.log(`  - ${ch.name} (${ch.featherColor}, ${ch.age} años, molting: ${ch.molting})`)
        );
      }
    });
  }
  callback();
}

// ========== MENÚ ==========
function mainMenu() {
  const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

  const menu = () => {
    console.log(`
=== MENÚ PRINCIPAL ===
1. Crear gallinero
2. Agregar gallina a un gallinero
3. Mostrar gallineros y gallinas
4. Salir
`);

    rl.question("Seleccione una opción: ", option => {
      switch (option) {
        case "1": addHenHouse(rl, menu); break;
        case "2": addChickenToHenHouse(rl, menu); break;
        case "3": showHenHouses(menu); break;
        default: console.log("Opción inválida."); menu();
      }
    });
  };

  menu();
}

mainMenu();
