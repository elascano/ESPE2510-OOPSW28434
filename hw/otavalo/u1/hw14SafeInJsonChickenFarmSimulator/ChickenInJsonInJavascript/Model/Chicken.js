
class Chicken {
    static nextId = 1;

    constructor(name, color, age, isMolting, id = null) {
        if (id === null) {
            this.id = String(Chicken.nextId);
            Chicken.nextId++;
        } else {
            this.id = String(id);
            if (parseInt(this.id) >= Chicken.nextId) {
                Chicken.nextId = parseInt(this.id) + 1;
            }
        }
        
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    toDict() {
        return {
            id: this.id,
            name: this.name,
            color: this.color,
            age: this.age,
            isMolting: this.isMolting
        };
    }

    static fromDict(data) {
        return new Chicken(
            data.name,
            data.color,
            data.age,
            data.isMolting,
            data.id
        );
    }
}

module.exports = { Chicken };