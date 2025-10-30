import fs from 'fs';
import path from 'path';
import { Chicken } from '../model/Chicken.js';
import { ChickenCoop } from '../model/ChickenCoop.js';
import { ChickenFarmer } from '../model/ChickenFarmer.js';

// 🗂️ Definimos carpeta y archivo
const DATA_DIR = './data';
const FILE_PATH = path.join(DATA_DIR, 'chickens.json');

// 🧩 Verifica que la carpeta exista o la crea
if (!fs.existsSync(DATA_DIR)) {
  fs.mkdirSync(DATA_DIR);
}

export class ChickenFarmSimulator {
  constructor() {
    this.coop = new ChickenCoop(1);
    this.farmer = new ChickenFarmer('Josue');
  }

  // Crear una nueva gallina
  createChicken(name, age, color, molting = false) {
    const chicken = new Chicken(this.coop.getTotalChickens() + 1, name, age, color, molting);
    this.coop.addChicken(chicken);
    console.log(` Gallina ${name} creada y añadida al gallinero.`);
    return chicken;
  }

  // Guardar gallinas en un archivo JSON
  saveChickensToFile() {
    const chickensData = this.coop.listChickens();
    fs.writeFileSync(FILE_PATH, JSON.stringify(chickensData, null, 2), 'utf8');
    console.log(` ${chickensData.length} gallinas guardadas en ${FILE_PATH}`);
  }

  // Cargar gallinas desde JSON
  loadChickensFromFile() {
    if (!fs.existsSync(FILE_PATH)) {
      console.log('No hay archivo chickens.json. Nada que cargar.');
      return;
    }

    const data = JSON.parse(fs.readFileSync(FILE_PATH, 'utf8'));
    this.coop.chickens = data.map(obj => Chicken.fromJSON(obj));
    console.log(` ${this.coop.getTotalChickens()} gallinas cargadas desde ${FILE_PATH}`);
  }

  showChickens() {
    console.log('=== Lista de Gallinas ===');
    this.coop.chickens.forEach((c, i) => {
      console.log(`${i + 1}. ${c.name} | Edad: ${c.age} | Color: ${c.color} | Muda: ${c.molting}`);
    });
  }
}

/* -------------------------------
   EJECUCIÓN PRINCIPAL DEL SIMULADOR
--------------------------------- */
const simulator = new ChickenFarmSimulator();

simulator.createChicken('Lola', 2, 'Blanca', false);
simulator.createChicken('Pepita', 1, 'Marrón', true);
simulator.createChicken('Clara', 3, 'Negra', false);

simulator.saveChickensToFile();
simulator.loadChickensFromFile();
simulator.showChickens();