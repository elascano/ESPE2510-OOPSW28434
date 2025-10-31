import { FarmController } from '../ChickenFarmSimulatorController/chickenFarmController.js';
async function main() {
    console.log("Iniciando la aplicación del simulador de granja...");
    
    const controller = new FarmController('chickensCoops.json'); 

    await controller.mainMenu(); 
    
}

main();