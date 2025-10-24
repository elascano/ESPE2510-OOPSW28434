export class Chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    toString() {
        return `Chicken(${this.id}, ${this.name}, ${this.color}, ${this.age}, Molting=${this.isMolting})`;
    }
}
