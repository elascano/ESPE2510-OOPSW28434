import readline from "readline";

class EventView {

    constructor() {
        this.controller = null;
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
    }

    showMenu() {
        console.log("\n--- EVENT SYSTEM ---");
        console.log("1. Change discount");
        console.log("2. Calculate event price");
        console.log("0. Exit");

        this.rl.question("Select an option: ", option => {
            this.handleOption(option);
        });
    }

    handleOption(option) {
        switch (option) {
            case "1":
                this.rl.question("Enter new discount (%): ", value => {
                    this.controller.changeDiscount(parseFloat(value));
                    this.showMenu();
                });
                break;

            case "2":
                this.rl.question("Enter event name: ", name => {
                    this.rl.question("Enter event price: ", price => {
                        this.controller.calculateEventPrice(
                            name,
                            parseFloat(price)
                        );
                        this.showMenu();
                    });
                });
                break;

            case "0":
                console.log("Exiting the system...");
                this.rl.close();
                break;

            default:
                console.log("Invalid option");
                this.showMenu();
        }
    }

    showEvent(event, finalPrice, discount) {
        console.log("\nEvent:", event.name);
        console.log("Original price:", event.price);
        console.log("Applied discount:", discount + "%");
        console.log("Final price:", finalPrice);
    }

    showMessage(message) {
        console.log(message);
    }
}

export default EventView;
