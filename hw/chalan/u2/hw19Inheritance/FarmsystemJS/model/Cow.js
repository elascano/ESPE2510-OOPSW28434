const FarmAnimal = require('./FarmAnimal');

class Cow extends FarmAnimal {
    constructor(isProducingMilk, litersPerDay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersPerDay = litersPerDay;
    }
    toString() { return `Cow{liters=${this.litersPerDay}, ${super.toString()}}`; }
}
module.exports = Cow;