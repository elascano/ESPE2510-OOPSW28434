export class ValidationController {
    static validateName(name) {
        const nameRegex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;
        return name && nameRegex.test(name);
    }

    static validateApartmentNumber(apartmentNumber) {
        const apartmentRegex = /^[a-zA-Z0-9\-]+$/;
        return apartmentNumber && apartmentRegex.test(apartmentNumber);
    }

    static validateEmail(email) {
        const emailRegex = /^[\w.%+-]+@[\w.-]+\.[a-zA-Z]{2,}$/;
        return email && emailRegex.test(email);
    }

    static validatePhone(phone) {
        const phoneRegex = /^\d{7,15}$/;
        return phone && phoneRegex.test(phone);
    }

    static validateAllFields(name, apartmentNumber, email, phone) {
        const errors = [];
        
        if (!this.validateName(name)) {
            errors.push("• Name can only contain letters and spaces.");
        }
        
        if (!this.validateApartmentNumber(apartmentNumber)) {
            errors.push("• Apartment number can only contain letters, numbers, and hyphens.");
        }
        
        if (!this.validateEmail(email)) {
            errors.push("• Email must have a valid format (example@domain.com).");
        }
        
        if (!this.validatePhone(phone)) {
            errors.push("• Phone can only contain numbers (7-15 digits).");
        }
        
        return errors.length > 0 ? errors.join("\n") : "";
    }
}