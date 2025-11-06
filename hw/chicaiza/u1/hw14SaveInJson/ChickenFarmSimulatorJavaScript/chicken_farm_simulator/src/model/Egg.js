export class Egg {
    constructor(id) {
        this.id = id;
    }
    
    toDict() {
        return { id: this.id };
    }
    
    static fromDict(data) {
        return new Egg(data.id);
    }
}