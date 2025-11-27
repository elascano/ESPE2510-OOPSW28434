<<<<<<< HEAD
const FarmAnimal = require('./FarmAnimal');

class Sheep extends FarmAnimal {
    constructor(lastShearing, id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
        this.lastShearing = lastShearing;
    }

    toString() {
        return `Sheep{lastShearing=${this.lastShearing}, ${super.toString()}}`;
    }

    cutWhool(kilogramsOfWool) {
        kilogramsOfWool++;
    }

    shear(shearedSheep) {
        shearedSheep = "The sheep has been sheared";
    }
}

=======
const FarmAnimal = require('./FarmAnimal');

class Sheep extends FarmAnimal {
    constructor(lastShearing, id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
        this.lastShearing = lastShearing;
    }

    toString() {
        return `Sheep{lastShearing=${this.lastShearing}, ${super.toString()}}`;
    }

    cutWhool(kilogramsOfWool) {
        kilogramsOfWool++;
    }

    shear(shearedSheep) {
        shearedSheep = "The sheep has been sheared";
    }
}

>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
module.exports = Sheep;