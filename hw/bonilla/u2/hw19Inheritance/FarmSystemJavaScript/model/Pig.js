const FarmAnimal = require('./FarmAnimal');

class Pig extends FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }

    toString() {
        return `{
  "farmAnimal": ${super.toString()}
}`;
    }
}

module.exports = Pig;
