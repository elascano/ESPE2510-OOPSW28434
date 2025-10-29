export class Egg {
    constructor(size) {
        this._size = size;
    }

    getSize() {
        return this._size;
    }
    setSize(size) {
        this._size = size;
    }

    toString() {
        return `Egg{size=${this._size}}`;
    }
}