class Egg {
    constructor(size) {
        this._size = size;  // 'S', 'M' o 'L'
    }

    toString() {
        return `Egg{size = ${this._size}}`;
    }

    get size() {
        return this._size;
    }

    set size(size) {
        this._size = size;
    }
}

module.exports = Egg;