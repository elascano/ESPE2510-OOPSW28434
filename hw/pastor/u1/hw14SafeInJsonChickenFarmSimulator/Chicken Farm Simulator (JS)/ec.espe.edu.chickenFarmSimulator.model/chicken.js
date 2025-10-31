const Egg = require('./egg.js');
const Poop = require('./poop.js');
class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
        this._eggProduced = 0;
    }

    methodsChicken=[this.cluck, this.eat, this.wander, this.drink, this.layAnEgg, this.poop]

    cluck(){
        console.log("chicken " + this._name + " is clucking");
    }

    eat(){
        console.log("chicken " + this._name + " is eating");
    }

    wander(){
        console.log("chicken " + this._name + " is wandering");
    }

    drink(){
        console.log("chicken " + this._name + " is drinking");
    }

    layAnEgg(){
        let egg = new Egg();
        this._eggProduced++;
        console.log("chicken " + this._name + " is laying an egg " + egg.toString() + " size egg");
        return egg;
    }

    poop(){
        let poop = new Poop();
        console.log("chicken " + this._name + " is pooping " + poop.toString());
        return poop;
    }

    doStuff(){
        let eggsObtainedFromThisSession = 0;
        for(let i=0; i < 5; i++){
            const randomIndex = Math.floor(Math.random() * this.methodsChicken.length);
            const chosenMethod = this.methodsChicken[randomIndex];
            if (chosenMethod === this.layAnEgg){
                chosenMethod.call(this);
                eggsObtainedFromThisSession++;
            }else{
                chosenMethod.call(this);
            }
        }
        return eggsObtainedFromThisSession;
    }

    toString(){
        return `id --> \t ${this._id} name --> \t ${this._name} color --> \t ${this._color} age --> \t ${this._age} isMolting --> \t ${this._isMolting}`;
    }


    getId() { return this._id; }
    getName() { return this._name; }
    getColor() { return this._color; }   
    getAge() { return this._age; }   
    getIsMolting() { return this._isMolting; }  
    getEggsProduced(){ return this._eggProduced;} 

    setId(id) { this._id = id; }
    setName(name) { this._name = name; }   
    setColor(color) { this._color = color; }
    setAge(age) { this._age = age; }   
    setIsMolting(isMolting) { this._isMolting = isMolting; }
    setEggsProduced(eggs) {this._eggProduced = eggs; }
}

module.exports = Chicken;