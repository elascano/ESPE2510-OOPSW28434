import { FarmAnimal } from "./FarmAnimal.js";

export class Sheep extends FarmAnimal {
    constructor(lastShearing, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
    }

    cutWool(kilogramsOfWool) {
        return kilogramsOfWool + 1;
    }

    shear() {
        return "The sheep has been sheared";
    }

    toString() {
        return `{
  "lastShearing": "${this.lastShearing}",
  "farmAnimal": ${super.toString()}
}`;
    }
}
