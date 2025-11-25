const FarmAnimal = require('./FarmAnimal');

class Cow extends FarmAnimal {
    constructor(isProducingMilk, litersADay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = litersADay;
    }

    milk() {
        this.litersADay++;
        return this.litersADay;
    }

    toString() {
        return `Cow{isProducingMilk=${this.isProducingMilk}, litersADay=${this.litersADay}, ${super.toString()}}`;
    }
}

module.exports = Cow;