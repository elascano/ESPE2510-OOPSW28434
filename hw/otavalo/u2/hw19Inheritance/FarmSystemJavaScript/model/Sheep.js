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

module.exports = Sheep;