const readline = require('readline');
const Chicken = require('./Chicken');
const ChickenCoop = require('./ChickenCoop');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function ask(question) {
  return new Promise(resolve => rl.question(question, answer => resolve(answer)));
}

(async function main() {
  console.log(" Welcome to the Chicken Farm Simulator ");

  const coops = [];

  while (true) {
    const coopId = parseInt(await ask("\nEnter coop ID: "));
    const coopName = await ask("Enter coop name: ");
    const coop = new ChickenCoop(coopId, coopName);

    while (true) {
      console.log(`\n--- Add chicken to coop ${coopName} ---`);
      const id = parseInt(await ask("ID: "));
      const name = await ask("Name: ");
      const color = await ask("Color: ");
      const age = parseInt(await ask("Age: "));
      const moltingAnswer = (await ask("Is molting? (yes/no): ")).toLowerCase();
      const isMolting = moltingAnswer === "yes";

      const chicken = new Chicken(id, name, color, age, isMolting);
      coop.addChicken(chicken);
      chicken.saveToCSV();

      const moreChickens = (await ask("Add another chicken to this coop? (y/n): ")).toLowerCase();
      if (moreChickens !== 'y') break;
    }

    coops.push(coop);
    coop.saveCoopToCSV();

    const moreCoops = (await ask("\nAdd another coop? (y/n): ")).toLowerCase();
    if (moreCoops !== 'y') break;
  }

  rl.close();

  console.log("\n--- Running chicken simulation ---\n");

  coops.forEach(coop => {
    console.log(`\n### Coop: ${coop.name} ###`);
    coop.chickens.forEach(chicken => {
      console.log(chicken.toString());
      chicken.doStuff();
    });
  });

  console.log("\nSimulation complete. Data saved in chickens.csv and chicken_coops.csv");
})();

