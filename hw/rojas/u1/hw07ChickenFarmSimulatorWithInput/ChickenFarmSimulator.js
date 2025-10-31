// ============================================
// Chicken Farm Simulator
// Author: Josue Rojas
// Owner: Josue Rojas
// ============================================

const readline = require("node:readline");
const Chicken = require("./Chicken");

function makeAsk() {
  const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
  const ask = (q) => new Promise((res) => rl.question(q, (ans) => res(ans.trim())));
  return { ask, close: () => rl.close() };
}

async function createChicken(nextId, ask) {
  console.log("\nNow is your moment to register a new chicken");
  const name = (await ask("Enter chicken name: ")) || "NoName";
  const color = (await ask("Enter chicken color: ")) || "Brown";
  const ageRaw = await ask("Enter chicken age: ");
  const age = parseInt(ageRaw) || 1;
  const molting = (await ask("Is the chicken molting? (yes/no): ")).toLowerCase();
  const isMolting = molting === "yes" || molting === "y";

  const c = new Chicken(nextId, name, color, age, isMolting);
  console.log("\nYou have registered a new chicken:");
  console.log(c.toString());
  return c;
}

(async function main() {
  const { ask, close } = makeAsk();

  console.log("Welcome to the Chicken Farm Simulator by Josue Rojas\n");

  // initial chickens
  const chickens = [
    new Chicken(1, "Lucy", "White", 2, false),
    new Chicken(2, "Maruja", "Brown", 3, true),
  ];

  for (const c of chickens) {
    console.log(c.toString());
    await c.doRandomActions(ask);
  }

  let nextId = 3;
  let currentIndex = 0;

  while (true) {
    const choice = (await ask("\nDo you want to see another chicken or add a new one? (see/add/exit): ")).toLowerCase();

    if (choice === "see" || choice === "s") {
      currentIndex++;
      if (currentIndex >= chickens.length) {
        console.log("There are no more chickens to see.");
        currentIndex = chickens.length - 1;
        continue;
      }
      const ch = chickens[currentIndex];
      console.log(ch.toString());
      await ch.doRandomActions(ask);

    } else if (choice === "add" || choice === "a") {
      const ch = await createChicken(nextId++, ask);
      chickens.push(ch);
      await ch.doRandomActions(ask);
      currentIndex = chickens.length - 1;

    } else if (choice === "exit" || choice === "no" || choice === "n") {
      console.log("\nSimulation finished. Goodbye, farmer!");
      break;

    } else {
      console.log("Please choose a valid option: see / add / exit.");
    }
  }

  close();
})();
