import { SortStrategy } from "./SortStrategy.js";

// Concrete Strategy: Quick Sort
export class QuickSort extends SortStrategy {
    sort(data) {
        if (data.length <= 1) return data;

        const pivot = data[Math.floor(data.length / 2)];
        const left = data.filter(x => x < pivot);
        const middle = data.filter(x => x === pivot);
        const right = data.filter(x => x > pivot);

        return [
            ...this.sort(left),
            ...middle,
            ...this.sort(right)
        ];
    }
}
