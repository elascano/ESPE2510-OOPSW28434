class ChickenCoop {
    static nextId = 1;

    constructor(name) {
        this.id = ChickenCoop.nextId++;
        this.name = name;
        this.chickens = []; 
    }

    addChicken(chicken) {
        this.chickens.push(chicken);
    }

    getChickens() {
        return this.chickens;
    }

    toString() {
        return `\nChickenCoop{ id: ${this.id}, name: ${this.name}, number of chickens: ${this.chickens.length} }`;
    }
}
module.exports = ChickenCoop;

