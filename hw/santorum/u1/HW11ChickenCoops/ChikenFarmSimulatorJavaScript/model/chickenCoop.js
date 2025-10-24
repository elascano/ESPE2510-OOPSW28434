import { Chicken } from './chicken.js';

export class ChickenCoop {
    constructor(coopId) {
        this.coopId = coopId;
        this.chickens = []; // array como ArrayList
    }

    addChicken(chicken) {
        this.chickens.push(chicken);
    }

    toString() {
        if (this.chickens.length === 0) return `Coop ${this.coopId}: Empty`;
        const chickensStr = this.chickens.map(c => c.toString()).join('\n  ');
        return `Coop ${this.coopId}:\n  ${chickensStr}`;
    }
}
