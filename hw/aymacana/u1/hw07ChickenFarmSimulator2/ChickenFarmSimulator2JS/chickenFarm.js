const Chicken = require('./chicken');

function getRandomChoice(array) {
    return array[Math.floor(Math.random() * array.length)];
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function main() {
    console.log("Welcome to the Chicken Farm Simulator\n");

    const colors = ["White", "Brown", "Black", "Yellow", "Gray"];
    const names = ["Lucy", "Maruja", "Rosita", "Clara", "Pepe"];

    const chicken1 = new Chicken(
        1,
        getRandomChoice(names),
        getRandomChoice(colors),
        getRandomInt(1, 5),
        getRandomChoice([true, false])
    );

    const chicken2 = new Chicken(
        2,
        getRandomChoice(names),
        getRandomChoice(colors),
        getRandomInt(1, 5),
        getRandomChoice([true, false])
    );

    const chicken3 = new Chicken(
        3,
        getRandomChoice(names),
        getRandomChoice(colors),
        getRandomInt(1, 5),
        getRandomChoice([true, false])
    );

    const readline = require('readline').createInterface({
        input: process.stdin,
        output: process.stdout
    });

    function showMenu() {
        console.log("\n--- Chicken Farm Menu ---");
        console.log("1. View chicken characteristics");
        console.log("2. Make a chicken do its routine");
        console.log("3. Exit");

        readline.question("Choose an option (1-3): ", (option) => {
            if (option === "1") {
                console.log("\nWhich chicken do you want to see?");
                console.log(`1. ${chicken1.name}`);
                console.log(`2. ${chicken2.name}`);
                console.log(`3. ${chicken3.name}`);

                readline.question("Enter the number of the chicken: ", (choice) => {
                    if (choice === "1") {
                        console.log(chicken1.toString());
                    } else if (choice === "2") {
                        console.log(chicken2.toString());
                    } else if (choice === "3") {
                        console.log(chicken3.toString());
                    } else {
                        console.log("Invalid option.");
                    }
                    showMenu();
                });

            } else if (option === "2") {
                console.log("\nWhich chicken should do its routine?");
                console.log(`1. ${chicken1.name}`);
                console.log(`2. ${chicken2.name}`);
                console.log(`3. ${chicken3.name}`);

                readline.question("Enter the number of the chicken: ", (choice) => {
                    if (choice === "1") {
                        chicken1.doStuff();
                    } else if (choice === "2") {
                        chicken2.doStuff();
                    } else if (choice === "3") {
                        chicken3.doStuff();
                    } else {
                        console.log("Invalid option.");
                    }
                    showMenu();
                });

            } else if (option === "3") {
                console.log("Exiting...");
                readline.close();
            } else {
                console.log("Please choose a valid option (1-3).");
                showMenu();
            }
        });
    }

    showMenu();
}

if (require.main === module) {
    main();
}