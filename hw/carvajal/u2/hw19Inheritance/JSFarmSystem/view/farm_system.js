import { Cage } from "../model/Cage.js";
import { Location } from "../model/Location.js";
import { Chicken } from "../model/Chicken.js";
import { Pig } from "../model/Pig.js";
import { Cow } from "../model/Cow.js";
import { Sheep } from "../model/Sheep.js";

function main() {

    let xCoordinate = 10;
    let yCoordinate = 20;
    let weight = 10.4;
    let gender = "male";
    let isAbleToReproduce = false;

    let date = new Date(2025, 1, 1);
    let breed = "Holstein";

    let location = new Location(xCoordinate, yCoordinate);
    let cage = new Cage(1, "STABLE FOR COWS", 2, location);

    let farmAnimal;

    farmAnimal = new Chicken(true, 0, 1, breed, date, gender, isAbleToReproduce, weight, cage);
    console.log("farmAnimal --->", farmAnimal.toString());

    farmAnimal = new Pig(2, breed, date, gender, isAbleToReproduce, weight, cage);
    console.log("farmAnimal --->", farmAnimal.toString());

    farmAnimal = new Cow(true, 5, 3, breed, date, gender, isAbleToReproduce, weight, cage);
    console.log("farmAnimal --->", farmAnimal.toString());

    farmAnimal = new Sheep(date, 5, 4, breed, date, gender, isAbleToReproduce, weight, cage);
    console.log("farmAnimal --->", farmAnimal.toString());
}

main();
