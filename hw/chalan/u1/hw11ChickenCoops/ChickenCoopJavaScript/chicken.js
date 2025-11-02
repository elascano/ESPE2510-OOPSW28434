class Chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    toString() {
        return `Chicken(id=${this.id}, name='-->${this.name}', color='--->${this.color}', age=${this.age}, isMolting=${this.isMolting})`;
    }

    toList() {
        return [this.id, this.name, this.color, this.age, this.isMolting];
    }

    doStuff() {
        console.log(`${this.name} is clucking, eating, and walk.`);
    }
}

export { Chicken };

