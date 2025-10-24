class Egg {
    constructor(size) {
        this.size = size;
    }

    toString() {
        return `Egg{size=${this.size}}`;
    }

    getSize() {
        return this.size;
    }

    setSize(size) {
        this.size = size;
    }
}