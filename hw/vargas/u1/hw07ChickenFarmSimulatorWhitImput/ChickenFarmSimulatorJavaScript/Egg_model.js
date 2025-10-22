class Egg {

    constructor() {
        this._size = this.selectRandomSize();
    }
    selectRandomSize() {
        const sizesAvailable = ['S', 'M', 'L'];
        const randomIndex = Math.floor(Math.random() * sizesAvailable.length);
        return sizesAvailable[randomIndex];
    }

    get size() {
        return this._size;
    }

 
    set size(newSize) {
        this._size = newSize;
    }

    toString() {
        return `size --> \t${this._size}`;
    }
}

module.exports = {
    Egg
};