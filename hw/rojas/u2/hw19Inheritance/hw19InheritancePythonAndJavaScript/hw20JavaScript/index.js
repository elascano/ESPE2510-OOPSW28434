
import { Location } from './ec.edu.espe.model/Location.js';
import { Cage } from './ec.edu.espe.model/Cage.js';
import { Chicken } from './ec.edu.espe.model/Chicken.js';
import { Cow } from './ec.edu.espe.model/Cow.js';
import { Pig } from './ec.edu.espe.model/Pig.js';
import { Sheep } from './ec.edu.espe.model/Sheep.js';


const location1 = new Location(150, 300);
const cage1 = new Cage(1, location1);

console.log("--- SYSTEM START ---");



const chicken = new Chicken(1, "Leghorn", new Date('2023-06-01'), "Female", true, 1.5, cage1, false, 5);
const cow = new Cow(2, "Holstein", new Date('2022-02-15'), "Female", true, 600, cage1, true, 15.5);
const pig = new Pig(3, "Duroc", new Date('2024-01-10'), "Male", false, 120, cage1);
const sheep = new Sheep(4, "Merino", new Date('2023-05-20'), "Female", true, 70, cage1, new Date('2024-08-01'));

console.log("\n--- Datos de los Animales ---");

const animals = [chicken, cow, pig, sheep]; 

animals.forEach(animal => {
    console.log(`\n[${animal.constructor.name}] ID: ${animal.id}`);
    console.log(`   Edad: ${animal.getAgeInMonths()} meses`);
    console.log(`   Raza: ${animal.breed}`);
});

console.log("\n--- Acciones Específicas ---");
chicken.layAnEgg(); 
cow.milk();          
sheep.cutWool();      

console.log("\n--- SYSTEM END ---");