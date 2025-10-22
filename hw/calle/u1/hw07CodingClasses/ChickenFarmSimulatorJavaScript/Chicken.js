class Chicken {
    constructor(id, name, color, isMolting, age) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    cluck() {
        console.log(`chicken ${this.name} is clucking, cluck, cluck , cluck`);
    }

    eat() {
        console.log(`chicken ${this.name} is eating, grains`);
    }
}

module.exports = Chicken;