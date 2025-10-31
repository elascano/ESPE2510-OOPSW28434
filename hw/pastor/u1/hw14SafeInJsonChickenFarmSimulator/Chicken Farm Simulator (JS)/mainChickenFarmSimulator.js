const ChickenFarmController = require('../Chicken Farm Simulator (JS)/ec.espe.edu.chickenFarmSimulator.controllers/chickenFarmController');

function main() {
    const app = new ChickenFarmController();
    console.log("========================================");
    console.log("  Welcome to the Chicken Farm Simulator by Mathews Pastor! (Node.js Version)  ");
    console.log("========================================");
    app.start();
}

main();