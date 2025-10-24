class Poop {
    constructor(amount) {
        this.amount = amount;
    }

    toString() {
        return `aPoop(amount=${this.amount})`;
    }
}

function makeChickenPoop(chicken, amount) {
    const poop = new Poop(amount);
    console.log(`chicken ${chicken.name} is pooping ${poop}`);
}

module.exports = { makeChickenPoop };