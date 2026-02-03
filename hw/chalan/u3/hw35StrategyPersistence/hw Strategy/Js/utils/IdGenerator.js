class IdGenerator {
    static _counter = 0;

    static previewId() {
        const now = new Date();
        const datePart = now.getFullYear().toString() + (now.getMonth() + 1).toString().padStart(2, '0');
        const numberPart = String(IdGenerator._counter).padStart(2, '0');
        return datePart + numberPart;
    }

    static generateId() {
        if (IdGenerator._counter > 99) throw new Error("Limit reached");
        const id = this.previewId();
        IdGenerator._counter++;
        return id;
    }
}
module.exports = IdGenerator;