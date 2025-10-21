const Chicken = require('./chicken.js');

console.log("This is my Chicken Farm Simulator\n");

const chicken1 = new Chicken(0, "Maruja", "white", 1, true);

const id = 1;
const name = "Lucy";
const color = "black and brown";
const age = 2;
const isMolting = false;

const chicken2 = new Chicken(id, name, color, age, isMolting);

console.log("The chicken is ---->");
console.log(chicken2.toString());

// getters
console.log(`\nChicken id ---> ${chicken2.id}, name ---> ${chicken2.name}`);

// setters
chicken2.age = 3;
chicken2.isMolting = true;
console.log("\nAfter updating age and molting:");
console.log(chicken2.toString());
