const fs = require('fs');
const Chicken = require('./Chicken');
const HenHouse = require('./HenHouse');

function saveChickensToCSV(chickens, filename = 'chickens.csv') {
    const headers = ['Name', 'Age', 'Color', 'Molting'];
    const rows = chickens.map(ch => `${ch.name},${ch.age},${ch.color},${ch.molting}`);
    const csvContent = [headers.join(','), ...rows].join('\n');
    fs.writeFileSync(filename, csvContent, 'utf8');
    console.log(`Data saved in ${filename}`);
}

function main() {
    const henHouse1 = new HenHouse(1, "North Side");
    const henHouse2 = new HenHouse(2, "South Side");

    const chickens = [
        new Chicken("Lucy", 2, "Brown", false),
        new Chicken("Molly", 1, "White", true),
        new Chicken("Bella", 3, "Black", false),
        new Chicken("Luna", 2, "Red", false),
        new Chicken("Coco", 4, "Yellow", true),
        new Chicken("Nina", 1, "Gray", false),
        new Chicken("Lola", 3, "White", true),
        new Chicken("Penny", 2, "Brown", false),
        new Chicken("Daisy", 5, "Black", false),
        new Chicken("Rosie", 1, "White", false)
    ];

    for (let i = 0; i < chickens.length; i++) {
        if (i < 5) {
            henHouse1.addChicken(chickens[i]);
        } else {
            henHouse2.addChicken(chickens[i]);
        }
    }

    henHouse1.showChickens();
    henHouse2.showChickens();

    const allChickens = [...henHouse1.chickens, ...henHouse2.chickens];
    saveChickensToCSV(allChickens);
}

main();