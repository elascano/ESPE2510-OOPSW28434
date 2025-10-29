class Poop{
    constructor(amount){
        this._size = amount;
    }

    toString(){
        return `Poop amount => ${this._amount}`;
    }

    setAmount(amount){
        this._amount = amount;
    }

    getAmount(){
        return this._amount;
    }
}

    module.export = Poop;