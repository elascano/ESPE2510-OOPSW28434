import { FileUtils } from "../utils/FileUtils.js";

class Discount {

    static instance;

    constructor() {
        if (Discount.instance) {
            return Discount.instance;
        }
        this.path = "./discount.json";
        this.load();
        Discount.instance = this;
    }

    load() {
        const json = FileUtils.readJSON(this.path);
        this.percentage = json.percentage;
    }

    update(newPercentage) {
        this.percentage = newPercentage;
        FileUtils.writeJSON(this.path, { percentage: newPercentage });
    }

    apply(amount) {
        return amount - (amount * this.percentage / 100);
    }

    getPercentage() {
        return this.percentage;
    }
}

export default Discount;
