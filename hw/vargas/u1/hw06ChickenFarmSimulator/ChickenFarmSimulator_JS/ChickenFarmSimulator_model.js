export class Chicken {
    /**
     * @param {number} id
     * @param {string} name
     * @param {string} color
     * @param {number} age
     * @param {boolean} isMolting
     */
    constructor(id, name, color, age, isMolting) {

        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    
    toString() {
       
        return `Chicken{\n\nid ->\t\t ${this._id}, \nname ->\t\t ${this._name}, \ncolor ->\t ${this._color}, \nage ->\t\t ${this._age}, \nisMolting ->\t ${this._isMolting}}`;
    }


    getId() {
        return this._id;
    }

    setId(id) {
        this._id = id;
    }

    getName() {
        return this._name;
    }

    setName(name) {
        this._name = name;
    }

    getColor() {
        return this._color;
    }

    setColor(color) {
        this._color = color;
    }

    getAge() {
        return this._age;
    }

    setAge(age) {
        this._age = age;
    }

    isMolting() {
        return this._isMolting;
    }

    setMolting(isMolting) {
        this._isMolting = isMolting;
    }
}