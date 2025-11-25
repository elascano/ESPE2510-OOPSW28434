const { FarmAnimal } = require('./FarmAnimal.js');

class Pig extends FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }

    toString() {
        return `Pig{${super.toString()}}`;
    }
}

module.exports = { Pig };