class chickenCoop {
    constructor(coopId) {
        this.coopId = coopId;
        this.chickens = [];
    }

    addChicken(chicken) {
        this.chickens.push(chicken);
    }

    toString() {
        if (this.chickens.length === 0) return `coop ${this.coopId}: Empty`;
        let result = `coop ${this.coopId}:\n`;
        this.chickens.forEach(chicken => {
            result += `  ${chicken.toString()}\n`;
        });
        return result;
    }
}

module.exports = chickenCoop;
