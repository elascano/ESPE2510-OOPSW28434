class Egg {
    constructor() {
        const sizeAvailable = ['S', 'M', 'L'];
        const randomIndex = Math.floor(Math.random() * sizeAvailable.length);
        this.size = sizeAvailable[randomIndex];
    }

    toString() {
        return ` ${this.size}`;
    }

    getSize() {
        return this.size;
    }   

    setSize(size) {
        this.size = size;
    }
}

module.exports = Egg;