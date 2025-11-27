import { Location } from "../model/Location.js";
import { Cage } from "../model/Cage.js";
import { Chicken } from "../model/Chicken.js";
import { Cow } from "../model/Cow.js";
import { Sheep } from "../model/Sheep.js";
import { Pig } from "../model/Pig.js";

const location = new Location(10, 20);
const cage = new Cage(1, "Corral for cows", 2);

const bornOn = new Date(2025, 1, 1); // Feb 1 2025


const chicken = new Chicken(true, 0, 1, "Holstein", bornOn, "male", false, 10.4, cage);
console.log("Animal -->", chicken.toString());


const cow = new Cow(true, 20.5, 2, "Jersey", bornOn, "female", true, 450, cage);
console.log("Animal -->", cow.toString());


const sheep = new Sheep(new Date(), 3, "Merino", bornOn, "female", true, 60, cage);
console.log("Animal -->", sheep.toString());


const pig = new Pig(3.5, false, 4, "Landrace", bornOn, "male", false, 120, cage);
console.log("Animal -->", pig.toString());
