class Task {
    constructor(name, dueDate) {
        this._name = name;
        this._dueDate = new Date(dueDate); // YYYY-MM-DD
    }

    getName() {
        return this._name;
    }

    getDueDate() {
        return this._dueDate;
    }

    getRemainingDays() {
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        const diffTime = this._dueDate - today;
        return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    }

    toString() {
        return `${this._name} - Vence en ${this.getRemainingDays()} días`;
    }
}

module.exports = Task;
