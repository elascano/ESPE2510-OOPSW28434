//KEVIN CHALAN NRC28434
const Chicken = require('./chicken.js');

console.log("This is my Chicken Farm Simulator\n");


const chicken = new Chicken(1, "Lucy", "black and brown", 2, false);

console.log("The chicken is ---->");
console.log(chicken.toString());

// getters
console.log(`\nChicken id ---> ${chicken.id}, name ---> ${chicken.name}`);


