const { FarmAnimal } = require('./FarmAnimal.js');

class Sheep extends FarmAnimal {
    constructor(lastShearing, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this._lastShearing = lastShearing;
    }

    cutWool() {
        console.log("Cutting wool...");
    }

    shear() {
        console.log("Sheep sheared successfully");
    }

    get lastShearing() { return this._lastShearing; }

    set lastShearing(lastShearing) { this._lastShearing = lastShearing; }

    toString() {
        return `Sheep{lastShearing=${this._lastShearing}, ${super.toString()}}`;
    }
}

module.exports = { Sheep };