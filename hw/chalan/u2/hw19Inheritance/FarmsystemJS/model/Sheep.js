const FarmAnimal = require('./FarmAnimal');

class Sheep extends FarmAnimal {
    constructor(lastShearing, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
    }
    toString() { return `Sheep{sheared=${this.lastShearing.toDateString()}, ${super.toString()}}`; }
}
module.exports = Sheep;