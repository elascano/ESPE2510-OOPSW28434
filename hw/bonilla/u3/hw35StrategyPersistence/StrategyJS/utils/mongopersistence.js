import MongoConnection from './mongoconnection.js';
import Persistence from './persistence.js';
import Parking from '../model/parking.js';

export class MongoPersistence extends Persistence {
    constructor() {
        super();
        this.collection = null;
    }

    async init() {
        const conn = await MongoConnection.getInstance();
        this.collection = conn.getCollection('Parking');
    }

    async create(parking) {
        await this.collection.insertOne({
            id: parking.id,
            plate: parking.plate,
            vehicleType: parking.vehicleType,
            entryTime: parking.entryTime.toISOString(),
            exitTime: parking.exitTime ? parking.exitTime.toISOString() : '',
            fee: parking.fee
        });
        return true;
    }

    async read() {
        const docs = await this.collection.find({}).toArray();
        return docs.map(d => new Parking(
            d.id,
            d.plate,
            d.vehicleType,
            d.entryTime,
            d.exitTime || null,
            d.fee
        ));
    }

    async update(id, parking) {
        await this.collection.replaceOne(
            { id },
            {
                id: parking.id,
                plate: parking.plate,
                vehicleType: parking.vehicleType,
                entryTime: parking.entryTime.toISOString(),
                exitTime: parking.exitTime ? parking.exitTime.toISOString() : '',
                fee: parking.fee
            }
        );
        return true;
    }

    async delete(id) {
        await this.collection.deleteOne({ id });
        return true;
    }

    async find(id) {
        const doc = await this.collection.findOne({ id });
        if (!doc) return null;
        return new Parking(
            doc.id,
            doc.plate,
            doc.vehicleType,
            doc.entryTime,
            doc.exitTime || null,
            doc.fee
        );
    }
}