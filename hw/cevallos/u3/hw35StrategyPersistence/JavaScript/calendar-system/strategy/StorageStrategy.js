class StorageStrategy {
    async addEvent(event) {
        throw new Error('Método addEvent debe ser implementado');
    }

    async updateEvent(event) {
        throw new Error('Método updateEvent debe ser implementado');
    }

    async deleteEvent(id) {
        throw new Error('Método deleteEvent debe ser implementado');
    }

    async readEvent(id) {
        throw new Error('Método readEvent debe ser implementado');
    }

    async getAllEvents() {
        throw new Error('Método getAllEvents debe ser implementado');
    }
}

module.exports = StorageStrategy;