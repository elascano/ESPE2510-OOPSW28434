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
    console.log("2. List Photographers");
    console.log("3. Hire Photographer (Calculate)"); 
    console.log("4. Exit");

    rl.question("Choose an option: ", async (option) => {
        switch (option) {
            case "1":
                addPhotographer();
                break;
            case "2":
                await listPhotographers();
                break;
            case "3":
                await hirePhotographer();
                break;
            case "4":
                console.log("Goodbye!");
                rl.close();
                process.exit(0);
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
            rl.question("Experience: ", experience => {
                rl.question("Hourly rate: ", rate => {
                    (async () => {
                        try {
                            await controller.register(name, specialty, experience, rate);
                            console.log("Saved!");
                        } catch (e) {
                            console.log("Error:", e.message);
                        }
                        showMenu();
                    })();
                });
            });
        });
    });
}

async function listPhotographers() {
    console.log("\n--- LIST ---");
    const list = await controller.getPhotographers();
    if (list.length === 0) console.log("Empty list.");
    else console.table(list.map(p => ({ Name: p.name, Rate: p.hourlyRate })));
    showMenu();
}


async function hirePhotographer() {
    rl.question("\nEnter Photographer Name to hire: ", async (name) => {
        
        
        const photographer = await controller.findPhotographer(name);

        if (!photographer) {
            console.log("Error: Photographer not found!");
            showMenu();
            return;
        }

        console.log(`FOUND: ${photographer.name} | Rate: $${photographer.hourlyRate}/hr`);

        
        rl.question("Enter hours (1-5): ", (hoursInput) => {
            const hours = parseInt(hoursInput);

            if (isNaN(hours) || hours < 1 || hours > 5) {
                console.log("Error: Hours must be between 1 and 5.");
            } else {
                
                const total = photographer.hourlyRate * hours;
                
                console.log("\n--- CONTRACT SUMMARY ---");
                console.log(`Photographer: ${photographer.name}`);
                console.log(`Rate: $${photographer.hourlyRate}`);
                console.log(`Hours: ${hours}`);
                console.log(`TOTAL TO PAY: $${total}`);
                console.log("------------------------");
            }
            showMenu();
        });
    });
}

showMenu();