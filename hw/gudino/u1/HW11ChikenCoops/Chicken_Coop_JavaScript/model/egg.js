class Egg {
    constructor(size = "medium", color = "white") {
        this.size = size;
        this.color = color;
    }

    toString() {
        return `Egg(size='${this.size}', color='${this.color}')`;
    }
}

module.exports = Egg;
