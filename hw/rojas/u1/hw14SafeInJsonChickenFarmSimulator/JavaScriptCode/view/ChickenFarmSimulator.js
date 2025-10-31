// Run from project root or from /view:
//   node view/ChickenFarmSimulator.js
// JSON is saved at project root as farm_data.json

const fs = require("fs");
const path = require("path");
const readline = require("readline");

const Chicken = require("../model/Chicken");
const ChickenCoop = require("../model/ChickenCoop");

const ROOT_DIR = path.resolve(__dirname, "..");
const DATA_FILE = path.join(ROOT_DIR, "farm_data.json");

// --------------- JSON HELPERS -----------------
function saveToJson(coops) {
  const data = coops.map(coop => ({
    id: coop.id,
    chickens: coop.chickens.map(c => ({
      id: c.id,
      name: c.name,
      color: c.color,
      age: c.age,
      is_molting: c.is_molting
    }))
  }));
  fs.writeFileSync(DATA_FILE, JSON.stringify(data, null, 4), "utf-8");
}

function loadFromJson() {
  if (!fs.existsSync(DATA_FILE)) return [];
  try {
    const raw = fs.readFileSync(DATA_FILE, "utf-8");
    const parsed = JSON.parse(raw);
    const list = Array.isArray(parsed) ? parsed : (parsed.coops || []);
    const coops = [];
    for (const coopData of list) {
      if (typeof coopData !== "object") continue;
      const coop = new ChickenCoop(coopData.id ?? 0);
      for (const c of (coopData.chickens || [])) {
        coop.add(new Chicken(c.id, c.name, c.color, c.age, c.is_molting));
      }
      coops.push(coop);
    }
    return coops;
  } catch {
    return [];
  }
}

// --------------- DOMAIN HELPERS ---------------
function nextChickenId(coops) {
  let maxId = 0;
  for (const cp of coops) {
    for (const c of cp.chickens) maxId = Math.max(maxId, c.id);
  }
  return maxId + 1;
}

function findCoop(coops, coopId) {
  return coops.find(cp => cp.id === coopId) || null;
}

function findChicken(coops, chickenId) {
  for (const coop of coops) {
    const idx = coop.chickens.findIndex(ch => ch.id === chickenId);
    if (idx !== -1) return { coop, chicken: coop.chickens[idx], index: idx };
  }
  return { coop: null, chicken: null, index: -1 };
}

function coopIdExists(coops, coopId) {
  return coops.some(cp => cp.id === coopId);
}

// --------------- DISPLAY (TABLE) ---------------
function showAll(coops) {
  console.log("\n==============================");
  console.log("       FARM INFORMATION       ");
  console.log("==============================");

  if (coops.length === 0) {
    console.log("No chicken coops found.");
    return;
  }

  for (const coop of coops) {
    console.log(`\nCOOP #${coop.id}`);
    console.log("------------------------------------------------------------");
    console.log(`${pad("ID", 5)} ${pad("NAME", 15)} ${pad("COLOR", 12)} ${pad("AGE", 5)} ${pad("MOLTING", 10)}`);
    console.log("------------------------------------------------------------");

    if (coop.chickens.length === 0) {
      console.log("(No chickens in this coop)");
    } else {
      const sorted = [...coop.chickens].sort((a, b) => a.id - b.id);
      for (const c of sorted) {
        console.log(`${pad(c.id, 5)} ${pad(c.name, 15)} ${pad(c.color, 12)} ${pad(c.age, 5)} ${pad(String(c.is_molting), 10)}`);
      }
    }
    console.log("------------------------------------------------------------");
  }
}

function pad(value, width) {
  const s = String(value);
  return s.length >= width ? s.slice(0, width) : s + " ".repeat(width - s.length);
}

// --------------- READLINE PROMPTS --------------
function rl() {
  return readline.createInterface({ input: process.stdin, output: process.stdout });
}

function ask(question) {
  const r = rl();
  return new Promise(resolve => {
    r.question(question, answer => {
      r.close();
      resolve(answer);
    });
  });
}

async function askInt(question) {
  const ans = (await ask(question)).trim();
  const n = Number(ans);
  if (!Number.isInteger(n)) throw new Error("Invalid integer input.");
  return n;
}

async function askYesNo(question) {
  const ans = (await ask(question)).trim().toLowerCase();
  if (ans === "y") return true;
  if (ans === "n") return false;
  throw new Error("Invalid input (y/n).");
}

// --------------- MENU ACTIONS -----------------
async function addCoopFlow(coops) {
  console.log("\n=== ADD NEW COOP ===");
  try {
    const coopId = await askInt("Enter new coop ID: ");
    if (coopIdExists(coops, coopId)) {
      console.log("A coop with that ID already exists.");
      return;
    }
    coops.push(new ChickenCoop(coopId));
    saveToJson(coops);
    console.log(`Coop #${coopId} created.`);
  } catch {
    console.log("Invalid input. Try again.");
  }
}

