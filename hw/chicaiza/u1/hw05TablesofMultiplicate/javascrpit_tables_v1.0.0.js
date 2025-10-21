const readline = require('readline-sync');

while (true) {
    let tableNumber = parseInt(readline.question("Enter the number of the multiplication table you want (1 to 12): "));

    if (tableNumber >= 1 && tableNumber <= 12) {
        console.log(`\nTable of ${tableNumber}`);
        console.log("-".repeat(15));
        for (let i = 1; i <= 12; i++) {
            console.log(`${tableNumber} x ${i} = ${tableNumber * i}`);
        }
    } else {
        console.log("Invalid number. Please enter a number between 1 and 12.");
        continue;
    }

    let continueChoice = readline.question("\nDo you want to generate another table? (y/n): ").toLowerCase();
    if (continueChoice !== "y") {
        console.log("Thank you! Program finished.");
        break;
    }
}