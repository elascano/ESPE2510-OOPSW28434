const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function askQuestion(query) {
    return new Promise(resolve => rl.question(query, answer => resolve(answer)));
}

(async function main() {
    while (true) {
        console.log("=============================================");
        console.log("         MULTIPLICATION TABLE MAKER         ");
        console.log("=============================================");

        let numberInput = await askQuestion("\nEnter a number to see its multiplication table: ");
        let number = parseInt(numberInput);

        console.log(`\nMultiplication Table of ${number}\n` + "    -".repeat(4));

        //Show multiplication table
        for (let i = 1; i <= 12; i++) {
            let result = number * i;
            console.log(`    | ${number.toString().padStart(2)} x ${i.toString().padStart(2)} = ${result.toString().padStart(4)} |`);
        }

        console.log("    -".repeat(4));

        let option;
        while (true) {
            option = (await askQuestion("\nDo you want to continue? (Y = YES / N = NO): ")).trim().toLowerCase();
            if (option === "y" || option === "n") break;
            console.log("Invalid option! Please enter Y or N.");
        }

        if (option === "n") {
            console.log("Thanks for using the program!");
            break;
        }

        console.log("\n");
    }

    rl.close();
})();