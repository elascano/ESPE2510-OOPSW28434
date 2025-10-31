export class ChickenCoop {
    static allCoops = []; 
    
    id;

    constructor(name) {
        this.id = ChickenCoop.allCoops.length + 1; 
        this.name = name;
        this.chickens = []; 
        
        ChickenCoop.allCoops.push(this); 
    }

    getId() {
        return this.id;
    }

    getName() {
        return this.name;
    }
    
    getChickens() {
        return this.chickens;
    }

    addChicken(chicken) {
        this.chickens.push(chicken);
    }

    getNumberOfChickens() {
        return this.chickens.length;
    }

    showCoopContents() {
        console.log(`\n---------------------------------------`);
        console.log(` CHICKEN COOP: ${this.name} (${this.getNumberOfChickens()} chickens)`);
        console.log(`---------------------------------------`);
        if (this.chickens.length === 0) {
            console.log("This coop is empty.");
        } else {
            this.chickens.forEach(chicken => console.log(chicken.toString()));
        }
    }

    static listAllCoops() {
        console.log("\n=== GLOBAL CHICKEN COOP COLLECTION ===");
        ChickenCoop.allCoops.forEach(coop => coop.showCoopContents());
    }
}