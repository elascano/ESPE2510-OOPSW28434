import fs from 'fs';
import path from 'path';
import Parking from '../model/parking.js';
import Persistence from './persistence.js';

export class JsonPersistence extends Persistence {
    constructor(fileName = 'data/parking.json') {
        super();
        this.fileName = fileName;
        if (!fs.existsSync(path.dirname(fileName))) fs.mkdirSync(path.dirname(fileName), { recursive: true });
        if (!fs.existsSync(fileName)) fs.writeFileSync(fileName, '[]', 'utf-8');
    }

    create(parking) {
        const list = this.read();
        list.push(parking);
        return this.save(list);
    }

    read() {
        try {
            const data = fs.readFileSync(this.fileName, 'utf-8');
            const rawList = JSON.parse(data);
            return rawList.map(p => new Parking(
                p.id,
                p.plate,
                p.vehicleType,
                p.entryTime,
                p.exitTime,
                p.fee
            ));
        } catch (err) {
            console.error('Error reading JSON:', err);
            return [];
        }
    }

    update(id, parking) {
        const list = this.read();
        const index = list.findIndex(p => p.id === id);
        if (index === -1) return false;
        list[index] = parking;
        return this.save(list);
    }

    delete(id) {
        let list = this.read();
        list = list.filter(p => p.id !== id);
        return this.save(list);
    }

    find(id) {
        return this.read().find(p => p.id === id) || null;
    }

    save(list) {
        try {
            fs.writeFileSync(this.fileName, JSON.stringify(list, null, 2), 'utf-8');
            return true;
        } catch (err) {
            console.error('Error saving JSON:', err);
            return false;
        }
    }
}