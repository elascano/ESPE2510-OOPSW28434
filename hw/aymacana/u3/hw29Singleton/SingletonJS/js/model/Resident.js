// js/model/Resident.js
class Resident {
    constructor(id, name, months) {
        this.id = id;
        this.name = name;
        this.months = months;
    }
    
    calculateTotalRent() {
        const rentalManager = RentalManager.getInstance();
        const monthlyRent = rentalManager.getMonthlyRent();
        return monthlyRent * this.months;
    }
    
    getId() {
        return this.id;
    }
    
    getName() {
        return this.name;
    }
    
    getMonths() {
        return this.months;
    }
    
    toString() {
        return `Residente(ID=${this.id}, Nombre="${this.name}", Meses=${this.months})`;
    }
}

window.Resident = Resident;