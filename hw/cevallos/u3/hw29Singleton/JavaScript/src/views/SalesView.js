class SalesView {
  constructor() {
    this.initializeElements();
    this.setupEventListeners();
    this.initializeView();
  }

  initializeElements() {
    // Form elements
    this.discountInput = document.getElementById('discountPercentage');
    this.applyDiscountBtn = document.getElementById('applyDiscountBtn');
    this.currentDiscountSpan = document.getElementById('currentDiscount');
    
    this.itemNameInput = document.getElementById('itemName');
    this.itemPriceInput = document.getElementById('itemPrice');
    this.createSaleBtn = document.getElementById('createSaleBtn');
    
    // Table elements
    this.salesTableBody = document.getElementById('salesTableBody');
    
    // Statistics elements
    this.totalSalesSpan = document.getElementById('totalSales');
    this.totalRevenueSpan = document.getElementById('totalRevenue');
    this.totalDiscountSpan = document.getElementById('totalDiscount');
    this.averageDiscountSpan = document.getElementById('averageDiscount');
    
    // Message elements
    this.errorMessageDiv = document.getElementById('errorMessage');
    this.successMessageDiv = document.getElementById('successMessage');
    
    // Clear button
    this.clearSalesBtn = document.getElementById('clearSalesBtn');
  }

  setupEventListeners() {
    this.applyDiscountBtn.addEventListener('click', () => this.handleApplyDiscount());
    this.createSaleBtn.addEventListener('click', () => this.handleCreateSale());
    this.clearSalesBtn.addEventListener('click', () => this.handleClearSales());
    
    // Allow Enter key to submit forms
    this.itemNameInput.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') this.handleCreateSale();
    });
    
    this.itemPriceInput.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') this.handleCreateSale();
    });
    
    this.discountInput.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') this.handleApplyDiscount();
    });
  }

  async initializeView() {
    try {
      const currentDiscount = await window.salesAPI.getCurrentDiscount();
      this.updateCurrentDiscount(currentDiscount);
      this.discountInput.value = currentDiscount;
      await this.updateSalesTable();
    } catch (error) {
      this.showMessage(`Error initializing view: ${error.message}`, 'error');
    }
  }

  async handleApplyDiscount() {
    try {
      const discountValue = parseFloat(this.discountInput.value);
      
      if (isNaN(discountValue) || discountValue < 0) {
        throw new Error('Please enter a valid non-negative number for discount.');
      }
      
      if (discountValue > 100) {
        throw new Error('Discount cannot exceed 100%.');
      }
      
      const result = await window.salesAPI.updateDiscount(discountValue);
      
      if (result.success) {
        this.updateCurrentDiscount(discountValue);
        await this.updateSalesTable();
        this.showMessage(`Discount successfully updated to ${discountValue}%`, 'success');
      } else {
        throw new Error(result.error || 'Failed to update discount. Please try again.');
      }
    } catch (error) {
      this.showMessage(error.message, 'error');
    }
  }

  async handleCreateSale() {
    try {
      const itemName = this.itemNameInput.value.trim();
      const priceStr = this.itemPriceInput.value.trim();
      
      if (!itemName) {
        throw new Error('Please enter an item name.');
      }
      
      if (!priceStr) {
        throw new Error('Please enter a price.');
      }
      
      const price = parseFloat(priceStr);
      
      if (isNaN(price) || price <= 0) {
        throw new Error('Price must be a positive number.');
      }
      
      const result = await window.salesAPI.createSale(itemName, price);
      
      if (result.success) {
        // Clear input fields
        this.itemNameInput.value = '';
        this.itemPriceInput.value = '';
        
        // Update UI
        await this.updateSalesTable();
        this.showMessage(
          `Sale created successfully: ${result.sale.name} for $${result.sale.originalPrice.toFixed(2)}`, 
          'success'
        );
        
        // Focus on item name for next entry
        this.itemNameInput.focus();
      } else {
        throw new Error(result.error || 'Failed to create sale.');
      }
    } catch (error) {
      this.showMessage(error.message, 'error');
    }
  }

  async handleClearSales() {
    if (confirm('Are you sure you want to clear all sales? This action cannot be undone.')) {
      try {
        const result = await window.salesAPI.clearSales();
        if (result.success) {
          await this.updateSalesTable();
          this.showMessage('All sales have been cleared.', 'success');
        }
      } catch (error) {
        this.showMessage(`Error clearing sales: ${error.message}`, 'error');
      }
    }
  }

  updateCurrentDiscount(discount) {
    this.currentDiscountSpan.textContent = discount.toFixed(1);
  }

  async updateSalesTable() {
    try {
      const data = await window.salesAPI.getSales();
      const sales = data.sales;
      const stats = data.stats;
      
      // Update table
      this.salesTableBody.innerHTML = '';
      
      if (sales.length === 0) {
        const emptyRow = document.createElement('tr');
        emptyRow.innerHTML = `
          <td colspan="7" style="text-align: center; padding: 2rem; color: #718096;">
            No sales yet. Create your first sale above!
          </td>
        `;
        this.salesTableBody.appendChild(emptyRow);
      } else {
        sales.forEach(sale => {
          const row = document.createElement('tr');
          
          const saleDate = new Date(sale.saleDate);
          const formattedDate = saleDate.toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
          });
          
          row.innerHTML = `
            <td>${sale.id}</td>
            <td>${sale.name}</td>
            <td>$${sale.originalPrice.toFixed(2)}</td>
            <td>${sale.discountPercentage.toFixed(1)}%</td>
            <td>$${sale.discountAmount.toFixed(2)}</td>
            <td><strong>$${sale.finalPrice.toFixed(2)}</strong></td>
            <td>${formattedDate}</td>
          `;
          
          this.salesTableBody.appendChild(row);
        });
      }
      
      // Update statistics
      this.updateStatistics(stats);
    } catch (error) {
      this.showMessage(`Error loading sales: ${error.message}`, 'error');
    }
  }

  updateStatistics(stats) {
    this.totalSalesSpan.textContent = stats.totalSales;
    this.totalRevenueSpan.textContent = stats.totalRevenue.toFixed(2);
    this.totalDiscountSpan.textContent = stats.totalDiscount.toFixed(2);
    this.averageDiscountSpan.textContent = stats.averageDiscount.toFixed(2);
  }

  showMessage(message, type = 'error') {
    // Hide both messages first
    this.errorMessageDiv.classList.remove('message-visible');
    this.successMessageDiv.classList.remove('message-visible');
    
    // Show the appropriate message
    if (type === 'error') {
      this.errorMessageDiv.textContent = message;
      this.errorMessageDiv.classList.add('message-visible');
    } else {
      this.successMessageDiv.textContent = message;
      this.successMessageDiv.classList.add('message-visible');
    }
    
    // Auto-hide success messages after 3 seconds
    if (type === 'success') {
      setTimeout(() => {
        this.successMessageDiv.classList.remove('message-visible');
      }, 3000);
    }
  }
}

// Initialize the view when the DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
  window.salesView = new SalesView();
});