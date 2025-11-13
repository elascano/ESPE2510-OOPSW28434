
const readline = require("readline");

const input = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

console.log(" Welcome to the Multiplication Tables \n");

function askTable() {
  input.question(" Which multiplication table would you like to see? (1–12): ", (answer) => {
    const number = parseInt(answer);

    if (isNaN(number) || number < 1 || number > 12) {
      console.log(" Invalid input. Please enter a number between 1 and 12.\n");
      askTable();
      return;
    }

    printTable(number);
    askAgain();
  });
}

function askAgain() {
  input.question("Do you want to see another table? (1 = Yes, 0 = No): ", (response) => {
    const decision = parseInt(response);

    if (decision === 1) {
      console.log();
      askTable();
    } else if (decision === 0) {
      console.log("\nGoodbye! Thanks for using the program.");
      input.close();
    } else {
      console.log("Invalid option. Enter 1 or 0.\n");
      askAgain();
    }
  });
}

function printTable(number) {
  console.log("+" + "-".repeat(34) + "+");
  console.log(`|  Multiplication Table of ${number.toString().padStart(2, " ")} |`);
  console.log("+" + "-".repeat(34) + "+");

  for (let i = 1; i <= 12; i++) {
    const result = number * i;
    console.log(`|   ${number.toString().padStart(2, " ")} x ${i.toString().padStart(2, " ")} = ${result.toString().padStart(3, " ")}               |`);
  }

  console.log("+" + "-".repeat(34) + "+\n");
}

askTable();
