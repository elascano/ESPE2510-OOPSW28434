const farmController = require('../controller/farmController');

function mainView() {
    console.log("== Chicken Farm Simulator ==\n");

    const controller = new farmController();
    controller.setupFarm();
    controller.showFarm();
    controller.saveFarmData();

    return controller.coops;
}

module.exports = mainView;
