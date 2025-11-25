const FarmAnimal = require('./FarmAnimal');

class Chicken extends FarmAnimal {
    constructor(isMolting, laidEggs, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }

    layAnEgg() {
        this.laidEggs++;
        return this.laidEggs;
    }

    toString() {
        return `Chicken{isMolting=${this.isMolting}, laidEggs=${this.laidEggs}, ${super.toString()}}`;
    }
}

module.exports = Chicken;