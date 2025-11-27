const FarmAnimal = require('./farmAnimal');

class Pig extends FarmAnimal {
    constructor(isReadyForSlaughter, id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        this.isReadyForSlaughter = isReadyForSlaughter;
    }

    toString() {
        const readyStr = this.isReadyForSlaughter ? "YES" : "No";
        return super.toString() + `
 > Ready to Kill : ${readyStr}
========================================`;
    }
}

module.exports = Pig;