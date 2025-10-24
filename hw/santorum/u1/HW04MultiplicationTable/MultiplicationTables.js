const readline = require("readline");

function runProgram() {
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });
 console.log("    Choose a number between one and twelve");
  rl.question("    Which number's table do you want? ", (input) => {
    const number = parseInt(input);

    console.log("\n-  -  -  -  -  -  -  -  -  -  -  -  -  -");
    console.log(`       Multiplication table of ${number}       `);
    console.log("-  -  -  -  -  -  -  -  -  -  -  -  -  -\n");

    for (let i = 1; i <= 12; i++) {
      console.log(` ♦   ${number} x ${i} = ${number * i}`);
    }

    console.log("\n-  -  -  -  -  -  -  -  -  -  -  -  -  -");

    rl.question("\nDo you want to use the program again? [y / n] ", (choice) => {
      rl.close();
      if (choice.toLowerCase() === 'y' || choice.toLowerCase() === 'yes') {
        console.log("\n");
        runProgram();
      } else {
        console.log("\nBye, bye!");
        process.exit(0);
      }
    });
  });
}

runProgram();
