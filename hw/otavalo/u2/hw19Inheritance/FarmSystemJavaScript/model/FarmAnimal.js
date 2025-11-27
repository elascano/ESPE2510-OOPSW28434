class FarmAnimal {
    constructor(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gendeR = gendeR;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
        this.location = location;
    }

    toString() {
        return `FarmAnimal{id=${this.id}, breed=${this.breed}, bornOn=${this.bornOn}, gendeR=${this.gendeR}, isAbleToReproduce=${this.isAbleToReproduce}, weight=${this.weight}, cage=${this.cage}, location=${this.location}}`;
    }

    getId() {
        return this.id;
    }

    setId(id) {
        this.id = id;
    }

    getBreed() {
        return this.breed;
    }

    setBreed(breed) {
        this.breed = breed;
    }

    getBornOn() {
        return this.bornOn;
    }

    setBornOn(bornOn) {
        this.bornOn = bornOn;
    }

    getGendeR() {
        return this.gendeR;
    }

    setGendeR(gendeR) {
        this.gendeR = gendeR;
    }

    isIsAbleToReproduce() {
        return this.isAbleToReproduce;
    }

    setIsAbleToReproduce(isAbleToReproduce) {
        this.isAbleToReproduce = isAbleToReproduce;
    }

    getWeight() {
        return this.weight;
    }

    setWeight(weight) {
        this.weight = weight;
    }

    getCage() {
        return this.cage;
    }

    setCage(cage) {
        this.cage = cage;
    }

    getLocation() {
        return this.location;
    }

    setLocation(location) {
        this.location = location;
    }
}

module.exports = FarmAnimal;