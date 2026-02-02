export class Customer {
    constructor(id = 0, name = "", apartmentNumber = "", email = "", phone = "") {
        this.id = id;
        this.name = name;
        this.apartmentNumber = apartmentNumber;
        this.email = email;
        this.phone = phone;
    }

    toString() {
        return `ID: ${this.id} | Name: ${this.name} | Apartment: ${this.apartmentNumber} | Email: ${this.email} | Phone: ${this.phone}`;
    }

    toJson() {
        return {
            id: this.id,
            name: this.name,
            apartmentNumber: this.apartmentNumber,
            email: this.email,
            phone: this.phone
        };
    }

    toCsv() {
        return `${this.id},${this.name},${this.apartmentNumber},${this.email},${this.phone}`;
    }

    static fromJson(data) {
        return new Customer(
            data.id || 0,
            data.name || "",
            data.apartmentNumber || "",
            data.email || "",
            data.phone || ""
        );
    }

    static fromCsv(line) {
        const parts = line.trim().split(",");
        if (parts.length === 5) {
            return new Customer(
                parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                parts[4]
            );
        }
        return null;
    }
}