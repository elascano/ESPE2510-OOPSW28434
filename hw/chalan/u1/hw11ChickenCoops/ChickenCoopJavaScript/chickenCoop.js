import { Chicken } from "./Chicken.js";

class ChickenCoop {
    constructor(coopId) {
        this.id = coopId;
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
            this.chickens.forEach(chicken => console.log(chicken.toString()));
        }
    }

    toList() {
        return this.chickens.map(chicken => [this.id, ...chicken.toList()]);
    }

    toString() {
        return `ChickenCoop(id=${this.id}, chickens=${this.chickens.length})`;
    }
}

export { ChickenCoop };

