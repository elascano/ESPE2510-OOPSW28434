class FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        this._id = id;
        this._breed = breed;
        this._bornOn = bornOn;
        this._gender = gender;
        this._isAbleToReproduce = isAbleToReproduce;
        this._weight = weight;
        this._cage = cage;
    }

    getAgeInMonths() {
        return 0;
    }

    assignCage(cage) {
        this._cage = cage;
    }

    get id() { return this._id; }
    get breed() { return this._breed; }
    get bornOn() { return this._bornOn; }
    get gender() { return this._gender; }
    get isAbleToReproduce() { return this._isAbleToReproduce; }
    get weight() { return this._weight; }
    get cage() { return this._cage; }

    set id(id) { this._id = id; }
    set breed(breed) { this._breed = breed; }
    set bornOn(bornOn) { this._bornOn = bornOn; }
    set gender(gender) { this._gender = gender; }
    set isAbleToReproduce(isAbleToReproduce) { this._isAbleToReproduce = isAbleToReproduce; }
    set weight(weight) { this._weight = weight; }
    set cage(cage) { this._cage = cage; }

    toString() {
        return `FarmAnimal{id=${this._id}, breed='${this._breed}', ` +
               `bornOn=${this._bornOn}, gender='${this._gender}', ` +
               `isAbleToReproduce=${this._isAbleToReproduce}, ` +
               `weight=${this._weight}, cage=${this._cage}}`;
    }
}

module.exports = { FarmAnimal };