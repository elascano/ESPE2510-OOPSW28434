export class GUIValidation {

    static validateOnlyNumbers(text, fieldName) {
        if (!text || text.trim() === '') {
            console.log(`ERROR: El campo '${fieldName}' es obligatorio.`);
            return false;
        }
        if (!/^\d+$/.test(text)) {
            console.log(`ERROR: El campo '${fieldName}' debe contener solo números.`);
            return false;
        }
        return true;
    }

    static validateDateRange(dateJs) {
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        const selected = new Date(dateJs);
        selected.setHours(0, 0, 0, 0);

        if (selected < today) {
            console.log("ERROR: La fecha no puede ser anterior a hoy.");
            return false;
        }

        const nextYear = new Date();
        nextYear.setFullYear(today.getFullYear() + 1);
        
        if (selected > nextYear) {
             console.log("ERROR: La fecha no puede exceder el próximo año.");
             return false;
        }
        return true;
    }
}