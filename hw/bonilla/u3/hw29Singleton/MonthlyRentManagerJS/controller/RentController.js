import Resident from "../model/Resident.js";

class RentController {
    getTotalToPay(id, name, months) {
        const resident = new Resident(id, name, months);
        return resident.calculateTotalRent();
    }
}

export default RentController;