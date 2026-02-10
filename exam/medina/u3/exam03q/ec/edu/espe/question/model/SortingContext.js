import BubbleSort from './strategies/BubbleSort.js';
import InsertionSort from './strategies/InsertionSort.js';
import QuickSort from './strategies/QuickSort.js';

class SortingContext {
  constructor() {
    this.bubbleSort = new BubbleSort();
    this.insertionSort = new InsertionSort();
    this.quickSort = new QuickSort();
    this.selectedStrategy = null;
  }


  selectStrategy(arraySize) {
    if (arraySize < 2) {
      throw new Error('Array size must be greater than 1');
    }

    if (arraySize >= 2 && arraySize <= 6) {
      this.selectedStrategy = this.bubbleSort;
    } else if (arraySize >= 7 && arraySize <= 10) {
      this.selectedStrategy = this.insertionSort;
    } else if (arraySize > 10) {
      this.selectedStrategy = this.quickSort;
    }

    return this.selectedStrategy;
  }

  executeSort(array) {
    if (!this.selectedStrategy) {
      this.selectStrategy(array.length);
    }
    return this.selectedStrategy.execute(array);
  }


  getStrategyName() {
    return this.selectedStrategy ? this.selectedStrategy.getName() : null;
  }
}

export default SortingContext;
