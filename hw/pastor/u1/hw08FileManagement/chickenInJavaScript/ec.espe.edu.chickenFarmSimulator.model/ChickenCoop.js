const Chicken = require("./Chicken");

class ChickenCoop{
    constructor(chickenCoopNumber){
        this._chickenCoopNumber = chickenCoopNumber;
        this._chickenCoops = [];    
    }

    addChicken(newChicken){
        if (newChicken instanceof Chicken){
            this._chickenCoops.push(newChicken);
        } 
    }

    showChickens(){
        console.log("---------------------------------------------------------------");
        console.log(`Free-range chickens in coop number  ${this._chickenCoopNumber}:`);
        console.log("---------------------------------------------------------------");
        if (!this._chickenCoops.length){
            console.log("The chicken coop is empty");
            return;
        }

        for (const chicken of this._chickenCoops){
            console.log(chicken.toString());
        }
    }

    getChickenCoopNumber(){
        return this._chickenCoopNumber;
    }

    setChickenCoopNumber(chickenCoopNumber){
        this._chickenCoopNumber = chickenCoopNumber;
    }

    getChickenCoop(){
        return this._chickenCoops;
    }

    setChickenCoop(chickenCoops){
        this._chickenCoops = chickenCoops;
    }
}

module.exports = ChickenCoop;