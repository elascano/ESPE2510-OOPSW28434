const { FarmAnimal } = require('./FarmAnimal.js');

class Chicken extends FarmAnimal {
    constructor(isMolting, laidEggs, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this._isMolting = isMolting;
        this._laidEggs = laidEggs;
    }

    layAnEgg() {
        this._laidEggs += 1;
    }

    get isMolting() { return this._isMolting; }
    get laidEggs() { return this._laidEggs; }

    set isMolting(isMolting) { this._isMolting = isMolting; }
    set laidEggs(laidEggs) { this._laidEggs = laidEggs; }

    toString() {
        return `Chicken{isMolting=${this._isMolting}, laidEggs=${this._laidEggs}, ${super.toString()}}`;
    }
}

module.exports = { Chicken };