// Maryuri Quiña
import fs from 'fs';
import { Location } from '../ec.edu.espe.FarmSystem.model/Location.js';
import { Cage } from '../ec.edu.espe.FarmSystem.model/Cage.js';
import { FarmAnimal } from '../ec.edu.espe.FarmSystem.model/FarmAnimal.js';
import { Chicken } from '../ec.edu.espe.FarmSystem.model/Chicken.js';
import { Cow } from '../ec.edu.espe.FarmSystem.model/Cow.js';
import { Pig } from '../ec.edu.espe.FarmSystem.model/Pig.js';
import { Sheep } from '../ec.edu.espe.FarmSystem.model/Sheep.js';

function showAnimalCard(animal, number) {
  console.log('\n----------------------------------------');
  console.log(`  ANIMAL #${number}  (${animal.constructor.name})`);
  console.log('----------------------------------------');
  console.log(` Id:            ${animal.id}`);
  console.log(` Breed:         ${animal.breed}`);
  console.log(` Gender:        ${animal.gender}`);
  console.log(` Born on:       ${animal.bornOn.toISOString().slice(0, 10)}`);
  console.log(` Weight (kg):   ${animal.weight}`);
  console.log(` Reproduce:     ${animal.isAbleToReproduce}`);
  console.log(' Cage:');
  console.log(`   - Id:        ${animal.cage.id}`);
  console.log(`   - Type:      ${animal.cage.type}`);
  console.log(`   - Desc:      ${animal.cage.description}`);
  console.log(
    `   - Location:  (${animal.cage.location.xCoordinate}, ${animal.cage.location.yCoordinate})`
  );

  if (animal instanceof Chicken) {
    console.log(' Extra (Chicken):');
    console.log(`   - Is molting: ${animal.isMolting}`);
    console.log(`   - Laid eggs:  ${animal.laidEggs}`);
  } else if (animal instanceof Cow) {
    console.log(' Extra (Cow):');
    console.log(`   - Produces milk: ${animal.isProducingMilk}`);
    console.log(`   - Liters/day:    ${animal.litersADay}`);
  } else if (animal instanceof Sheep) {
    console.log(' Extra (Sheep):');
    console.log(`   - Last shearing: ${animal.lastShearing.toISOString().slice(0, 10)}`);
  } else if (animal instanceof Pig) {
    console.log(' Extra (Pig):');
    console.log('   - No extra fields');
  }

  console.log('----------------------------------------');
}

function main() {
  const lastShearing = new Date(2006, 10, 4); // mes 10 = noviembre
  const xCoordinate = 10;
  const yCoordinate = 20;
  const isAbleToReproduce = false;
  const weight = 10.4;
  const gender = 'male';
  const bornOn = new Date(2025, 1, 1); // mes 1 = febrero
  const breed = 'Holstein';

  const location = new Location(xCoordinate, yCoordinate);
  const cage = new Cage(1, 'Corral for cows', 2, location);

  /** @type {FarmAnimal[]} */
  const animals = [];

  animals.push(new Chicken(true, 0, 1, breed, bornOn, gender, isAbleToReproduce, weight, cage));
  animals.push(new Cow(true, 1.5, 2, breed, bornOn, gender, isAbleToReproduce, weight, cage));
  animals.push(new Pig(3, breed, bornOn, gender, isAbleToReproduce, weight, cage));
  animals.push(new Sheep(lastShearing, 4, breed, bornOn, gender, isAbleToReproduce, weight, cage));

  console.log('========== FARM ANIMALS REPORT ==========');
  animals.forEach((a, i) => showAnimalCard(a, i + 1));
  console.log('\n============== END OF REPORT ==============');

  // Guardar también en JSON
  const jsonData = animals.map(a => ({
    type: a.constructor.name,
    id: a.id,
    breed: a.breed,
    gender: a.gender,
    bornOn: a.bornOn.toISOString(),
    weight: a.weight,
    isAbleToReproduce: a.isAbleToReproduce,
    cage: {
      id: a.cage.id,
      type: a.cage.type,
      description: a.cage.description,
      location: {
        xCoordinate: a.cage.location.xCoordinate,
        yCoordinate: a.cage.location.yCoordinate
      }
    }
  }));

  fs.writeFileSync('animals.json', JSON.stringify(jsonData, null, 2));
  console.log('\n>> Data saved to animals.json');
}

main();
