import { Resident } from '../model/Resident.js';

export class RentController {
    getTotalToPay(id, name, months) {
        const resident = new Resident(id, name, months);
        return resident.calculateTotalRent();
    }
}