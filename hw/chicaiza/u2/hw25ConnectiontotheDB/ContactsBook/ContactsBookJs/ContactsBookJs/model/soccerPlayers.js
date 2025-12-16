const { getDb } = require("../utils/mongodbConnection");
const JsonManager = require("../utils/jsonManager");

class SoccerPlayers {
    constructor(id, name,tshirt, age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.tshirt = tshirt;
    }


    async save() {
        const db = getDb();

        // Guardar en MongoDB
        await db.collection("SoccerPlayers").insertOne(this);

        // Guardar también en JSON mediante el utilitario
        JsonManager.saveSoccerPlayers(this);
    }

    static async getAll() {
        const db = getDb();
        return await db.collection("SoccerPlayers").find().toArray();
    }
}

module.exports = SoccerPlayers ;
