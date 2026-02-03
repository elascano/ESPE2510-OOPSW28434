const readline = require('readline-sync');
const { validModel, validPrice } = require('../utils/Validators');

class ConsoleView {
    static showMenu() {
        console.log("\n--- CELLPHONE STORE ---");
        console.log("1. Create Cellphone");
        console.log("2. Find Cellphone");
        console.log("3. Management (Update/Delete)");
        console.log("4. Exit");
        return readline.question("Select an option: ");
    }

    static async handleCreate(controller, idGen) {
        console.log(`\nNext ID: ${idGen.previewId()}`);
        const model = readline.question("Model: ");
        if (!validModel(model)) return console.log("Error: Invalid model");

        const priceStr = readline.question("Price: ");
        if (!validPrice(priceStr)) return console.log("Error: Invalid price");

        const id = idGen.generateId();
        await controller.create({ id, model, price: parseFloat(priceStr) });
        console.log("Saved successfully!");
    }
}
module.exports = ConsoleView;