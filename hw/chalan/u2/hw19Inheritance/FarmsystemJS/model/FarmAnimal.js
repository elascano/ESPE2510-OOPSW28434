class FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
    }
    toString() { return `FarmAnimal{id=${this.id}, breed=${this.breed}, bornOn=${this.bornOn.toDateString()}}`; }
}
module.exports = FarmAnimal;