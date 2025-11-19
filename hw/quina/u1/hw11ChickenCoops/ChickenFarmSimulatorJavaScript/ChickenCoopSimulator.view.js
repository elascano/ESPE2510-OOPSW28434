const Chicken = require('./Chicken');
const { makeChickenPoop } = require('./Poop');
const { layEgg } = require('./Egg');
const fs = require('fs');

class ChickenCoop {
    constructor(id, name) {
        this.id = id;
        this.name = name;
        this.chickens = [];
    }

    addChicken(chicken) {
        this.chickens.push(chicken);
    }

    showCoop() {
        console.log(`\n--- Coop ${this.name} (ID: ${this.id}) ---`);
        if (this.chickens.length === 0) {
            console.log("No chickens here!");
        } else {
            console.log("Chickens:");
            for (const chicken of this.chickens) {
                console.log(`  - ID: ${chicken.id}, Name: ${chicken.name}, Color: ${chicken.color}, Age: ${chicken.age}, Molting: ${chicken.isMolting ? 'Yes' : 'No'}`);
            }
        }
    }
}

class ChickenCoopSimulator {
    constructor() {
        this.coops = [];
        this.initializeData();
    }

    initializeData() {
        const coop1 = new ChickenCoop(1, "Coop A");
        const coop2 = new ChickenCoop(2, "Coop B");

        const chickens = [
            new Chicken(1, "Lucy", "White and Brown", false, 2),
            new Chicken(2, "Maruja", "White", true, 1),
            new Chicken(3, "Rosie", "Black", false, 3),
            new Chicken(4, "Lola", "Golden", false, 4),
            new Chicken(5, "Nina", "Gray", true, 2),
            new Chicken(6, "Sofi", "Red", false, 1),
            new Chicken(7, "Mona", "Brown", false, 3),
            new Chicken(8, "Clara", "White", false, 5),
            new Chicken(9, "Tina", "Yellow", true, 2),
            new Chicken(10, "Lili", "Black and White", false, 1),
        ];

        for (let i = 0; i < chickens.length; i++) {
            if (i < 5) coop1.addChicken(chickens[i]);
            else coop2.addChicken(chickens[i]);
        }

        this.coops.push(coop1, coop2);
    }

    showAllCoops() {
        for (const coop of this.coops) {
            coop.showCoop();
        }
    }

    saveToCSV() {
        let data = "CoopID,CoopName,ChickenID,Name,Color,Age,IsMolting\n";
        for (const coop of this.coops) {
            for (const chicken of coop.chickens) {
                data += `${coop.id},${coop.name},${chicken.id},${chicken.name},${chicken.color},${chicken.age},${chicken.isMolting}\n`;
            }
        }
        fs.writeFileSync("chickens_data.csv", data);
    }

    run() {
        console.log(" Chicken Coop Simulator - by Maryuri");
        this.showAllCoops();
        this.saveToCSV();
    }
}

const simulator = new ChickenCoopSimulator();
simulator.run();