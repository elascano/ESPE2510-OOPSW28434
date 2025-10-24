const Chicken = require("./Chicken");

class ChickenCoop {
    constructor(id, name) {
        this._id = id;
        this._name = name;
        this._chickens = []; 
    }

    get_id() {
        return this._id;
    }

    get_name() {
        return this._name;
    }

    get_chickens() {
        return this._chickens;
    }

    set_id(id) {
        this._id = id;
    }

    set_name(name) {
        this._name = name;
    }

    addChicken(chicken) {
        this._chickens.push(chicken);
    }

    removeChicken(chickenId) {
        this._chickens = this._chickens.filter(chicken => chicken.get_id() !== chickenId);
    }

    getChickenCount() {
        return this._chickens.length;
    }

    toString() {
        let coopInfo = `Chicken Coop ID: ${this._id}\n` +
                      `Name: ${this._name}\n` +
                      `Number of Chickens: ${this.getChickenCount()}\n`;
        
        if (this._chickens.length > 0) {
            coopInfo += "Chickens in this coop:\n";
            this._chickens.forEach(chicken => {
                coopInfo += `- ${chicken.get_name()} (ID: ${chicken.get_id()})\n`;
            });
        } else {
            coopInfo += "No chickens in this coop.\n";
        }
        
        return coopInfo;
    }

    displayAllChickens() {
        console.log(`\n=== Detailed info for ${this._name} ===`);
        this._chickens.forEach(chicken => {
            console.log(chicken.toString());
            console.log("---");
        });
    }
}

module.exports = ChickenCoop;
