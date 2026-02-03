const readline = require('readline-sync');
const CellphoneController = require('./controller/CellphoneController');
const MongoUpdateStrategy = require('./controller/MongoUpdateStrategy');
const IdGenerator = require('./utils/IdGenerator');
const ConsoleView = require('./view/ConsoleView');

async function main() {
    const strategy = new MongoUpdateStrategy();
    const controller = new CellphoneController(strategy);

    while (true) {
        const opt = ConsoleView.showMenu();

        if (opt === '1') {
            await ConsoleView.handleCreate(controller, IdGenerator);
        } else if (opt === '2') {
            const id = readline.question("Enter ID: ");
            const phone = await controller.findById(id);
            phone ? console.table([phone]) : console.log("Not found.");
        } else if (opt === '3') {
            const all = await controller.findAll();
            console.table(all);
            const sub = readline.question("U (Update) / D (Delete) / B (Back): ").toUpperCase();
            if (sub === 'D') {
                const id = readline.question("ID to delete: ");
                await controller.delete(id);
            } else if (sub === 'U') {
                const id = readline.question("ID: ");
                const model = readline.question("New Model: ");
                const price = readline.question("New Price: ");
                await controller.update({ id, model, price: parseFloat(price) });
            }
        } else if (opt === '4') {
            process.exit();
        }
    }
}

main();