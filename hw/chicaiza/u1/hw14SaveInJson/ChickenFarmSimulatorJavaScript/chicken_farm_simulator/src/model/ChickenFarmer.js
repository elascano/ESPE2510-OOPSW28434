import { ChickenCoop } from './ChickenCoop.js';

export class ChickenFarmer {
    constructor(name) {
        this.name = name;
        this.coops = [];
        this._currentIteration = 0;
    }
    
    add(coop) {
        this.coops.push(coop);
    }
    
    remove(coopId) {
        this.coops = this.coops.filter(coop => coop.id !== coopId);
    }
    
    resetIteration() {
        this._currentIteration = 0;
    }
    
    next() {
        if (this._currentIteration < this.coops.length) {
            const coop = this.coops[this._currentIteration];
            this._currentIteration++;
            return coop;
        }
        return null;
    }
    
    toDict() {
        return {
            name: this.name,
            coops: this.coops.map(coop => coop.toDict())
        };
    }
    
    static fromDict(data) {
        const farmer = new ChickenFarmer(data.name);
        farmer.coops = data.coops.map(coopData => ChickenCoop.fromDict(coopData));
        return farmer;
    }
    
    getCoop(coopId) {
        return this.coops.find(coop => coop.id === coopId) || null;
    }
}