import { Cage } from "../model/Cage.js";
import { Location } from "../model/Location.js";
import { Pig } from "../model/Pig.js";
import { Chicken } from "../model/Chicken.js";
import { FarmSystem } from "../model/FarmSystem.js";
import { FarmView } from "./FarmView.js";

const view = new FarmView();
const farm = new FarmSystem();

const loc1 = new Location("North Field", "A3");
const cage1 = new Cage("C-101", 3, loc1);


const pig1 = new Pig("Porky", 3, 150, "Yorkshire");
const chicken1 = new Chicken("Clara", 1, 5, 6);
const chicken2 = new Chicken("Pecky", 2, 4, 4);


view.showAnimalAdded(cage1.addAnimal(pig1));
view.showAnimalAdded(cage1.addAnimal(chicken1));
view.showAnimalAdded(cage1.addAnimal(chicken2));

view.showAnimalSound(pig1);
view.showAnimalSound(chicken1);


farm.addCage(cage1);
view.showAnimalsInCage(cage1.listAnimals());
view.showCagesList(farm.listCages());
