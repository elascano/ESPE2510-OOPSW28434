const readline = require("readline");

const interface = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function showMultiplicationTable(number) {
  console.log(`\nMultiplication Table ${number}`);
  for (let i = 1; i <= 10; i++) {
    console.log(`${number} x ${i} = ${number * i}`);
  }
}

function askForNumber() {
  interface.question("\nEnter a number from 1 to 10: ", (answer) => {
    const number = parseInt(answer);

    if (!isNaN(number) && number >= 1 && number <= 10) {
      showMultiplicationTable(number);
      askForAnotherTable();
    } else {
      console.log("Error, Please enter a number between 1 and 10.");
      askForNumber();
    }
  });
}

function askForAnotherTable() {
  interface.question("Do you want to see another table? (y/n): ", (option) => {
    if (option.trim().toLowerCase() === "y") {
      askForNumber();
    } else {
      console.log("\nGoodbye thanks");
      interface.close();
    }
  });
}

askForNumber();

