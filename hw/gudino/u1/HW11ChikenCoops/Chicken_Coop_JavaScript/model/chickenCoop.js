class ChickenCoop {
    constructor(id) {
        this.id = id;
        this.chickens = [];
    }

    add(chicken) {
        this.chickens.push(chicken);
    }

    remove(chickenId) {
        this.chickens = this.chickens.filter(c => c.id !== chickenId);
    }

    showAllChickens() {
        if (this.chickens.length === 0) {
            console.log("No chickens in this coop.");
        } else {
            this.chickens.forEach(c => console.log(c.toString()));
        }
    }

    toArray() {
        return this.chickens.map(c => [this.id, ...c.toArray()]);
    }
}

module.exports = ChickenCoop;
