import { Chicken } from './ChickenFarmSimulator_model.js'; 

function executeApp() {
    console.log("This is my Chicken Farm Simulator");

    let owner = "César Vargas"; 
    let id = 1;
    let name = "Lucy";
    let color = "White and Brown";
    let age = 2;
    let isMolting = false;

   
    const chicken = new Chicken(id, name, color, age, isMolting);

    console.log("The chicken is --> " + chicken);

    console.log("chicken id is --> " + chicken.getId());

    chicken.getId();
}

executeApp();