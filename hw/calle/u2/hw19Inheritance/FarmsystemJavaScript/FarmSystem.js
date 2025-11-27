import { Cage } from './modules/Cage.js';
import { Location } from './modules/Location.js';
import { Chicken } from './modules/Chicken.js';
import { Cow } from './modules/Cow.js';
import { Sheep } from './modules/Sheep.js';
import { Pig } from './modules/Pig.js';

function main() {
    let xCoordinate = 10;
    let yCoordinate = 20;
    let weight = 10.4;
    let gender = "male";
    let isAbleToReproduce = false;
    let breed = "Holstein";

    let bornOn = new Date(2024, 0, 1); 

    let location = new Location(xCoordinate, yCoordinate);
    let cage = new Cage(1, "Corral for cows", 2, location);

    console.log("--- Farm System  ---");

    let chicken = new Chicken(true, 0, 1, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    console.log("farmAnimal --> Chicken{" + chicken.toString().replace("Chicken{", "FarmAnimal --> Chicken{") );
    chicken.layAnEgg();
    console.log(`Laid Eggs: ${chicken.getLaidEggs()}`);
    console.log("-".repeat(30));

    let cow = new Cow(true, 1.5, 2, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    console.log("farmAnimal --> Cow{" + cow.toString().replace("Cow{", "FarmAnimal --> Cow{") );
    cow.milk();
    cow.setIsProducingMilk(false);
    cow.milk();
    console.log("-".repeat(30));

    let lastSheering = new Date(bornOn);
    lastSheering.setDate(bornOn.getDate() + 10);

    let sheep = new Sheep(lastSheering, 3, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    console.log("farmAnimal --> Sheep{" + sheep.toString().replace("Sheep{", "FarmAnimal --> Sheep{") );
    sheep.cutWhool();
    console.log("-".repeat(30));

    let pig = new Pig(4, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    console.log("farmAnimal --> Pig{" + pig.toString().replace("Pig{", "FarmAnimal --> Pig{") );
}

main();