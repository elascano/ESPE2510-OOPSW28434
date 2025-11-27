import { Location } from "./Location.js";
import { Cage } from "./Cage.js";
import { Chicken } from "./Chicken.js";
import { Pig } from "./Pig.js";
import { Cow } from "./Cow.js";
import { Sheep } from "./Sheep.js";

function main() {
    const xCoordinate = 10;
    const yCoordinate = 20;
    const weight = 10.4;
    const gender = "male";
    const bornOn = new Date(2025, 2, 1); // Marzo = 2
    const breed = "Holstein";

    const location = new Location(xCoordinate, yCoordinate);
    const cage = new Cage(1, "Stable for cows", 2, location);

    let farmAnimal = new Chicken(true, 0, 1, breed, bornOn, gender, true, weight, cage);
    console.log("farmAnimal --> Chicken", farmAnimal.toString());

    farmAnimal = new Pig(2, breed, bornOn, gender, false, weight, cage);
    console.log("farmAnimal --> Pig", farmAnimal.toString());

    farmAnimal = new Cow(false, weight, 3, breed, bornOn, gender, true, weight, cage);
    console.log("farmAnimal --> Cow", farmAnimal.toString());

    farmAnimal = new Sheep(bornOn, 4, breed, bornOn, gender, false, weight, cage);
    console.log("farmAnimal --> Sheep", farmAnimal.toString());
}

main();
