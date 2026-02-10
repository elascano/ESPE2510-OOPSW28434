class QuickSort {
  execute(array) {
    return this.quickSort([...array], 0, array.length - 1);
  }

  quickSort(arr, low, high) {
    if (low < high) {
      const partitionIndex = this.partition(arr, low, high);
      this.quickSort(arr, low, partitionIndex - 1);
      this.quickSort(arr, partitionIndex + 1, high);
    }
    return arr;
  }

  partition(arr, low, high) {
    const pivot = arr[high];
    let i = low - 1;

    for (let j = low; j < high; j++) {
      if (arr[j] < pivot) {
        i++;
        [arr[i], arr[j]] = [arr[j], arr[i]];
      }
    }

    [arr[i + 1], arr[high]] = [arr[high], arr[i + 1]];
    return i + 1;
  }

  getName() {
    return 'QuickSort';
  }
}

export default QuickSort;
