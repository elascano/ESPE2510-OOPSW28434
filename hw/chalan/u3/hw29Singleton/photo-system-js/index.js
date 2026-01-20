const readline = require("readline");
const PhotographerController = require("./controller/PhotographerController");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const controller = new PhotographerController();

function showMenu() {
    console.log("\n=== PHOTO SYSTEM ===");
    console.log("1. Add Photographer");
    console.log("2. Exit");

    rl.question("Choose an option: ", option => {
        switch (option) {
            case "1":
                addPhotographer();
                break;
            case "2":
                console.log("Goodbye!");
                rl.close();
                break;
            default:
                console.log("Invalid option");
                showMenu();
        }
    });
}

function addPhotographer() {
    rl.question("Name: ", name => {
        rl.question("Specialty: ", specialty => {
            rl.question("Experience (years): ", experience => {
                rl.question("Hourly rate: ", rate => {
                    try {
                        controller.register(
                            name,
                            specialty,
                            experience,
                            rate
                        );
                        console.log("Photographer added successfully!");
                    } catch (error) {
                        console.log("Error:", error.message);
                    }
                    showMenu();
                });
            });
        });
    });
}

showMenu();
