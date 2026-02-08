import readline from "readline";
import { SortContext } from "./controller/SortContext.js";
import { saveResult } from "./database/mongo.js";

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

async function main() {
    rl.question("Ingresa los números separados por coma: ", async (input) => {

        const data = input.split(",").map(Number);

        const context = new SortContext([...data]);
        const sortedData = context.sort();

        const result = {
            unsorted: data.join(","),
            size: data.length,
            algorithm: context.strategy.constructor.name,
            sorted: sortedData.join(","),
            date: new Date()
        };

        console.log("Unsorted:", result.unsorted);
        console.log("Size:", result.size);
        console.log("Algorithm:", result.algorithm);
        console.log("Sorted:", result.sorted);

        await saveResult(result);

        rl.close();
    });
}

main();
