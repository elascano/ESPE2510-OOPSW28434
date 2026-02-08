import { BubbleSort } from "../model/BubbleSort.js";
import { InsertionSort } from "../model/InsertionSort.js";
import { QuickSort } from "../model/QuickSort.js";

export class SortContext {
    constructor(data) {
        this.data = data;
        this.strategy = this.chooseStrategy();
    }

    chooseStrategy() {
        const size = this.data.length;

        if (size >= 2 && size <= 5) {
            return new BubbleSort();
        }
        if (size >= 6 && size <= 10) {
            return new InsertionSort();
        }
        if (size > 10) {
            return new QuickSort();
        }

        throw new Error("Array must have more than 1 element");
    }

    sort() {
        // STRATEGY PATTERN APPLIED HERE
        // The context delegates the sorting algorithm
        // to the selected strategy at runtime
        return this.strategy.sort(this.data);
    }
}
