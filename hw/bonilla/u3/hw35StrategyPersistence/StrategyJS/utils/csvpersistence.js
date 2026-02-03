import fs from 'fs';
import path from 'path';
import Parking from '../model/parking.js';
import Persistence from './persistence.js';

export class CsvPersistence extends Persistence {
    constructor(fileName = 'data/parking.csv') {
        super();
        this.fileName = fileName;
        if (!fs.existsSync(path.dirname(fileName))) fs.mkdirSync(path.dirname(fileName), { recursive: true });
        if (!fs.existsSync(fileName)) fs.writeFileSync(fileName, 'id,plate,vehicleType,entryTime,exitTime,fee\n', 'utf-8');
    }

    create(parking) {
        const list = this.read();
        list.push(parking);
        return this.save(list);
    }

    read() {
        try {
            const data = fs.readFileSync(this.fileName, 'utf-8');
            const lines = data.split('\n').slice(1);
            return lines.filter(l => l.trim() !== '').map(line => {
                const [id, plate, vehicleType, entryTime, exitTime, fee] = line.split(',');
                return new Parking(id, plate, vehicleType, entryTime, exitTime || null, parseFloat(fee));
            });
        } catch (err) {
            console.error('Error reading CSV:', err);
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
            const data = ['id,plate,vehicleType,entryTime,exitTime,fee'];
            for (const p of list) {
                data.push(`${p.id},${p.plate},${p.vehicleType},${p.entryTime.toISOString()},${p.exitTime ? p.exitTime.toISOString() : ''},${p.fee}`);
            }
            fs.writeFileSync(this.fileName, data.join('\n'), 'utf-8');
            return true;
        } catch (err) {
            console.error('Error saving CSV:', err);
            return false;
        }
    }
}