const ChickenCoop = require("./chickenCoops.js");

class ChickenFarmer {
    constructor(id, name) {
        this._id = id;
        this._name = name;
        this._coops = [];
        this._nextCoopId = 1;
    }

    addCoop(chickenCapacity) {
        const newCoop = new ChickenCoop(this._nextCoopId++, chickenCapacity);
        this._coops.push(newCoop);
        return newCoop;
    }

    removeCoop(coopId) {
        const initialLength = this._coops.length;
        this._coops = this._coops.filter(coop => coop.getCoopCoopNumber() !== coopId);
        return this._coops.length < initialLength;
    }

    updateCoop(coopId, coopUpdates) {
        const coop = this.findCoop(coopId);
        if (coop) {
            if (coopUpdates.capacity !== undefined) {
                coop.setCapacity(coopUpdates.capacity);
            }
            return true;
        }
        return false;
    }

    findCoop(coopId) {
        return this.getCoops().find(c => c.getCoopCoopNumber() === coopId);
    }

    toString() {
        return `Farmer: ${this.getName()} (ID: ${this.getId()}), Chicken coops: ${this.getCoops().length}`;
    }

    getId() { return this._id; }
    getName() { return this._name; }
    getCoops() { return this._coops; }
    getNextCoopId() { return this._nextCoopId; }

    setId(id) { this._id = id; }
    setName(name) { this._name = name; }
    setNextCoopId(nextCoopId) { this._nextCoopId = nextCoopId; }
}

module.exports = ChickenFarmer;