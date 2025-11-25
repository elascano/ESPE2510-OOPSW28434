const FarmAnimal = require('./farmAnimal');

class Sheep extends FarmAnimal {
    constructor(lastSheering, id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        this.lastSheering = lastSheering; // Date object
    }

    shear() {
        this.lastSheering = new Date();
    }

    toString() {
        const shearingStr = this.lastSheering.toISOString().split('T')[0];
        return super.toString() + `
 > Last Sheering : ${shearingStr}
========================================`;
    }
}

module.exports = Sheep;