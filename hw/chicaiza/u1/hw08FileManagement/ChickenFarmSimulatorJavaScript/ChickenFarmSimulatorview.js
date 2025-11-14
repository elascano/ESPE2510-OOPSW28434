class View {
    constructor() {}

    showChicken(chicken) {
        console.log(chicken.displayInfo());
    }

    showMessage(message) {
        console.log(message);
    }
}

module.exports = View;
