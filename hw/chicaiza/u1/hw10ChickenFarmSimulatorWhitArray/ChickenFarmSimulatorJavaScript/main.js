const Chicken = require('./Chicken');
const HenHouse = require('./HenHouse');

function main() {
    // Crear dos gallineros
    const henHouse1 = new HenHouse(1, "North Side");
    const henHouse2 = new HenHouse(2, "South Side");

    // Crear 10 gallinas (datos estáticos)
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

    // Asignar gallinas a los gallineros
    for (let i = 0; i < chickens.length; i++) {
        if (i < 5) {
            henHouse1.addChicken(chickens[i]);
        } else {
            henHouse2.addChicken(chickens[i]);
        }
    }

    // Mostrar resultados
    henHouse1.showChickens();
    henHouse2.showChickens();
}

main();