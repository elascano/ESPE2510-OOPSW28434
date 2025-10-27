const chicken = require('../model/chicken');
const chickenCoop = require('../model/chickenCoop');
const fs = require('fs');

class farmController {
    constructor() {
        this.coops = [];
    }

    setupFarm() {
        const coop1 = new chickenCoop(1);
        const coop2 = new chickenCoop(2);

        const chickens = [
            new chicken(1, "lucy", "white", 2, false),
            new chicken(2, "molly", "brown", 3, true),
            new chicken(3, "daisy", "black", 1, false),
            new chicken(4, "rose", "yellow", 4, true),
            new chicken(5, "martha", "red", 2, false),
            new chicken(6, "luna", "white", 1, false),
            new chicken(7, "nora", "gray", 2, true),
            new chicken(8, "ava", "black", 3, false),
            new chicken(9, "olive", "golden", 4, true),
            new chicken(10, "ruby", "brown", 2, false)
        ];

        chickens.forEach((c, i) => {
            if (i < 5) coop1.addChicken(c);
            else coop2.addChicken(c);
        });

        this.coops.push(coop1);
        this.coops.push(coop2);
    }

    showFarm() {
        this.coops.forEach(coop => {
            console.log(coop.toString());
            coop.chickens.forEach(chicken => chicken.doStuff());
        });
    }

    saveFarmData() {
        let csvContent = "coopId,chickenId,name,color,age,isMolting\n";
        let txtContent = "";

        this.coops.forEach(coop => {
            coop.chickens.forEach(c => {
                csvContent += `${coop.coopId},${c.id},${c.name},${c.color},${c.age},${c.isMolting}\n`;
            });

            txtContent += `coop ${coop.coopId}:\n`;
            coop.chickens.forEach(c => {
                txtContent += `  ${c.toString()}\n`;
            });
            txtContent += "\n";
        });

        fs.writeFileSync("farmData.csv", csvContent);
        fs.writeFileSync("farmData.txt", txtContent);
        console.log("Data.");
    }
}

module.exports = farmController;
