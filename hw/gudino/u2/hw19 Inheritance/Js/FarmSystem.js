// FarmSystem.js
import { Location } from "./Location.js";
import { Cage } from "./Cage.js";
import { Pig } from "./Pig.js";

function main() {
    const location = new Location(15, 15);

    const cage = new Cage(1, "STY FOR PIGS", 4, location);

    const pig = new Pig(
        4,
        "Landrace",
        new Date(2024, 8, 20),
        "male",
        true,
        120.5,
        cage
    );

    console.log("farmAnimal (Pig) ---> " + pig.toString());
}

main();
