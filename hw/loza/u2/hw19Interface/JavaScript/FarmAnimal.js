export class FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
    }

    getAgeInMonths() {
        return 0; // TODO
    }

    assignCage(cage) {
        this.cage = cage;
    }

    toString() {
        return `FarmAnimal{id=${this.id}, breed=${this.breed}, bornOn=${this.bornOn}, gender=${this.gender}, isAbleToReproduce=${this.isAbleToReproduce}, weight=${this.weight}, cage=${this.cage}}`;
    }
}
