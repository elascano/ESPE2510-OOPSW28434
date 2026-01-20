import Inventory from "../model/Inventory.js";
import Product from "../model/Product.js";
import ConfigurationStock from "../model/ConfigurationStock.js";
import StockNotifier from "../service/StockNotifier.js";
import JsonUtil from "../util/JsonUtil.js";

export default class ProductController {
    constructor() {
        this.inventory = new Inventory();
        this.notifier = StockNotifier;

        const products = JsonUtil.loadProducts();
        if (products) {
            products.forEach(p =>
                this.inventory.addProduct(new Product(p.name, p.stock))
            );
        }
    }

    addProduct(name, quantity) {
        this.inventory.addProduct(new Product(name, quantity));
        JsonUtil.saveProducts(this.inventory.getProducts());
    }

    sellProduct(index, quantity) {
        const product = this.inventory.getProducts()[index];
        product.sell(quantity);

        if (product.stock <= ConfigurationStock.getMinimumStock()) {
            this.notifier.alertLowStock(product.name, product.stock);
        }

        JsonUtil.saveProducts(this.inventory.getProducts());
    }

    restockProduct(index, quantity) {
        this.inventory.restockProduct(index, quantity);
        JsonUtil.saveProducts(this.inventory.getProducts());
    }

    getProducts() {
        return this.inventory.getProducts();
    }
}
