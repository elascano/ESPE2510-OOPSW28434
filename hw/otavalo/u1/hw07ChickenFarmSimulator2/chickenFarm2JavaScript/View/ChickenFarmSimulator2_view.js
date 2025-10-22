const prompt = require('prompt-sync')({ sigint: true });
const Chicken = require('../model/Chicken');

console.log("Welcome to my Chicken Farm Simulator");

function getValidatedString(message) {
  let value;
  do {
    value = prompt(message).trim();
    if (!isNaN(value) || value === "") {
      console.log(" Please enter a valid text (letters only).");
      value = null;
    }
  } while (!value);
  return value;
}

function getValidatedInt(message) {
  let value;
  do {
    value = prompt(message);
    if (!/^\d+$/.test(value)) {
      console.log(" Please enter a valid integer number.");
      value = null;
    }
  } while (!value);
  return parseInt(value);
}

function getValidatedBoolean(message) {
  let value;
  do {
    value = prompt(message + " (true/false): ").toLowerCase().trim();
    if (value !== "true" && value !== "false") {
      console.log(" Please enter 'true' or 'false'.");
      value = null;
    }
  } while (!value);
  return value === "true";
}

function createChicken(id) {
  console.log(`\n Enter data for chicken #${id}:`);

  const name = getValidatedString("Name: ");
  const color = getValidatedString("Color: ");
  const age = getValidatedInt("Age: ");
  const isMolting = getValidatedBoolean("Is molting");

  const chicken = new Chicken(name, color, age, isMolting);
  console.log(chicken.toString());
  chicken.doStuff();
  console.log('---------------------------');
}

createChicken(1);
createChicken(2);
