const Event = require('../model/Event');
const StrategyFactory = require('../factory/StrategyFactory');

class EventController {
    constructor(storageType = 'json') {
        this.storageType = storageType;
        this.strategy = StrategyFactory.createStrategy(storageType);
    }

    setStorageStrategy(storageType) {
        this.storageType = storageType;
        this.strategy = StrategyFactory.createStrategy(storageType);
        console.log(`Estrategia cambiada a: ${storageType}`);
    }

    async addEvent(id, name, date) {
        try {
            const event = new Event();
            event.id = id;
            event.name = name;
            event.date = date;
            
            const success = await this.strategy.addEvent(event);
            return { success, event };
        } catch (error) {
            return { success: false, error: error.message };
        }
    }

    async updateEvent(id, name, date) {
        try {
            const event = new Event();
            event.id = id;
            event.name = name;
            event.date = date;
            
            const success = await this.strategy.updateEvent(event);
            return { success, event };
        } catch (error) {
            return { success: false, error: error.message };
        }
    }

    async deleteEvent(id) {
        try {
            const success = await this.strategy.deleteEvent(id);
            return { success };
        } catch (error) {
            return { success: false, error: error.message };
        }
    }

    async readEvent(id) {
        try {
            const event = await this.strategy.readEvent(id);
            return { success: !!event, event };
        } catch (error) {
            return { success: false, error: error.message };
        }
    }

    async getAllEvents() {
        try {
            const events = await this.strategy.getAllEvents();
            return { success: true, events };
        } catch (error) {
            return { success: false, error: error.message, events: [] };
        }
    }

    createEventObject(id, name, date) {
        try {
            const event = new Event();
            event.id = id;
            event.name = name;
            event.date = date;
            return { success: true, event };
        } catch (error) {
            return { success: false, error: error.message };
        }
    }
}

module.exports = EventController;