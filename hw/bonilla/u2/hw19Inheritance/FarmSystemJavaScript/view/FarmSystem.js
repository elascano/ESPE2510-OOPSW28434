const Cage = require('../model/Cage');
const Chicken = require('../model/Chicken');
const Cow = require('../model/Cow');
const Pig = require('../model/Pig');
const Sheep = require('../model/Sheep');
const Location = require('../model/Location');

class CustomLocation extends Location {
    constructor(xCoordinate, yCoordinate) {
        super(xCoordinate, yCoordinate);
    }
}

function main() {
    let id;
    let breed;
    let bornOn;
    let gender;
    let isAbleToReproduce;
    let weight;
    let cage;
    let location;
    let xCoordinate;
    let yCoordinate;

    xCoordinate = 10;
    yCoordinate = 20;
    weight = 10.4;
    gender = "male";
    isAbleToReproduce = false;

    bornOn = new Date(2025, 2, 1); 
    breed = "Holstein";

    location = new CustomLocation(xCoordinate, yCoordinate);

    cage = new Cage(1, "Stable for cows", 2, location);

    let farmAnimal;

    farmAnimal = new Chicken(true, 0, 1, breed, bornOn, gender, true, weight, cage);
    console.log("farmAnimal --> Chicken " + farmAnimal.toString());

    farmAnimal = new Pig(2, breed, bornOn, gender, false, weight, cage);
    console.log("farmAnimal --> Pig " + farmAnimal.toString());

    farmAnimal = new Cow(false, weight, 3, breed, bornOn, gender, true, weight, cage);
    console.log("farmAnimal --> Cow " + farmAnimal.toString());

    farmAnimal = new Sheep(bornOn, 4, breed, bornOn, gender, false, weight, cage);
    console.log("farmAnimal --> Sheep " + farmAnimal.toString());
}

main();