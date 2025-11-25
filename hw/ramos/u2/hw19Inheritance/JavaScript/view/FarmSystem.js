import { Location } from "../model/Location.js";
import { Cage } from "../model/Cage.js";
import { Chicken } from "../model/Chicken.js";
import { Cow } from "../model/Cow.js";
import { Sheep } from "../model/Sheep.js";
import { Pig } from "../model/Pig.js";

const location = new Location(30, 10);
const cage = new Cage(1, "Corral for cows", 2);

const bornOn = new Date(2023, 2, 12); 


const chicken = new Chicken(true, 0, 1, "Holstein", bornOn, "male", false, 5, cage);
console.log("Animal -->", chicken.toString());


const cow = new Cow(true, 15, 2, "Jersey", bornOn, "female", true, 500, cage);
console.log("Animal -->", cow.toString());


const sheep = new Sheep(new Date(), 3, "Merino", bornOn, "female", true, 55, cage);
console.log("Animal -->", sheep.toString());


const pig = new Pig(5, false, 4, "Landrace", bornOn, "male", false, 115, cage);
console.log("Animal -->", pig.toString());
