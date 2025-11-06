class ChickenCoop {
    constructor(id, description) {
        this.id = id;
        this.description = description;
        this.chickens = [];
    }

    addChicken(chicken) {
        this.chickens.push(chicken);
        console.log(`Chicken ${chicken.getName()} added to coop ${this.id}`);
    }

    removeChicken(chickenId) {
        const index = this.chickens.findIndex(chicken => chicken.getId() === chickenId);
        if (index !== -1) {
            this.chickens.splice(index, 1);
            console.log(`Chicken with ID ${chickenId} removed from coop ${this.id}`);
        } else {
            console.log(`Chicken with ID ${chickenId} not found in coop ${this.id}`);
        }
    }

    listChickens() {
        console.log(`\n--- Chickens in Coop ${this.id} - ${this.description} ---`);
        if (this.chickens.length === 0) {
            console.log("No chickens in this coop.");
        } else {
            for (const chicken of this.chickens) {
                console.log(`  ${chicken.toString()}`);
            }
        }
    }

    makeAllDoStuff() {
        console.log(`\n--- All chickens in Coop ${this.id} are active! ---`);
        if (this.chickens.length === 0) {
            console.log("No chickens in this coop to do stuff.");
            return;
        }
        
        for (const chicken of this.chickens) {
            console.log(`\n--- ${chicken.getName()} is doing stuff ---`);
            chicken.doStuff();
        }
    }

    findChickenById(chickenId) {
        return this.chickens.find(chicken => chicken.getId() === chickenId) || null;
    }

    getChickens() {
        return this.chickens;
    }
    
    getId() {
        return this.id;
    }
    
    getDescription() {
        return this.description;
    }
    
    getChickenCount() {
        return this.chickens.length;
    }
    
    toString() {
        return `ChickenCoop{id=${this.id}, description='${this.description}', chickens=${this.chickens.length}}`;
    }
}

module.exports = ChickenCoop;