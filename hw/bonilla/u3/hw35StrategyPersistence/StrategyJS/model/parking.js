export default class Parking {
    constructor(id, plate, vehicleType, entryTime, exitTime, fee) {
        this.id = id;
        this.plate = plate;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime ? new Date(entryTime) : null;
        this.exitTime = exitTime ? new Date(exitTime) : null;
        this.fee = fee || 0;
    }

    toString() {
        return `${this.plate} (${this.vehicleType})`;
    }
}