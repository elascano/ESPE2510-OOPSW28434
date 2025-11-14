class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    methodsChicken=[this.cluck, this.eat, this.wander, this.drink, this.poop, this.layAnEgg]

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
        const Egg = require('./Egg.js');
        let egg = new Egg();
        console.log("chicken " + this._name + " is laying an egg " + egg.toString() + " size egg");
        return egg;
    }

    poop(){
        const Poop = require('./Poop.js');
        let poop = new Poop();
        console.log("chicken " + this._name + " is pooping " + poop.toString());
        return poop;
    }

    doStuff(){
        const randomIndex = Math.floor(Math.random() * this.methodsChicken.length);
        const chosenMethod = this.methodsChicken[randomIndex];
        chosenMethod.call(this);
    }

    toString(){
        return \n id --> \t ${this._id} \n name --> \t ${this._name} \n color --> \t ${this._color} \n age --> \t ${this._age} \n isMolting --> \t ${this._isMolting};
    }


    getId() {
        return this._id;
    }

    getName() {
        return this._name;
    }

    getColor() {
        return this._color;
    }   

    getAge() {
        return this._age;
    }   

    getIsMolting() {
        return this._isMolting;
    }   

    setId(id) {
        this._id = id;
    }

    setName(name) {
        this._name = name;
    }   

    setColor(color) {
        this._color = color;
    }

    setAge(age) {
        this._age = age;
    }   

    setIsMolting(isMolting) {
        this._isMolting = isMolting;
    }
}

module.exports = Chicken;
