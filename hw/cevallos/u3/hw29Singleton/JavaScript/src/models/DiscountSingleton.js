const fs = require('fs');
const path = require('path');

class DiscountSingleton {
  constructor() {
    if (DiscountSingleton.instance) {
      return DiscountSingleton.instance;
    }
    
    this._configFile = path.join(__dirname, '../config/settings.json');
    this._discountPercentage = 0.0;
    this._initializeDiscount();
    
    DiscountSingleton.instance = this;
  }

  _initializeDiscount() {
    try {
      if (fs.existsSync(this._configFile)) {
        const configData = fs.readFileSync(this._configFile, 'utf8');
        const config = JSON.parse(configData);
        this._discountPercentage = parseFloat(config.discount_percentage) || 0.0;
      } else {
        // Create default config file
        this._saveConfig();
      }
    } catch (error) {
      console.error(`Error loading configuration: ${error.message}. Using default discount 0%.`);
      this._discountPercentage = 0.0;
    }
  }

  _saveConfig() {
    try {
      const configDir = path.dirname(this._configFile);
      if (!fs.existsSync(configDir)) {
        fs.mkdirSync(configDir, { recursive: true });
      }
      
      const config = {
        discount_percentage: this._discountPercentage
      };
      
      fs.writeFileSync(this._configFile, JSON.stringify(config, null, 2), 'utf8');
      return true;
    } catch (error) {
      console.error(`Error saving configuration: ${error.message}`);
      return false;
    }
  }

  getDiscountPercentage() {
    return this._discountPercentage;
  }

  setDiscountPercentage(newDiscount) {
    if (typeof newDiscount !== 'number' || newDiscount < 0 || isNaN(newDiscount)) {
      throw new Error('Discount must be a non-negative number.');
    }
    
    this._discountPercentage = newDiscount;
    return this._saveConfig();
  }

  calculateDiscountedPrice(originalPrice) {
    if (typeof originalPrice !== 'number' || originalPrice < 0) {
      throw new Error('Price must be a non-negative number.');
    }
    
    const discountAmount = originalPrice * (this._discountPercentage / 100);
    return Math.max(0, originalPrice - discountAmount);
  }

  static getInstance() {
    if (!DiscountSingleton.instance) {
      DiscountSingleton.instance = new DiscountSingleton();
    }
    return DiscountSingleton.instance;
  }
}

module.exports = DiscountSingleton;