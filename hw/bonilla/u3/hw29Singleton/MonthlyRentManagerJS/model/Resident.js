import RentalManager from "../utils/RentalManager.js";

class Resident {
    constructor(id, name, months) {
        this.id = id;
        this.name = name;
        this.months = months;
    }

    calculateTotalRent() {
        const monthlyRent = RentalManager.getInstance().getMonthlyRent();
        return monthlyRent * this.months;
    }
}

export default Resident;