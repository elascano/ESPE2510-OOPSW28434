class Event {
    constructor(id = '', name = '', date = '') {
        this._id = id;
        this._name = name;
        this._date = date;
    }

    get id() {
        return this._id;
    }

    set id(value) {
        this._id = value;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        // Validar que solo contenga letras y espacios
        if (value && !/^[a-zA-Z\s]+$/.test(value)) {
            throw new Error('El nombre solo puede contener letras y espacios');
        }
        this._name = value;
    }

    get date() {
        return this._date;
    }

    set date(value) {
        // Validar formato de fecha (YYYY-MM-DD)
        if (value && !/^\d{4}-\d{2}-\d{2}$/.test(value)) {
            throw new Error('Formato de fecha inválido. Use YYYY-MM-DD');
        }
        this._date = value;
    }

    toObject() {
        return {
            id: this._id,
            name: this._name,
            date: this._date
        };
    }

    static fromObject(obj) {
        const event = new Event();
        event.id = obj.id;
        event.name = obj.name;
        event.date = obj.date;
        return event;
    }

    toString() {
        return `Evento{id='${this._id}', nombre='${this._name}', fecha='${this._date}'}`;
    }
}

module.exports = Event;