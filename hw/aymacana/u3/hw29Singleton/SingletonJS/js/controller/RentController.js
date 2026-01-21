// js/controller/RentController.js
class RentController {
    getTotalToPay(id, name, months) {
        if (months < 0 || months > 12) {
            throw new Error("Los meses deben estar entre 0 y 12");
        }
        
        const resident = new Resident(id, name, months);
        return resident.calculateTotalRent();
    }
    
    processPayment(id, name, months) {
        try {
            const total = this.getTotalToPay(id, name, months);
            
            return {
                success: true,
                message: "Pago procesado exitosamente",
                data: {
                    residentId: id,
                    residentName: name,
                    months: months,
                    total: total,
                    timestamp: new Date().toISOString()
                }
            };
        } catch (error) {
            return {
                success: false,
                message: error.message,
                data: null
            };
        }
    }
}

// Exportar la clase
window.RentController = RentController;