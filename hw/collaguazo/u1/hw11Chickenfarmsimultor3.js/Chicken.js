class Chicken {
    static counter = 1;

    constructor(name, color, age, isMolting) {
        this.identifier = Chicken.counter++;
        this.name = name;
        this.plumageColor = color;
        this.yearsOld = age;
        this.molting = isMolting;
    }

    toString() {
        const status = this.molting ? 'True' : 'False';
        return Chicken { id => ${this.identifier}, name => ${this.name}, color => ${this.plumageColor}, age => ${this.yearsOld}, isMolting => ${status} };
    }
}

module.exports = Chicken;
