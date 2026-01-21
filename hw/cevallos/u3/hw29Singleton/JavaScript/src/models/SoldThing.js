const DiscountSingleton = require('./DiscountSingleton');

class SoldThing {
  constructor(name, originalPrice, saleDate = null, saleId = null) {
    if (!name || typeof name !== 'string') {
      throw new Error('Name must be a non-empty string.');
    }
    
    if (typeof originalPrice !== 'number' || originalPrice <= 0) {
      throw new Error('Price must be a positive number.');
    }
    
    this.name = name;
    this.originalPrice = originalPrice;
    this.saleDate = saleDate || new Date();
    this.saleId = saleId || this._generateSaleId();
  }

  _generateSaleId() {
    const timestamp = this.saleDate.toISOString().replace(/[-:.]/g, '').slice(0, 15);
    return `SALE-${timestamp}`;
  }

  calculateFinalPrice() {
    const discountSingleton = DiscountSingleton.getInstance();
    return discountSingleton.calculateDiscountedPrice(this.originalPrice);
  }

  getDiscountPercentage() {
    const discountSingleton = DiscountSingleton.getInstance();
    return discountSingleton.getDiscountPercentage();
  }

  getDiscountAmount() {
    const discountSingleton = DiscountSingleton.getInstance();
    const discountPercentage = discountSingleton.getDiscountPercentage();
    return this.originalPrice * (discountPercentage / 100);
  }

  toJSON() {
    return {
      id: this.saleId,
      name: this.name,
      originalPrice: this.originalPrice,
      discountPercentage: this.getDiscountPercentage(),
      discountAmount: this.getDiscountAmount(),
      finalPrice: this.calculateFinalPrice(),
      saleDate: this.saleDate.toISOString()
    };
  }
}

module.exports = SoldThing;