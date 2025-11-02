/**
 * Food.js
 * 
 */
class Food {
    /**
     * @param {number} amount - 
     */
    constructor(amount) {
        this.amount = amount;
    }

    getAmount() {
        return this.amount;
    }
}

module.exports = Food;