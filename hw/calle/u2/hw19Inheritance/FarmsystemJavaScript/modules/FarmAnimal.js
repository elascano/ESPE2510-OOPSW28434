export class FarmAnimal {
    #id;
    #breed;
    #bornOn;
    #gender;
    #isAbleToReproduce;
    #weight;
    #cage;

    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        this.#id = id;
        this.#breed = breed;
        this.#bornOn = bornOn;
        this.#gender = gender;
        this.#isAbleToReproduce = isAbleToReproduce;
        this.#weight = weight;
        this.#cage = cage;

        if (new.target === FarmAnimal) {
            throw new TypeError("Cannot instantiate abstract class FarmAnimal directly.");
        }
    }

    getAgeInMonths() {
        const bornDate = this.#bornOn;
        const currentDate = new Date();

        const yearDiff = currentDate.getFullYear() - bornDate.getFullYear();
        const monthDiff = currentDate.getMonth() - bornDate.getMonth();
        
        let totalMonths = yearDiff * 12 + monthDiff;
        
        if (currentDate.getDate() < bornDate.getDate()) {
            totalMonths--;
        }

        return Math.max(0, totalMonths);
    }

    assignCage(cage) {
        this.setCage(cage);
    }

    toString() {
        return `FarmAnimal{id=${this.#id}, breed=${this.#breed}, bornOn=${this.#bornOn.toDateString()}, gender=${this.#gender}, isAbleToReproduce=${this.#isAbleToReproduce}, weight=${this.#weight}, cage=${this.#cage.toString()}, ageInMonths=${this.getAgeInMonths()}}`;
    }

    getId() {
        return this.#id;
    }
    setId(id) {
        this.#id = id;
    }
    getBreed() {
        return this.#breed;
    }
    setBreed(breed) {
        this.#breed = breed;
    }
    getBornOn() {
        return this.#bornOn;
    }
    setBornOn(bornOn) {
        this.#bornOn = bornOn;
    }
    getGender() {
        return this.#gender;
    }
    setGender(gender) {
        this.#gender = gender;
    }
    isIsAbleToReproduce() {
        return this.#isAbleToReproduce;
    }
    setIsAbleToReproduce(isAbleToReproduce) {
        this.#isAbleToReproduce = isAbleToReproduce;
    }
    getWeight() {
        return this.#weight;
    }
    setWeight(weight) {
        this.#weight = weight;
    }
    getCage() {
        return this.#cage;
    }
    setCage(cage) {
        this.#cage = cage;
    }
}