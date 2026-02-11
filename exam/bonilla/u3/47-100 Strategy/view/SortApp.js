const readline = require("readline")
const SortingContext = require("../model/SortingContext")
const MongoConnection = require("../utils/MongoConnection")

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

rl.question("Enter a list of numbers (separated by commas): ", async (input) => {
    try {
        let numbers = input.split(",").map(n => Number(n.trim()))
        if (numbers.length <= 1 || numbers.some(isNaN)) {
            throw new Error("Invalid input")
        }

        const context = new SortingContext()
        const result = context.sort(numbers)

        const db = await MongoConnection.connect()
        const collection = db.collection("arrayArelis")

        const document = {
            unsorted: numbers,
            size: numbers.length,
            sort: result.algorithm,
            sorted: result.sorted
        }

        await collection.insertOne(document)

        console.table([{
            Unsorted: numbers.join(", "),
            Size: numbers.length,
            "Sort Algorithm": result.algorithm,
            Sorted: result.sorted.join(", ")
        }])
    } catch (e) {
        console.log("Error:", e.message)
    } finally {
        rl.close()
        process.exit(0)
    }
})