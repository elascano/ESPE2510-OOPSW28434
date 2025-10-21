const Chicken = require("./Chicken_model");

console.log("Welcome to my Chicken Farm Simulator");

let chicken = new Chicken(1, "Maruja", "White", 2, false);

console.log(`The owner of the chicken is: ${chicken.getOwner()}`);

chicken.setId(1);
chicken.setName("Lucy");
chicken.setColor("Brown and White");
chicken.setAge(6);
chicken.setIsMolting(true);

console.log(chicken.toString());

