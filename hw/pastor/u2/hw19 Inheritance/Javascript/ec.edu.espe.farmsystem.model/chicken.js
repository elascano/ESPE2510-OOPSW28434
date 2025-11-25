const FarmAnimal = require('./farmAnimal');

class Chicken extends FarmAnimal {
    constructor(isMolting, laidAnEggs, id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        this.isMolting = isMolting;
        this.laidAnEggs = laidAnEggs;
    }

    layAnEgg() {
        this.laidAnEggs++;
    }

    toString() {
        const moltingStr = this.isMolting ? "Yes" : "No";
        return super.toString() + `
 > Is Molting    : ${moltingStr}
 > Eggs Laid     : ${this.laidAnEggs}
========================================`;
    }
}

module.exports = Chicken;