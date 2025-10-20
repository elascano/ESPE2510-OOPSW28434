const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

console.log("Welcome to Mathews Pastor's Multiplication Table");
askForNumber();

function askForNumber() {
    rl.question("Enter the number that said you were viewing the table: ", (userAnswer) => {
        const userNumber = parseInt(userAnswer);
        if(isNaN(userNumber)) {
            console.log("Invalid number entered. Please enter again.");
            askForNumber();
        } else {
            calculateMultiplicationTable(userNumber);
            wantsToContinue();
        }
    });
}

function wantsToContinue() {
    rl.question("¿Do you want to continue? (\"y\" for YES/any other letter not to):", (continueAnswer)=>{
        const userResponse = continueAnswer.toLocaleLowerCase();
        if(userResponse === 'y'|| userResponse === 'Y'){
            askForNumber();
        }else{
            console.log("Thank you for using the multiplication table program. Goodbye!");
            rl.close();
        }
    });
}

function calculateMultiplicationTable(number) {
    console.log(`---Multiplication Table for ${number}---`);
    for (let i = 1; i <= 12; i++) {
        const result = number * i;
        console.log(`${number} x ${i} = ${result}`);
        }
}









