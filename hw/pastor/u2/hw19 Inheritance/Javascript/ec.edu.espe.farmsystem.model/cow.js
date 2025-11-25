const FarmAnimal = require('./farmAnimal');

class Cow extends FarmAnimal {
    constructor(isProducingMilk, littersADay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        this.isProducingMilk = isProducingMilk;
        this.littersADay = littersADay;
    }

    milk() {
        return this.littersADay;
    }

    toString() {
        const milkingStr = this.isProducingMilk ? "Yes" : "No";
        return super.toString() + `
 > Is Milking    : ${milkingStr}
 > Milk/Day      : ${this.littersADay.toFixed(1)} Liters
========================================`;
    }
}

module.exports = Cow;