class StockNotifier {
    alertLowStock(product, stock) {
        console.warn(`Low stock: ${product} (${stock})`);
    }
}

const instance = new StockNotifier();
export default instance;
