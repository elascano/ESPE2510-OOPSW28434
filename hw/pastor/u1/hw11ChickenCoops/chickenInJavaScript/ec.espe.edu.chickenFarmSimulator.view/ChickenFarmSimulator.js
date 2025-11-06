const  Chicken  = require('../ec.espe.edu.chickenFarmSimulator.model/Chicken');
const ChickenCoop = require('../ec.espe.edu.chickenFarmSimulator.model/ChickenCoop');

console.log("This is my chicken Farm Simulator by Mathews Pastor");

const owner = "Mathews Pastor";

const chickenCoop1 = new ChickenCoop(1);
const chickenCoop2 = new ChickenCoop(2);
const chickenCoop3 = new ChickenCoop(3);

const chicken1 = new Chicken(1,"Lucy", "White and Brown", "2", false);
chickenCoop1.addChicken(chicken1);
const chicken2 = new Chicken(2,"Clueca", "Brown", "2", false);
chickenCoop1.addChicken(chicken2);
const chicken3 = new Chicken(3,"Maruja", "White", "1", true);
chickenCoop1.addChicken(chicken3);
const chicken4 = new Chicken(4,"Gusepa", "Yellow", "2", false);
chickenCoop1.addChicken(chicken4);
const chicken5 = new Chicken(5,"Martha", "Black and White", "1", false);
chickenCoop1.addChicken(chicken5);
const chicken6 = new Chicken(6,"Pepa", "Pig", "3", true);
chickenCoop1.addChicken(chicken6);

const chicken7 = new Chicken(7,"Nora", "Red", "1", false);
chickenCoop2.addChicken(chicken7);
const chicken8 = new Chicken(8,"Pipa", "Brown and Red", "2", false);
chickenCoop2.addChicken(chicken8);
const chicken9 = new Chicken(9,"Dolores", "White", "1", true);
chickenCoop2.addChicken(chicken9);
const chicken10 = new Chicken(10,"Josefa", "Black", "1", false);
chickenCoop2.addChicken(chicken10);

chickenCoop1.showChickens();
chickenCoop2.showChickens();
chickenCoop3.showChickens();



