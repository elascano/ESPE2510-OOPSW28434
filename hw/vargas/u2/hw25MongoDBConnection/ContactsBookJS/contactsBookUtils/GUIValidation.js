class JavaScriptValidator {
    static isNotEmpty(value) {
        if (value === null || value === undefined) {
            return false;
        }
        return value.trim() !== "";
    }

    static isValidName(name) {
        if (!name || !JavaScriptValidator.isNotEmpty(name)) {
            return false;
        }
        const regex = /^[ \p{L}.-]+$/u;
        return regex.test(name);
    }

    static isDateNotFuture(selectedDate) {
        if (!selectedDate || !JavaScriptValidator.isNotEmpty(selectedDate)) {
            return false;
        }

        const selectedTime = new Date(selectedDate).getTime();
        
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        return selectedTime <= today.getTime();
    }
}

module.exports = JavaScriptValidator;