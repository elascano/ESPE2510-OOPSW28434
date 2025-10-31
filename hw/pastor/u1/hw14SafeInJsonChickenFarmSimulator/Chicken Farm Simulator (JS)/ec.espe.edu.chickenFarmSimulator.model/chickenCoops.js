const Chicken = require("./chicken");

class ChickenCoop{
    constructor(chickenCoopNumber, chickenCapacity = 100){
        this._chickenCoopNumber = chickenCoopNumber;
        this._chickens = [];
        this._chickenCapacity = chickenCapacity;
        this._totalEggs = 0;
    }

    addChicken(chicken){
        if (chicken instanceof Chicken && this._chickens.length < this._chickenCapacity) {
            this._chickens.push(chicken);
            return true;
        } 
        return false;
    }

    removeChicken(chickenId){
        const initialLength = this._chickens.length;
        this._chickens = this._chickens.filter(chicken => chicken.getId() !== chickenId);
        return this._chickens.length < initialLength;
    }

    updateChicken(chickenId, chickenUpdates){
        const chicken = this.findChicken(chickenId);
        if(chicken){
            if(chickenUpdates.name !== undefined) chicken.setName(chickenUpdates.name);
            if(chickenUpdates.color !== undefined) chicken.setColor(chickenUpdates.color);
            if(chickenUpdates.age !== undefined) chicken.setAge(chickenUpdates.age);
            if(chickenUpdates.isMolting !== undefined) chicken.setIsMolting(chickenUpdates.isMolting);
            return true;
        }
        return false;
    }

    findChicken(chickenId){
        return this._chickens.find(chicken => chicken.getId() === chickenId);
    }

    simulateCoopDay(){
            let totalEggsLaid = 0;
            console.log(`\n--- Simulate ChickenCoop Day - COOP ID ${this.getCoopCoopNumber()} ---`);
            this._chickens.forEach(chicken => {
                const eggsLaidByChicken = chicken.doStuff();
                totalEggsLaid += eggsLaidByChicken;
            });
            
            this._totalEggs += totalEggsLaid;
            console.log(`\n Today: A total of ${totalEggsLaid} eggs were laid in the Chicken Coop ${this.getCoopCoopNumber()}.`);
            
            return totalEggsLaid;
        }
    
        toString(){
            return `Chicken coop ID: ${this._chickenCoopNumber}, Chickens: ${this._chickens.length}/${this._chickenCapacity}, Total Eggs: ${this._totalEggs}`;
        }

        getCoopCoopNumber(){ return this._chickenCoopNumber; }
        getCapacity() { return this._chickenCapacity; }
        getChickens() { return this._chickens; }
        getTotalEggs() { return this._totalEggs; }

        setCapacity(capacity){ this._chickenCapacity = capacity; }

        setTotalEggs(eggs){ this._totalEggs = eggs; }
}

module.exports = ChickenCoop;