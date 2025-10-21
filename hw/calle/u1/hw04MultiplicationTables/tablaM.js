const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

console.log("Welcome to Multiplication Tables!");

function askTable() {
    rl.question("Which table do you want? (1-12): ", (answer) => {
        let number = parseInt(answer);
        
        if (number >= 1 && number <= 12) {
            console.log("\nTable of " + number + ":");
            for (let i = 1; i <= 12; i++) {
                let result = number * i;
                console.log(number + " x " + i + " = " + result);
            }
            
            rl.question("\nDo you want another table? (yes/no): ", (answer2) => {
                if (answer2.toLowerCase() === 'yes') {
                    askTable();
                } else {
                    console.log("Thanks for using the program!");
                    rl.close();
                }
            });
        } else {
            console.log("Please enter a number between 1 and 12.");
            askTable();
        }
    });
}

askTable();