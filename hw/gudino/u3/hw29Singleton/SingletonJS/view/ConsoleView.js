import readline from "readline";

export default class ConsoleView {
    constructor(controller) {
        this.controller = controller;
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
    }

    start() {
        console.log("\nStock Control System");
        this.showMenu();
    }

    showMenu() {
        console.log(`
1. Add product
2. Sell product
3. Show products
4. Exit
        `);

        this.rl.question("Choose an option: ", option => {
            switch (option) {
                case "1":
                    this.addProduct();
                    break;
                case "2":
                    this.sellProduct();
                    break;
                case "3":
                    this.showProducts();
                    break;
                case "4":
                    this.rl.close();
                    break;
                default:
                    this.showMenu();
            }
        });
    }

    addProduct() {
        this.rl.question("Product name: ", name => {
            this.rl.question("Initial stock: ", stock => {
                this.controller.addProduct(name, Number(stock));
                console.log("Product added\n");
                this.showMenu();
            });
        });
    }

    sellProduct() {
        this.showProducts(false);
        this.rl.question("Product index: ", index => {
            this.rl.question("Quantity to sell: ", qty => {
                this.controller.sellProduct(Number(index), Number(qty));
                console.log("Sale completed\n");
                this.showMenu();
            });
        });
    }

    showProducts(backToMenu = true) {
        const products = this.controller.getProducts();

        console.log("\nCurrent products:");
        products.forEach((p, i) => {
            console.log(`${i}. ${p.name} - Stock: ${p.stock}`);
        });
        console.log("");

        if (backToMenu) this.showMenu();
    }
}
