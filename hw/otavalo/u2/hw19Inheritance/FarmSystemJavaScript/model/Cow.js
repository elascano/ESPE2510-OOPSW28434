const FarmAnimal = require('./FarmAnimal');

class Cow extends FarmAnimal {
    constructor(isProducingMilk, litersADay, id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = litersADay;
    }

    toString() {
        return `Cow{isProducingMilk=${this.isProducingMilk}, litersADay=${this.litersADay}, ${super.toString()}}`;
    }

    isIsProducingMilk() {
        return this.isProducingMilk;
    }

    setIsProducingMilk(isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    getLitersADay() {
        return this.litersADay;
    }

    setLitersADay(litersADay) {
        this.litersADay = litersADay;
    }
}

module.exports = Cow;