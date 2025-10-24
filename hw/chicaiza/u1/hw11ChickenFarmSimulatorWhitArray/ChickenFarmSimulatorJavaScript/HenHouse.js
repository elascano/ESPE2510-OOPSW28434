const Chicken = require('./Chicken');

class HenHouse {
    constructor(id, location) {
        this._id = id;
        this._location = location;
        this._chickens = []; // Lista (array) de gallinas
    }

    // Agregar una gallina al gallinero
    addChicken(chicken) {
        if (chicken instanceof Chicken) {
            this._chickens.push(chicken);
        } else {
            console.log("Only Chicken objects can be added.");
        }
    }

    // Mostrar todas las gallinas del gallinero
    showChickens() {
        console.log(`\n🐔 HenHouse ${this._id} - Location: ${this._location}`);
        if (this._chickens.length === 0) {
            console.log("No chickens in this henhouse.");
        } else {
            this._chickens.forEach((ch, i) => {
                console.log(`${i + 1}. ${ch.displayInfo()}`);
            });
        }
    }

    // Getters
    get id() { return this._id; }
    get location() { return this._location; }
    get chickens() { return this._chickens; }
}

module.exports = HenHouse;