const FarmAnimal = require('./FarmAnimal');

class Sheep extends FarmAnimal {
    constructor(lastSheering, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastSheering = lastSheering;
    }

    cutWool() {
        // Implementation for cutting wool
    }

    shear() {
        // Implementation for shearing
    }

    toString() {
        return `Sheep{lastSheering=${this.lastSheering}, ${super.toString()}}`;
    }
}

module.exports = Sheep;