class ChickenCoop {
    static counter = 1;

    constructor(name) {
        this.identifier = ChickenCoop.counter++;
        this.coopName = name;
        this.birds = [];
    }

    addChicken(chicken) {
        this.birds.push(chicken);
    }

    getChickens() {
        return this.birds;
    }

    toString() {
        return \nChickenCoop { id => ${this.identifier}, name => ${this.coopName}, number of chickens => ${this.birds.length} };
    }
}

module.exports = ChickenCoop;