async function addChickenFlow(coops) {
  console.log("\n=== ADD NEW CHICKEN ===");
  try {
    const coopId = await askInt("Enter coop ID: ");
    const coop = findCoop(coops, coopId);
    if (!coop) {
      console.log("Coop not found.");
      return;
    }
    const name = (await ask("Enter chicken name: ")).trim();
    const color = (await ask("Enter color: ")).trim();
    const age = await askInt("Enter age: ");
    let isMolting;
    try {
      isMolting = await askYesNo("Is it molting? (y/n): ");
    } catch {
      console.log("Invalid input. Try again.");
      return;
    }

    const newId = nextChickenId(coops);
    coop.add(new Chicken(newId, name, color, age, isMolting));
    saveToJson(coops);
    console.log(`Chicken '${name}' added to Coop #${coopId} with ID ${newId}.`);
  } catch {
    console.log("Invalid input. Try again.");
  }
}

async function removeChickenFlow(coops) {
  console.log("\n=== REMOVE CHICKEN ===");
  try {
    const chickenId = await askInt("Enter chicken ID to remove: ");
    const { coop, index } = findChicken(coops, chickenId);
    if (!coop) {
      console.log("Chicken not found.");
      return;
    }
    coop.chickens.splice(index, 1);
    saveToJson(coops);
    console.log(`Chicken ID ${chickenId} removed from Coop #${coop.id}.`);
  } catch {
    console.log("Invalid input. Try again.");
  }
}

async function editChickenFlow(coops) {
  console.log("\n=== EDIT CHICKEN ===");
  try {
    const chickenId = await askInt("Enter chicken ID to edit: ");
    const find = findChicken(coops, chickenId);
    const coop = find.coop;
    const ch = find.chicken;
    const index = find.index;

    if (!ch) {
      console.log("Chicken not found.");
      return;
    }

    console.log(`\nEditing Chicken (ID=${ch.id})`);
    console.log(`Current: Name=${ch.name}, Color=${ch.color}, Age=${ch.age}, Molting=${ch.is_molting}`);
    console.log("\nSelect field to edit:");
    console.log("1. Name");
    console.log("2. Color");
    console.log("3. Age");
    console.log("4. Molting status");
    console.log("5. Move to another coop");
    console.log("6. Cancel");
    const choice = (await ask("Option: ")).trim();

    if (choice === "1") {
      ch.name = (await ask("New name: ")).trim();
    } else if (choice === "2") {
      ch.color = (await ask("New color: ")).trim();
    } else if (choice === "3") {
      ch.age = await askInt("New age: ");
    } else if (choice === "4") {
      ch.is_molting = await askYesNo("Is it molting? (y/n): ");
    } else if (choice === "5") {
      const newCoopId = await askInt("Enter target coop ID: ");
      const target = findCoop(coops, newCoopId);
      if (!target) {
        console.log("Target coop not found.");
        return;
      }
      // move chicken
      coop.chickens.splice(index, 1);
      target.add(ch);
      console.log(`Chicken moved to Coop #${newCoopId}.`);
    } else if (choice === "6") {
      console.log("Edit cancelled.");
      return;
    } else {
      console.log("Invalid option.");
      return;
    }

    saveToJson(coops);
    console.log("Changes saved.");
  } catch {
    console.log("Invalid input. Try again.");
  }
}

// --------------- MAIN LOOP --------------------
async function main() {
  let coops = loadFromJson();

  // Seed initial data if empty
  if (coops.length === 0) {
    const coop1 = new ChickenCoop(1);
    const coop2 = new ChickenCoop(2);
    coop1.add(new Chicken(1, "Lucy", "White", 2, false));
    coop1.add(new Chicken(2, "Maruja", "Brown", 1, true));
    coop2.add(new Chicken(3, "Pio", "Black", 3, false));
    coops = [coop1, coop2];
    saveToJson(coops);
  }

  while (true) {
    console.log("\n==============================");
    console.log("        CHICKEN FARM MENU     ");
    console.log("==============================");
    console.log("1. View all coops and chickens");
    console.log("2. Add coop");
    console.log("3. Add chicken");
    console.log("4. Remove chicken");
    console.log("5. Edit chicken");
    console.log("6. Exit");

    const option = (await ask("Choose an option: ")).trim();

    if (option === "1") showAll(coops);
    else if (option === "2") await addCoopFlow(coops);
    else if (option === "3") await addChickenFlow(coops);
    else if (option === "4") await removeChickenFlow(coops);
    else if (option === "5") await editChickenFlow(coops);
    else if (option === "6") { console.log("Exiting Chicken Farm Simulator."); break; }
    else console.log("Invalid option. Please choose 1–6.");
  }
}

main().catch(() => {
  console.log("Unexpected error. Exiting.");
});
