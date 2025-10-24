class Egg {
    constructor(size) {
        this.size = size;
    }

    toString() {
        return `a ${this.size} size egg`;
    }
}

function layEgg(chicken, size) {
    const egg = new Egg(size);
    console.log(`chicken ${chicken.name} is laying ${egg}`);
}

module.exports = { layEgg };