const readline = require("readline");

function showTable(number) {
  console.log("\n" + "-".repeat(31));
  console.log(`   Multiplication Table of ${number}`.padStart(22 + Math.floor(number / 10)));
  console.log("-".repeat(31));
  for (let i = 1; i <= 12; i++) {
    console.log(`${String(number).padStart(2)} x ${String(i).padStart(2)} = ${String(number * i).padStart(3)}`);
  }
  console.log("-".repeat(31) + "\n");
}

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function askForNumber(callback) {
  rl.question("Enter the number of the table you want to see: ", (input) => {
    const number = parseInt(input);

    if (isNaN(number)) {
      console.log("\nError: You must enter a valid number (no letters or symbols).\n");
      return askForNumber(callback);
    }

    if (number < 1 || number > 12) {
      console.log("\nError: Please choose a number between 1 and 12.\n");
      return askForNumber(callback);
    }

    showTable(number);
    callback(); 
  });
}

function askToContinue() {
  rl.question("Do you want to see another table? (yes/no): ", (answer) => {
    const response = answer.trim().toLowerCase();
    if (response === "yes" || response === "y") {
      console.log();
      askForNumber(askToContinue);
    } else {
      console.log("\nThank you for using the program.\n");
      rl.close();
    }
  });
}

function main() {
  console.log("----- Welcome to the Multiplication Table Program -----");
  console.log("You can choose a table from 1 to 12.\n");
  askForNumber(askToContinue);
}

main();