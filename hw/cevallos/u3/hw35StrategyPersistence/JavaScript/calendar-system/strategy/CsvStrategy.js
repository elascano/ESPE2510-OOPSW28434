const fs = require('fs').promises;
const path = require('path');
const { parse, stringify } = require('csv/sync');
const StorageStrategy = require('./StorageStrategy');
const Event = require('../model/Event');

class CsvStrategy extends StorageStrategy {
    constructor(filePath = 'events.csv') {
        super();
        this.filePath = path.join(__dirname, '..', 'data', filePath);
        this.initFile();
    }

    async initFile() {
        try {
            await fs.access(this.filePath);
        } catch (error) {
            // Si el archivo no existe, crearlo con encabezados
            await fs.mkdir(path.dirname(this.filePath), { recursive: true });
            const headers = stringify([['id', 'name', 'date']]);
            await fs.writeFile(this.filePath, headers);
        }
    }

    async _readFile() {
        try {
            const data = await fs.readFile(this.filePath, 'utf-8');
            const records = parse(data, {
                columns: true,
                skip_empty_lines: true
            });
            return records;
        } catch (error) {
            return [];
        }
    }

    async _writeFile(data) {
        try {
            const csv = stringify(data, { header: true });
            await fs.writeFile(this.filePath, csv);
            return true;
        } catch (error) {
            console.error('Error escribiendo archivo CSV:', error.message);
            return false;
        }
    }

    async addEvent(event) {
        try {
            const events = await this._readFile();
            events.push(event.toObject());
            return await this._writeFile(events);
        } catch (error) {
            console.error('Error agregando evento:', error.message);
            return false;
        }
    }

    async updateEvent(event) {
        try {
            const events = await this._readFile();
            const index = events.findIndex(e => e.id === event.id);
            
            if (index !== -1) {
                events[index] = event.toObject();
                return await this._writeFile(events);
            }
            return false;
        } catch (error) {
            console.error('Error actualizando evento:', error.message);
            return false;
        }
    }

    async deleteEvent(id) {
        try {
            const events = await this._readFile();
            const filteredEvents = events.filter(e => e.id !== id);
            
            if (filteredEvents.length !== events.length) {
                return await this._writeFile(filteredEvents);
            }
            return false;
        } catch (error) {
            console.error('Error eliminando evento:', error.message);
            return false;
        }
    }

    async readEvent(id) {
        try {
            const events = await this._readFile();
            const eventData = events.find(e => e.id === id);
            return eventData ? Event.fromObject(eventData) : null;
        } catch (error) {
            console.error('Error leyendo evento:', error.message);
            return null;
        }
    }

    async getAllEvents() {
        try {
            const events = await this._readFile();
            return events.map(eventData => Event.fromObject(eventData));
        } catch (error) {
            console.error('Error obteniendo todos los eventos:', error.message);
            return [];
        }
    }
}

module.exports = CsvStrategy;