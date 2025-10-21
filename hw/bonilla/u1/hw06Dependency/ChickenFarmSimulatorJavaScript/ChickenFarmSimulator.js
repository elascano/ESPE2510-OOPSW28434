const Chicken = require("./Chicken");
console.log("This is my Chicken Farm Simulator");
console.log("By: Arelis Samantha Bonilla Cruz");

const chicken1 = new Chicken(1, "Lucy", "White and Brown", 2, false);

console.log("The chicken is ---> " + chicken1.toString());
console.log("Chicken id --> " + chicken1.get_id());