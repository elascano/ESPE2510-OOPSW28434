import fs from "fs";
import path from "path";

class RentalManager {
    static instance;

    constructor() {
        this.filePath = path.resolve("data/rent.txt");
        this.loadRent();
    }

    static getInstance() {
        if (!RentalManager.instance) {
            RentalManager.instance = new RentalManager();
        }
        return RentalManager.instance;
    }

    loadRent() {
        try {
            const data = fs.readFileSync(this.filePath, "utf8");
            this.monthlyRent = parseFloat(data);
        } catch {
            this.monthlyRent = 20;
            this.saveRent();
        }
    }

    saveRent() {
        fs.writeFileSync(this.filePath, this.monthlyRent.toString());
    }

    getMonthlyRent() {
        return this.monthlyRent;
    }

    updateMonthlyRent(newRent) {
        this.monthlyRent = newRent;
        this.saveRent();
    }
}

export default RentalManager;