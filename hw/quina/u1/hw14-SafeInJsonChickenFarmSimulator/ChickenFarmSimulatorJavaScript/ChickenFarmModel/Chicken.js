const Egg = require('./Egg');
const Poop = require('./Poop');
const readline = require('readline/promises');

// NOTA: Usamos una instancia global (o local) de readline en los métodos para obtener input
const rlInstance = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false 
});

class Chicken {
    constructor(chicken_id, name, color, is_molting, age) {
        this.id = chicken_id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.is_molting = is_molting;
    }

    cluck() {
        console.log(`Chicken ${this.name} says: Cluck cluck!`);
    }

    eat() {
        console.log(`Chicken ${this.name} is eating grains.`);
    }

    async lay_egg() {
        // En JS de consola, los métodos que piden input deben ser asíncronos (async/await)
        const size = await rlInstance.question("Enter egg size (small, medium, large): ");
        return this._make_egg(size);
    }

    async poop() {
        const amount = await rlInstance.question("Enter poop amount (low, medium, high): ");
        return this._make_poop(amount);
    }

    _make_egg(size) { // Método "privado" (por convención JS)
        const egg = new Egg(size);
        console.log(`Chicken ${this.name} laid ${egg.toString()}.`);
        return egg;
    }

    _make_poop(amount) { // Método "privado" (por convención JS)
        const poop = new Poop(amount);
        console.log(`Chicken ${this.name} produced ${poop.toString()}.`);
        return poop;
    }

    to_dict() {
        return {
            id: this.id,
            name: this.name,
            color: this.color,
            is_molting: this.is_molting,
            age: this.age
        };
    }
}

module.exports = Chicken;