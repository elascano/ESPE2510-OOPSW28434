class Poop{
    constructor(){
        this._amount = Math.floor(Math.random() * 51);
    }

    toString(){ return `Poop amount => ${this._amount}`; }

    setAmount(amount){ this._amount = amount; }
    getAmount(){ return this._amount; }
}

module.exports = Poop;