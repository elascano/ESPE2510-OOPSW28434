const Chicken = require('../model/chicken');

class View {
    constructor() {}

    showChicken(chicken) {
        console.log(chicken.displayInfo());
    }

    showMessage(message) {
        console.log(message);
    }
}

// ------------------------
// Prueba de funcionamiento
// ------------------------
const view = new View();

const chicken1 = new Chicken('Clucky', 2, 'Brown');
const chicken2 = new Chicken('Feathers', 1, 'White');

view.showMessage('--- Initial Chickens ---');
view.showChicken(chicken1);
view.showChicken(chicken2);

// Actualización de datos
chicken1.age = 3;
chicken2.color = 'Black';

view.showMessage('--- Updated Chickens ---');
view.showChicken(chicken1);
view.showChicken(chicken2);

module.exports = View;