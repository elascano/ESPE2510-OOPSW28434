const BubbleSort = require("./BubbleSort")
const InsertionSort = require("./InsertionSort")
const QuickSort = require("./QuickSort")

class SortingContext {
    setStrategy(size) {
        if (size >= 2 && size <= 6) return new BubbleSort()
        if (size >= 7 && size <= 10) return new InsertionSort()
        if (size >= 11) return new QuickSort()
        throw new Error("Invalid array size")
    }

    sort(data) {
        let strategy = this.setStrategy(data.length)
        return { 
            algorithm: strategy.constructor.name, 
            sorted: strategy.sort(data) 
        }
    }
}

module.exports = SortingContext