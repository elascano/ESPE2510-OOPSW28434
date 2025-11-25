const { FarmAnimal } = require('./FarmAnimal.js');

class Cow extends FarmAnimal {
    constructor(isProducingMilk, litersPerDay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this._isProducingMilk = isProducingMilk;
        this._litersPerDay = litersPerDay;
    }

    milk(milkProduced) {
        this._litersPerDay += milkProduced;
        return this._litersPerDay;
    }

    get isProducingMilk() { return this._isProducingMilk; }
    get litersPerDay() { return this._litersPerDay; }

    set isProducingMilk(isProducingMilk) { this._isProducingMilk = isProducingMilk; }
    set litersPerDay(litersPerDay) { this._litersPerDay = litersPerDay; }

    toString() {
        return `Cow{isProducingMilk=${this._isProducingMilk}, ` +
               `litersPerDay=${this._litersPerDay}, ${super.toString()}}`;
    }
}

module.exports = { Cow };