import SortingModel from '../model/SortingModel.js';


class SortingController {
  constructor() {
    this.model = new SortingModel();
    this.initializeEventListeners();
  }

  initializeEventListeners() {
    const form = document.getElementById('sortingForm');
    form.addEventListener('submit', (event) => this.handleFormSubmit(event));
  }

  async handleFormSubmit(event) {
    event.preventDefault();
    this.clearMessages();
    this.showLoadingIndicator(true);

    try {
      const inputElements = document.getElementById('inputElements').value.trim();

      if (!inputElements) {
        throw new Error('Please enter elements to sort');
      }


      const result = this.model.sort(inputElements);


      await this.model.saveToDatabase(result);


      this.displayResults(result);
      this.showSuccessMessage('Sorting completed and data saved successfully!');


      document.getElementById('inputElements').value = '';
    } catch (error) {
      this.showErrorMessage(error.message);
      console.error('Sorting error:', error);
    } finally {
      this.showLoadingIndicator(false);
    }
  }

  displayResults(result) {
    document.getElementById('unsortedResult').textContent = result.unsorted;
    document.getElementById('sizeResult').textContent = result.size;
    document.getElementById('algorithmResult').textContent = result.sortAlgorithm;
    document.getElementById('sortedResult').textContent = result.sorted;

    document.getElementById('resultsContainer').classList.add('show');
  }

  showErrorMessage(message) {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = `Error: ${message}`;
    errorDiv.classList.add('show');
  }

  showSuccessMessage(message) {
    const successDiv = document.getElementById('successMessage');
    successDiv.textContent = message;
    successDiv.classList.add('show');

    setTimeout(() => {
      successDiv.classList.remove('show');
    }, 5000);
  }

  clearMessages() {
    document.getElementById('errorMessage').classList.remove('show');
    document.getElementById('successMessage').classList.remove('show');
  }

  showLoadingIndicator(show) {
    const loadingDiv = document.getElementById('loadingIndicator');
    if (show) {
      loadingDiv.classList.add('show');
    } else {
      loadingDiv.classList.remove('show');
    }
  }
}

document.addEventListener('DOMContentLoaded', () => {
  new SortingController();
});

export default SortingController;
