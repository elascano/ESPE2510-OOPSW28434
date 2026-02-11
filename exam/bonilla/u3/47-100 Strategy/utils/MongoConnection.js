const { MongoClient } = require("mongodb")

class MongoConnection {
    static async connect() {
        if (!this.client) {
            this.client = new MongoClient("mongodb+srv://Arelis:Arelis2006@cluster0.qdn4zsf.mongodb.net/")
            await this.client.connect()
        }
        return this.client.db("strategyBonilla")
    }
}

module.exports = MongoConnection