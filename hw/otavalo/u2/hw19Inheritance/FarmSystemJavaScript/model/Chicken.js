const FarmAnimal = require('./FarmAnimal');

class Chicken extends FarmAnimal {
    constructor(isMolting, laidEggs, id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }

    toString() {
        return `Chicken{isMolting=${this.isMolting}, laidEggs=${this.laidEggs}, ${super.toString()}}`;
    }

    layAnEgg() {
        this.laidEggs++;
    }
}

module.exports = Chicken;