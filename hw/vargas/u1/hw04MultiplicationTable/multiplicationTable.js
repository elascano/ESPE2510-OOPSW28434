const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function askNumberTable() {
  rl.question("Enter a number from 1 to 12 to see its multiplication table: ", (input) => {
    const number = parseInt(input);
    if (isNaN(number) || number < 1 || number > 12) {
      console.log("Please enter a valid number in range of 1 and 12.");
      return askNumberTable();
    }

    console.log("===================================");
    console.log(`\nMultiplication table of number: ${number}`);
    for (let i = 1; i <= 12; i++) {
      const result = number * i;
      const numStr = String(number).padStart(2, " ");
      const iStr = String(i).padStart(2, " ");
      const resStr = String(result).padStart(3, " ");
      console.log(`|| ${numStr} x ${iStr} = ${resStr} ||`);
    }
    console.log("===================================");

    rl.question("Do you want to see another multiplication table? (y/n): ", (again) => {
      if (again.trim().toLowerCase() === "y") {
        askNumberTable();
      } else {
        rl.close();
      }
    });
  });
}
askNumberTable();
