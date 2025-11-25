const FarmAnimal = require('./FarmAnimal');

class Cow extends FarmAnimal {
    constructor(isProducingMilk, litersADay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = litersADay;
    }

    toString() {
        return `{
  "isProducingMilk": ${this.isProducingMilk},
  "litersADay": ${this.litersADay},
  "farmAnimal": ${super.toString()}
}`;
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
