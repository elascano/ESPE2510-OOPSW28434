import { Chicken } from './Chicken.js';

export class ChickenCoop {
    constructor(id) {
        this.id = id;
        this.chickens = [];
        this._currentIteration = 0;
    }
    
    add(chicken) {
        this.chickens.push(chicken);
    }
    
    remove(chickenId) {
        this.chickens = this.chickens.filter(chicken => chicken.id !== chickenId);
    }
    
    resetIteration() {
        this._currentIteration = 0;
    }
    
    next() {
        if (this._currentIteration < this.chickens.length) {
            const chicken = this.chickens[this._currentIteration];
            this._currentIteration++;
            return chicken;
        }
        return null;
    }
    
    toDict() {
        return {
            id: this.id,
            chickens: this.chickens.map(chicken => chicken.toDict())
        };
    }
    
    static fromDict(data) {
        const coop = new ChickenCoop(data.id);
        coop.chickens = data.chickens.map(chickenData => Chicken.fromDict(chickenData));
        return coop;
    }
    
    getChicken(chickenId) {
        return this.chickens.find(chicken => chicken.id === chickenId) || null;
    }
    
    updateChicken(chickenId, name, color, age, isMolting) {
        const chicken = this.getChicken(chickenId);
        if (chicken) {
            chicken.name = name;
            chicken.color = color;
            chicken.age = age;
            chicken.isMolting = isMolting;
            return true;
        }
        return false;
    }
}