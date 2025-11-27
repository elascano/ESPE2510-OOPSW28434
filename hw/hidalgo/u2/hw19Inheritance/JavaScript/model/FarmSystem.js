export class FarmSystem {
    constructor() {
        this.cages = [];
    }

    addCage(cage) {
        this.cages.push(cage);
    }

    listCages() {
        return this.cages.map(c => c.id);
    }
}
