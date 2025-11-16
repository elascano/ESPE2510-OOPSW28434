class Egg {
    constructor(size) {
        this.size = size;
    }
    
    toString() {
        return `an ${this.size}-size egg`;
    }
}

module.exports = Egg;