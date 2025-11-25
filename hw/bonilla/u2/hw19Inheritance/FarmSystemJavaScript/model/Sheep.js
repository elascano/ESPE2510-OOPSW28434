const FarmAnimal = require('./FarmAnimal');

class Sheep extends FarmAnimal {
    constructor(lastShearing, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
    }

    toString() {
        return `{
  "lastShearing": "${this.lastShearing}",
  "farmAnimal": ${super.toString()}
}`;
    }

    getLastShearing() {
        return this.lastShearing;
    }

    setLastShearing(lastShearing) {
        this.lastShearing = lastShearing;
    }

    cutWhool(kilogramsOfWool) {
        kilogramsOfWool++;
    }

    shear(shearedSheep) {
        shearedSheep = "The sheep has been sheared";
    }
}

module.exports = Sheep;
