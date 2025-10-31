class Egg{
    constructor(){
        const sizes = ['S','M','L'];
        const randomSize = Math.floor(Math.random() * sizes.length);
        this._size = sizes[randomSize];
    }

    toString(){ return `Egg size => ${this._size}`; }

    setSize(size){ this._size = size; }

    getSize(){ return this._size; }
}

module.exports = Egg;