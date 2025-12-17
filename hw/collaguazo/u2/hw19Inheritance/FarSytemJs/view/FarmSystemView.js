import { Location } from "../model/Location.js";
import { Cage } from "../model/Cage.js";
import { Chicken } from "../model/Chicken.js";
import { Cow } from "../model/Cow.js";
import { Sheep } from "../model/Sheep.js";
import { Pig } from "../model/Pig.js";

console.log("=== FARM SYSTEM / JAVASCRIPT VIEW ===\n");

const location = new Location(10, 10);
const cage = new Cage(1, "Stable for cows", 2, location);

const chicken = new Chicken(1, "Criolla", "2025-01-01", "female", true, 3.4, cage, true, 2);
const cow = new Cow(2, "Holstein", "2024-05-22", "female", false, 150.4, cage, true, 12.5);
const sheep = new Sheep(3, "Merina", "2023-03-11", "male", false, 40.2, cage, "2025-02-02");
const pig = new Pig(4, "Large White", "2024-09-10", "female", false, 80.8, cage);

console.log(chicken.toString());
console.log("\n");
console.log(cow.toString());
console.log("\n");
console.log(sheep.toString());
console.log("\n");
console.log(pig.toString());
