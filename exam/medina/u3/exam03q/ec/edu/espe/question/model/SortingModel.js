import SortingContext from './SortingContext.js';


class SortingModel {
  constructor() {
    this.sortingContext = new SortingContext();
    this.lastResult = null;
  }


  parseInput(inputString) {
    const elements = inputString.split(',').map(element => {
      const num = parseFloat(element.trim());
      if (isNaN(num)) {
        throw new Error(`Invalid input: "${element.trim()}" is not a number`);
      }
      return num;
    });

    if (elements.length < 2) {
      throw new Error('Array must contain at least 2 elements');
    }

    return elements;
  }


  sort(inputString) {
    const unsortedArray = this.parseInput(inputString);
    const arraySize = unsortedArray.length;

    this.sortingContext.selectStrategy(arraySize);
    const sortedArray = this.sortingContext.executeSort(unsortedArray);

    this.lastResult = {
      unsorted: unsortedArray.join(', '),
      size: arraySize,
      sortAlgorithm: this.sortingContext.getStrategyName(),
      sorted: sortedArray.join(', ')
    };

    return this.lastResult;
  }


  getLastResult() {
    return this.lastResult;
  }

  async saveToDatabase(result) {
    try {
      const response = await fetch('/api/sorting-results', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          unsorted: result.unsorted,
          size: result.size,
          sortAlgorithm: result.sortAlgorithm,
          sorted: result.sorted
        })
      });

      if (!response.ok) {
        throw new Error(`Failed to save to database: ${response.statusText}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Database save error:', error);
      throw error;
    }
  }
}

export default SortingModel;
