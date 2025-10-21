const  Chicken  = require('./Chicken.js');

const readline = require('readline');

console.log("This is my chicken Farm Simulator by Mathews Pastor");

const owner = "Mathews Pastor";
let id = 1;
const name = "Lucy";
const color = "White and Brown";
let age = 2;
let isMolting = false;

let chicken = new Chicken(id, name, color, age, isMolting);

console.log(`The chicken is: ${chicken.toString()}`);