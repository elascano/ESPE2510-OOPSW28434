import { MongoClient, ObjectId } from "mongodb";

export class AlarmConfig {
    static instance = null;

    constructor() {
        if (AlarmConfig.instance) {
            throw new Error("Use getInstance()");
        }
    }

    static async getInstance() {
        if (!AlarmConfig.instance) {
            const config = new AlarmConfig();
            await config.#init();
            AlarmConfig.instance = config;
        }
        return AlarmConfig.instance;
    }

    async #init() {
        this.client = new MongoClient("mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/?appName=Cluster0");
        await this.client.connect();

        this.db = this.client.db("SingletonDB");
        this.collection = this.db.collection("sales_configuration");

        const doc = await this.collection.findOne();

        if (!doc) {
            const result = await this.collection.insertOne({ minimumStock: 10 });
            this.id = result.insertedId;
            this.minimumStock = 10;
        } else {
            this.id = doc._id;
            this.minimumStock = doc.minimumStock;
        }
    }

    getMinimumStock() {
        return this.minimumStock;
    }

    async updateMinimumStock(newStock) {
        this.minimumStock = newStock;
        await this.collection.updateOne(
            { _id: this.id },
            { $set: { minimumStock: newStock } }
        );
    }
}
