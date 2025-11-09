const Chicken = require("./chicken");

function main() {
  console.log("This is my Chicken Farm Simulator\n");

  const chicken2 = new Chicken(0, "Maruja", "White", 1, true);

  const owner = "Mateo Cevallos";
  const id = 1;
  const name = "Lucy";
  const color = "White and Brown";
  const age = 2;
  const isMolting = false;

  const chicken = new Chicken(id, name, color, age, isMolting);

  console.log("The chicken is", chicken.toString());
  console.log(`chicken id --> ${chicken.id}, name --> ${chicken.name}`);
}

main();
