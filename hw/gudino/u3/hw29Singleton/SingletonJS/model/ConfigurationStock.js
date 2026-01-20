class ConfigurationStock {
    constructor() {
        this.minimumStock = 10;
    }

    getMinimumStock() {
        return this.minimumStock;
    }

    setMinimumStock(value) {
        this.minimumStock = value;
    }
}

const instance = new ConfigurationStock();
export default instance;
