const { MongoClient } = require("mongodb");
const ProductRepository = require("./ProductRepository");
const Product = require("../models/Product");

class MongoProductRepository extends ProductRepository {
    constructor(
        uri = "mongodb://localhost:27017",
        dbName = "oop",
        collectionName = "products"
    ) {
        super();
        this.client = new MongoClient(uri);
        this.dbName = dbName;
        this.collectionName = collectionName;
    }

    async connect() {
        if (!this.client.topology?.isConnected()) {
            await this.client.connect();
            this.db = this.client.db(this.dbName);
            this.collection = this.db.collection(this.collectionName);
        }
    }

    async save(product) {
        await this.connect();
        await this.collection.insertOne(product.toDocument());
    }

    async getAll() {
        await this.connect();
        const docs = await this.collection.find().toArray();

        return docs.map(d =>
            new Product(
                d.name,
                d.make,
                d.base_price,
                d.final_price
            )
        );
    }

    async getTotalSum() {
        await this.connect();
        const result = await this.collection.aggregate([
            { $group: { _id: null, total: { $sum: "$final_price" } } }
        ]).toArray();

        return result.length ? result[0].total : 0;
    }
}

module.exports = MongoProductRepository;
