//npm init -y
//npm install express
export class RentalManager {
    static instance = null;

    constructor() {
        if (RentalManager.instance) return RentalManager.instance;
        this.monthlyRent = 40.0; 
        RentalManager.instance = this;
    }

    static getInstance() {
        if (!this.instance) this.instance = new RentalManager();
        return this.instance;
    }

    getMonthlyRent() {
        return this.monthlyRent;
    }

    updateMonthlyRent(newValue) {
        if (!isNaN(newValue) && newValue > 0) {
            this.monthlyRent = newValue;
            return true;
        }
        return false;
    }
}