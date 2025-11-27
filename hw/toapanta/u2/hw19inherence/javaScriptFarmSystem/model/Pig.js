<<<<<<< HEAD
const FarmAnimal = require('./FarmAnimal');

class Pig extends FarmAnimal {
    constructor(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
    }

    toString() {
        return `Pig{, ${super.toString()}}`;
    }
}

=======
const FarmAnimal = require('./FarmAnimal');

class Pig extends FarmAnimal {
    constructor(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
    }

    toString() {
        return `Pig{, ${super.toString()}}`;
    }
}

>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
module.exports = Pig;