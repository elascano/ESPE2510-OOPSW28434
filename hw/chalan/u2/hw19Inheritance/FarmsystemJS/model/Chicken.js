const FarmAnimal = require('./FarmAnimal');

class Chicken extends FarmAnimal {
    constructor(isMolting, laidEggs, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }
    toString() { return `Chicken{eggs=${this.laidEggs}, ${super.toString()}}`; }
}
module.exports = Chicken;