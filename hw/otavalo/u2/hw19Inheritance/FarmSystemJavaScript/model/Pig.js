const FarmAnimal = require('./FarmAnimal');

class Pig extends FarmAnimal {
    constructor(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
    }

    toString() {
        return `Pig{, ${super.toString()}}`;
    }
}

module.exports = Pig;