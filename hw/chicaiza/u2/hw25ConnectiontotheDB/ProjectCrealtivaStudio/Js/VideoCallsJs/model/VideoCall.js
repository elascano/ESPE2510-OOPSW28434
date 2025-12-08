const { getDb } = require("../utils/mongodbConnection");

class VideoCall {
    constructor(customerId, videoCallDate, hour, medium, note) {
        this.customerId = customerId;
        this.videoCallDate = videoCallDate;
        this.hour = hour;
        this.medium = medium;
        this.note = note;
    }

    async save() {
        const db = getDb();
        if (!db) throw new Error("No hay conexión a la base de datos");

        const collection = db.collection("VideoCallsJavaScript");
        await collection.insertOne({
            customerId: this.customerId,
            videoCallDate: this.videoCallDate,
            hour: this.hour,
            medium: this.medium,
            note: this.note
        });
    }

    static async getAll() {
        const db = getDb();
        if (!db) throw new Error("No hay conexión a la base de datos");

        const collection = db.collection("VideoCallsJavaScript");
        const calls = await collection.find({}).toArray();
        return calls;
    }
}

module.exports = VideoCall;
