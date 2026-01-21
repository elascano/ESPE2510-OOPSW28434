import { MongoClient } from "mongodb";
import { Product } from "../Model/Product.js";
import { AlarmConfig } from "../Config/AlarmConfig.js";

export class StockAlertController {
    constructor() {
        this.client = new MongoClient("mongodb+srv://Joseph:Joseph1751774793@cluster0.h8pi0ir.mongodb.net/?appName=Cluster0");
    }

    async init() {
        await this.client.connect();
        this.db = this.client.db("SingletonDB");
        this.productsCollection = this.db.collection("products");
        this.config = await AlarmConfig.getInstance();
    }

    async checkStock() {
        const min = this.config.getMinimumStock();
        const cursor = this.productsCollection.find();

        const lowStockProducts = [];

        for await (const doc of cursor) {
            const product = new Product(doc._id, doc.name, doc.stock);
            if (product.stock <= min) {
                lowStockProducts.push(product);
            }
        }

        return lowStockProducts;
    }

    async updateMinimumStock(newValue) {
        await this.config.updateMinimumStock(newValue);
    }
}
