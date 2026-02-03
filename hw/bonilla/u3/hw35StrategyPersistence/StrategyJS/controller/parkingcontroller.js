const Parking = require('../model/parking');

class ParkingController {
    constructor(strategy = null) {
        this.strategy = strategy;
        this.RATE_PER_HOUR = 2.5;
    }

    setStrategy(strategy) {
        this.strategy = strategy;
    }

    calculateFee(entry, exit) {
        let hours = Math.ceil((exit - entry) / (1000 * 60 * 60));
        if (hours === 0) hours = 1;
        return hours * this.RATE_PER_HOUR;
    }

    registerEntry(id, plate, vehicleType, entryTime) {
        if (!this.strategy || !id || !plate || !vehicleType || !entryTime) return false;
        const parking = new Parking(id, plate, vehicleType, entryTime, null, 0);
        return this.strategy.create(parking);
    }

    registerExit(id) {
        if (!this.strategy || !id) return false;
        const parking = this.strategy.find(id);
        if (parking) {
            const exitTime = new Date();
            const fee = this.calculateFee(parking.entryTime, exitTime);
            parking.exitTime = exitTime;
            parking.fee = fee;
            return this.strategy.update(id, parking);
        }
        return false;
    }

    getAllParkings() {
        if (!this.strategy) return [];
        return this.strategy.read();
    }

    findById(id) {
        if (!this.strategy || !id) return null;
        return this.strategy.find(id);
    }

    delete(id) {
        if (!this.strategy || !id) return false;
        return this.strategy.delete(id);
    }

    getNextId() {
        const parkings = this.getAllParkings();
        if (parkings.length === 0) return '1';
        const maxId = Math.max(...parkings.map(p => parseInt(p.id)));
        return String(maxId + 1);
    }
}

module.exports = ParkingController;