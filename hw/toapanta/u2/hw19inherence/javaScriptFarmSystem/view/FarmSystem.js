<<<<<<< HEAD
const Cage = require('../model/Cage');
const Chicken = require('../model/Chicken');
const Cow = require('../model/Cow');
const Location = require('../model/Location');
const Pig = require('../model/Pig');
const Sheep = require('../model/Sheep');

class FarmSystem {
    static main() {
        let id;
        let breed;
        let bornOn;
        let gender;
        let genderSheep;
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
        genderSheep = "female";
        isAbleToReproduce = false;

        bornOn = new Date(2025, 2, 12);
        breed = "Holstein";

        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1, "stable for cows", 2, location);

        let bornOnSheep;
        bornOnSheep = new Date(2025, 1, 1);

        let chicken;
        chicken = new Chicken(true, 0, 1, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);

        let sheep;
        sheep = new Sheep(bornOnSheep, 2, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);

        let cow;
        cow = new Cow(isAbleToReproduce, weight, 3, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);

        let pig;
        pig = new Pig(4, breed, bornOn, gender, true, weight, cage, location);

        console.log("farmAnimal --> " + chicken.toString());
        console.log("farmAnimal --> " + sheep.toString());
        console.log("farmAnimal --> " + cow.toString());
        console.log("farmAnimal --> " + pig.toString());
    }
}

=======
const Cage = require('../model/Cage');
const Chicken = require('../model/Chicken');
const Cow = require('../model/Cow');
const Location = require('../model/Location');
const Pig = require('../model/Pig');
const Sheep = require('../model/Sheep');

class FarmSystem {
    static main() {
        let id;
        let breed;
        let bornOn;
        let gender;
        let genderSheep;
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
        genderSheep = "female";
        isAbleToReproduce = false;

        bornOn = new Date(2025, 2, 12);
        breed = "Holstein";

        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1, "stable for cows", 2, location);

        let bornOnSheep;
        bornOnSheep = new Date(2025, 1, 1);

        let chicken;
        chicken = new Chicken(true, 0, 1, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);

        let sheep;
        sheep = new Sheep(bornOnSheep, 2, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);

        let cow;
        cow = new Cow(isAbleToReproduce, weight, 3, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);

        let pig;
        pig = new Pig(4, breed, bornOn, gender, true, weight, cage, location);

        console.log("farmAnimal --> " + chicken.toString());
        console.log("farmAnimal --> " + sheep.toString());
        console.log("farmAnimal --> " + cow.toString());
        console.log("farmAnimal --> " + pig.toString());
    }
}

>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
FarmSystem.main();