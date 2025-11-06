import fs from "fs";
import readline from "readline";

const DATA_FILE = "data.json";
let coops = [];

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

// === FUNCIONES AUXILIARES ===
function saveData() {
  fs.writeFileSync(DATA_FILE, JSON.stringify(coops, null, 2));
}

function loadData() {
  if (fs.existsSync(DATA_FILE)) {
    coops = JSON.parse(fs.readFileSync(DATA_FILE));
  }
}

function printTable(data) {
  if (data.length === 0) {
    console.log("No chickens available.\n");
    return;
  }

  const headers = ["ID", "Name", "Color", "Age", "Molting"];
  const rows = data.map(chicken => ({
    id: chicken.id,
    name: chicken.name,
    color: chicken.color,
    age: chicken.age,
    molting: chicken.molting ? "Yes" : "No",
  }));

  const colWidths = headers.map(h =>
    Math.max(h.length, ...rows.map(r => String(r[h.toLowerCase()]).length))
  );

  const formatRow = row =>
    headers.map((h, i) => String(row[h.toLowerCase()]).padEnd(colWidths[i])).join(" | ");

  console.log(formatRow(Object.fromEntries(headers.map(h => [h.toLowerCase(), h]))));
  console.log(colWidths.map(w => "-".repeat(w)).join("-+-"));
  rows.forEach(row => console.log(formatRow(row)));
  console.log();
}

// === FUNCIONES PRINCIPALES ===
function addCoop() {
  rl.question("Enter coop ID: ", coopId => {
    if (coops.some(c => c.id === coopId)) {
      console.log("A coop with that ID already exists.\n");
      return mainMenu();
    }

    coops.push({ id: coopId, chickens: [] });
    saveData();
    console.log(`Coop ${coopId} added!\n`);
    mainMenu();
  });
}

function addChicken() {
  if (coops.length === 0) {
    console.log("You must create a coop first.\n");
    return mainMenu();
  }

  console.log("Available coops:");
  coops.forEach(c => console.log(`- Coop ${c.id}`));

  rl.question("Enter coop ID where to add the chicken: ", coopId => {
    const coop = coops.find(c => c.id === coopId);
    if (!coop) {
      console.log("Coop not found.\n");
      return mainMenu();
    }

    rl.question("Enter chicken ID: ", id => {
      if (coop.chickens.some(ch => ch.id === id)) {
        console.log("A chicken with that ID already exists in this coop.\n");
        return mainMenu();
      }

      rl.question("Enter name: ", name => {
        rl.question("Enter color: ", color => {
          rl.question("Enter age: ", age => {
            rl.question("Is molting? (y/n): ", molting => {
              coop.chickens.push({
                id,
                name,
                color,
                age: parseInt(age),
                molting: molting.toLowerCase() === "y",
              });
              saveData();
              console.log("Chicken added!\n");
              mainMenu();
            });
          });
        });
      });
    });
  });
}

function viewChickens() {
  loadData();
  if (coops.length === 0) {
    console.log("No coops available.\n");
    return mainMenu();
  }

  for (const coop of coops) {
    console.log(`\n=== Coop ${coop.id} ===`);
    printTable(coop.chickens);
  }
  mainMenu();
}

function editChicken() {
  rl.question("Enter coop ID: ", coopId => {
    const coop = coops.find(c => c.id === coopId);
    if (!coop) {
      console.log("Coop not found.\n");
      return mainMenu();
    }

    rl.question("Enter chicken ID to edit: ", id => {
      const chicken = coop.chickens.find(c => c.id === id);
      if (!chicken) {
        console.log("Chicken not found.\n");
        return mainMenu();
      }

      rl.question(`Name (${chicken.name}): `, name => {
        rl.question(`Color (${chicken.color}): `, color => {
          rl.question(`Age (${chicken.age}): `, age => {
            rl.question("Molting (y/n): ", molting => {
              if (name) chicken.name = name;
              if (color) chicken.color = color;
              if (age) chicken.age = parseInt(age);
              if (molting) chicken.molting = molting.toLowerCase() === "y";

              saveData();
              console.log("Chicken updated!\n");
              mainMenu();
            });
          });
        });
      });
    });
  });
}

function deleteChicken() {
  rl.question("Enter coop ID: ", coopId => {
    const coop = coops.find(c => c.id === coopId);
    if (!coop) {
      console.log("Coop not found.\n");
      return mainMenu();
    }

    rl.question("Enter chicken ID to delete: ", id => {
      const index = coop.chickens.findIndex(c => c.id === id);
      if (index === -1) {
        console.log("Chicken not found.\n");
      } else {
        const removed = coop.chickens.splice(index, 1);
        saveData();
        console.log(`Chicken '${removed[0].name}' deleted.\n`);
      }
      mainMenu();
    });
  });
}

function findChicken() {
  rl.question("Enter chicken ID to find: ", id => {
    const found = [];

    for (const coop of coops) {
      const matches = coop.chickens.filter(c => c.id === id);
      if (matches.length > 0) {
        found.push({ coopId: coop.id, chickens: matches });
      }
    }

    if (found.length === 0) {
      console.log("Chicken not found.\n");
    } else {
      for (const f of found) {
        console.log(`\nFound in Coop ${f.coopId}:`);
        printTable(f.chickens);
      }
    }
    mainMenu();
  });
}

// === MENÚ PRINCIPAL ===
function mainMenu() {
  console.log("=== Chicken Farm Simulator ===");
  console.log("1. Add coop");
  console.log("2. Add chicken");
  console.log("3. View chickens");
  console.log("4. Edit chicken");
  console.log("5. Delete chicken");
  console.log("6. Find chicken");
  console.log("0. Exit");

  rl.question("Choose an option: ", opt => {
    switch (opt) {
      case "1": addCoop(); break;
      case "2": addChicken(); break;
      case "3": viewChickens(); break;
      case "4": editChicken(); break;
      case "5": deleteChicken(); break;
      case "6": findChicken(); break;
      case "0": console.log("Goodbye!"); rl.close(); break;
      default: console.log("Invalid option.\n"); mainMenu();
    }
  });
}

// === INICIO ===
loadData();
mainMenu();
