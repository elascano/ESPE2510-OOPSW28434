const SoldThing = require('../models/soldThing');
const DiscountSingleton = require('../models/DiscountSingleton');

class SalesController {
  constructor() {
    this.sales = [];
    this.discountSingleton = DiscountSingleton.getInstance();
  }

  createSale(itemName, price) {
    try {
      const soldItem = new SoldThing(itemName, price);
      this.sales.push(soldItem);
      return soldItem;
    } catch (error) {
      throw new Error(`Failed to create sale: ${error.message}`);
    }
  }

  getAllSales() {
    return [...this.sales];
  }

  getSalesSummary() {
    return this.sales.map(sale => sale.toJSON());
  }

  updateDiscountPercentage(newDiscount) {
    try {
      return this.discountSingleton.setDiscountPercentage(newDiscount);
    } catch (error) {
      throw new Error(`Failed to update discount: ${error.message}`);
    }
  }

  getCurrentDiscount() {
    return this.discountSingleton.getDiscountPercentage();
  }

  clearSales() {
    this.sales = [];
    return this.sales.length === 0;
  }

  getSalesStatistics() {
    const summary = this.getSalesSummary();
    
    if (summary.length === 0) {
      return {
        totalSales: 0,
        totalRevenue: 0,
        totalDiscount: 0,
        averageDiscount: 0
      };
    }

    const totalRevenue = summary.reduce((sum, sale) => sum + sale.finalPrice, 0);
    const totalDiscount = summary.reduce((sum, sale) => sum + sale.discountAmount, 0);
    const averageDiscount = totalDiscount / summary.length;

    return {
      totalSales: summary.length,
      totalRevenue: parseFloat(totalRevenue.toFixed(2)),
      totalDiscount: parseFloat(totalDiscount.toFixed(2)),
      averageDiscount: parseFloat(averageDiscount.toFixed(2))
    };
  }
}

module.exports = SalesController;