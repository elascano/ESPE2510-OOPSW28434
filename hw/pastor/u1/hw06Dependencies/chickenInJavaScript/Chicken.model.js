class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }


    toString(){
        return `Chicken: \n id --> \t ${this._id} \n name --> \t ${this._name} \n color --> \t ${this._color} \n age --> \t ${this._age} \n isMolting --> \t ${this._isMolting}`;
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