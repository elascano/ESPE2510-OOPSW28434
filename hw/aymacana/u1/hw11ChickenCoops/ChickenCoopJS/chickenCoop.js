import { Chicken } from './chicken.js';

export class ChickenCoop {
    constructor(name = "Unnamed Coop") {
        this._chickens = [];
        this._name = name;
    }

    addChicken(chicken) {
        this._chickens.push(chicken);
        console.log(`Chicken '${chicken.name}' added to the coop '${this._name}'`);
    }

    removeChicken(chickenId) {
        for (let i = 0; i < this._chickens.length; i++) {
            if (this._chickens[i].id === chickenId) {
                const chicken = this._chickens[i];
                this._chickens.splice(i, 1);
                console.log(`Chicken '${chicken.name}' removed from the coop '${this._name}'`);
                return;
            }
        }
        console.log(`Chicken with ID ${chickenId} not found in coop '${this._name}'`);
    }

    getChicken(chickenId) {
        for (const chicken of this._chickens) {
            if (chicken.id === chickenId) {
                return chicken;
            }
        }
        return null;
    }

    getAllChickens() {
        return [...this._chickens];
    }

    countChickens() {
        return this._chickens.length;
    }

    isEmpty() {
        return this._chickens.length === 0;
    }

    displayChickens() {
        if (this.isEmpty()) {
            console.log(`Coop '${this._name}' is empty.`);
        } else {
            console.log(`\n--- Chickens in ${this._name} (${this.countChickens()} total) ---`);
            for (const chicken of this._chickens) {
                console.log(chicken.toString());
            }
        }
    }

    get name() {
        return this._name;
    }

    toString() {
        return `${this._name} with ${this.countChickens()} chickens`;
    }

    get length() {
        return this.countChickens();
    }
}