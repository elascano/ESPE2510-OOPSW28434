import { RentalManager } from '../utils/RentalManager.js';

export class Resident {
    constructor(id, name, months) {
        this.id = id;
        this.name = name;
        this.months = months;
    }

    calculateTotalRent() {
        const rentBase = RentalManager.getInstance().getMonthlyRent();
        return rentBase * this.months;
    }
}