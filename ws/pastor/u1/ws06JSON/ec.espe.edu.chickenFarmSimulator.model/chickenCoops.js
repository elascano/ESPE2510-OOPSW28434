const Chicken = require("./chicken");

class ChickenCoop{
    constructor(chickenCoopNumber){
        this.chickenCoopNumber = chickenCoopNumber;
        this.chickenCoops = [];
    }

    addChicken(chicken){
        if (chicken instanceof Chicken){
            this.chickenCoops.push(chicken);
        } 
    }

    showChickens(){
        console.log("---------------------------------------------------------------");
        console.log(`Free-range chickens in coop number  ${this.chickenCoopNumber}:`);
        console.log("---------------------------------------------------------------");
        if (!this.chickenCoops.length){
            console.log("The chicken coop is empty");
            return;
        }

        for (const chicken of this.chickenCoops){
            console.log(chicken.toString());
        }
    }
}

module.exports = ChickenCoop;