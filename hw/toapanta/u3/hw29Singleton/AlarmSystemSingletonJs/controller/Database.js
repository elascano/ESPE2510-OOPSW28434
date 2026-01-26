const { MongoClient } = require('mongodb');

class Database {
    static #instance = null;
    static #db = null;
    static #uri = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/?retryWrites=true&w=majority&family=4";

    static async getDatabase() {
        if (!this.#instance) {
            this.#instance = new MongoClient(this.#uri);
            await this.#instance.connect();
            this.#db = this.#instance.db("Singleton");
        }
        return this.#db;
    }
}

module.exports = Database;