export class FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
        this.location = null;
    }

    assignCage(cage) {
        this.cage = cage;
    }

    getAgeInMonths() {
        const today = new Date();
        const diff = today - this.bornOn;
        return Math.floor(diff / (1000 * 60 * 60 * 24 * 30));
    }

    toString() {
        return `
ID: ${this.id}
Breed: ${this.breed}
Born On: ${this.bornOn.toDateString()}
Gender: ${this.gender}
Can Reproduce: ${this.isAbleToReproduce}
Weight: ${this.weight} kg
Cage: ${this.cage.description}`;
    }
}
