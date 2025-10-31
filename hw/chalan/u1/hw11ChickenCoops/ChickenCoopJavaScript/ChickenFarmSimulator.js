import { Chicken } from "./Chicken.js";
import { ChickenCoop } from "./ChickenCoop.js";
import fs from "fs";

function main() {
    console.log("Farm Simulation by Kevin Chalan\n");

    const chickens = [
        new Chicken(1, "katya", "black", 2, false),
        new Chicken(2, "Mari", "white", 1, true),
        new Chicken(3, "Carolina", "brown", 3, false),
        new Chicken(4, "Pin", "red", 2, false),
        new Chicken(5, "Narcisa", "yellow", 1, true),
        new Chicken(6, "Tita", "gray", 4, false),
        new Chicken(7, "Rosita", "white", 2, false),
        new Chicken(8, "Lolita", "black", 1, false),
        new Chicken(9, "wasp", "orange", 3, true),
        new Chicken(10, "Cherita", "brown", 2, false)
    ];

    const coop1 = new ChickenCoop(101);
    const coop2 = new ChickenCoop(102);

    chickens.forEach((chicken, i) => {
        if (i < 5) coop1.add(chicken);
        else coop2.add(chicken);
    });

    console.log("COOP ONE");
    coop1.showAllChickens();

    console.log("\nCOOP TWO");
    coop2.showAllChickens();

    saveToCsv("chickensData.csv", [coop1, coop2]);

    console.log("\n'chickensData.csv' is saving!");

    console.log("\nExample actions:");
    chickens[0].doStuff();
    chickens[3].doStuff();
    chickens[7].doStuff();
}

function saveToCsv(filename, coops) {
    const header = ["Coop ID", "Chicken ID", "Name", "Color", "Age", "Is Molting"];
    const rows = coops.flatMap(coop => coop.toList());
    const csvData = [header, ...rows].map(r => r.join(",")).join("\n");
    fs.writeFileSync(filename, csvData, "utf8");
}

main();

