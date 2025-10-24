import { FarmController } from './controller/farmController.js';

console.log("=== 🐔 Chicken Farm Simulator ===\n");

const farmController = new FarmController();
farmController.setupFarm();
farmController.showFarm();
