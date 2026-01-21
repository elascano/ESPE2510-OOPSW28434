
export class DateModel {
    constructor(day = 0, month = 0, year = 0, hour = 0, minute = 0) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    toString() {
        const pad = (num) => String(num).padStart(2, '0');
        const yearStr = String(this.year).padStart(4, '0');
        return `${yearStr}-${pad(this.month)}-${pad(this.day)} @ ${pad(this.hour)}:${pad(this.minute)}`;
    }

    isBefore(other) {
        if (this.year !== other.year) return this.year < other.year;
        if (this.month !== other.month) return this.month < other.month;
        if (this.day !== other.day) return this.day < other.day;
        if (this.hour !== other.hour) return this.hour < other.hour;
        return this.minute < other.minute;
    }
}