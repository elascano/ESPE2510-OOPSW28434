const FarmAnimal = require('./FarmAnimal');

class Chicken extends FarmAnimal {
    constructor(isMolting, laidEgg, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEgg = laidEgg;
    }

    toString() {
        return `{
  "isMolting": ${this.isMolting},
  "laidEgg": ${this.laidEgg},
  "farmAnimal": ${super.toString()}
}`;
    }

    layAnEgg() {
        this.setLaidEgg(this.getLaidEgg() + 1);
    }

    isIsMolting() {
        return this.isMolting;
    }

    setIsMolting(isMolting) {
        this.isMolting = isMolting;
    }

    getLaidEgg() {
        return this.laidEgg;
    }

    setLaidEgg(laidEgg) {
        this.laidEgg = laidEgg;
    }
}

module.exports = Chicken;
