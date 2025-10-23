import { Chicken } from "./chicken.js";

console.log("This is my Chicken Farm Simulator");

const chicken2 = new Chicken(0, "Maruja", "white", 1, true);

let owner = "Bryan Gudino";
let id = 1;
let name = "Lucy";
let color = "black and brown";
let age = 2;
let isMolting = false;

const chicken = new Chicken(id, name, color, age, isMolting);
console.log("The chicken is ---->", chicken.toString());

console.log("chicken id --->", chicken.getId(), chicken.getName());
chicken.getId();
chicken.doStuff();
chicken2.getId();
chicken2.doStuff();
