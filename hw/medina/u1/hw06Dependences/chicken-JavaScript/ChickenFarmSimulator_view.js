const Chicken = require('./Chicken_model.js');



console.log(" This is my Chicken Farm SIMULATOR 🧑‍🌾");
console.log("The chicken owner is Joseph Medina");

const chicken = new Chicken(1, "Lucy", "White and Brown", 2, false);

console.log("The chicken is:");
console.log(chicken.toString());

