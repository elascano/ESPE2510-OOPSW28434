import { Location } from '../ec.edu.espe.model/Location.js';
import { Cage } from '../ec.edu.espe.model/Cage.js';
import { Chicken } from '../ec.edu.espe.model/Chicken.js';
import { Cow } from '../ec.edu.espe.model/Cow.js';
import { Pig } from '../ec.edu.espe.model/Pig.js';
import { Sheep } from '../ec.edu.espe.model/Sheep.js';


console.log("--------------------------------");
console.log("    FARM SYSTEM - INICIANDO     ");
console.log("--------------------------------");

const location1 = new Location(10, 20);
const cage1 = new Cage(1, location1);


const chicken = new Chicken(1, "Leghorn", new Date('2023-01-01'), "Hembra", true, 2.5, cage1, false, 0);
const cow = new Cow(2, "Holstein", new Date('2022-05-15'), "Hembra", true, 500, cage1, true, 20);
const pig = new Pig(3, "Duroc", new Date('2024-02-01'), "Macho", false, 120, cage1);
const sheep = new Sheep(4, "Merino", new Date('2023-08-10'), "Hembra", true, 70, cage1, new Date('2024-01-01'));

const animals = [chicken, cow, pig, sheep];

console.log("\n--- REPORTE DE ANIMALES ---");
animals.forEach(animal => {
    console.log(`\nTipo: ${animal.constructor.name}`);
    console.log(`ID: ${animal.id}, Raza: ${animal.breed}, Peso: ${animal.weight}kg`);
    console.log(`Edad: ${animal.getAgeInMonths()} meses`);
});

console.log("\n--- ACCIONES DE LA GRANJA ---");

console.log("> Prueba Gallina:");
chicken.layAnEgg();

console.log("\n> Prueba Vaca:");
cow.milk();

console.log("\n> Prueba Oveja:");
sheep.cutWool();

console.log("\n--------------------------------");
console.log("    FARM SYSTEM - FINALIZADO    ");
console.log("--------------------------------");