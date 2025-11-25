<<<<<<< HEAD
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

=======
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

>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
module.exports = Chicken;