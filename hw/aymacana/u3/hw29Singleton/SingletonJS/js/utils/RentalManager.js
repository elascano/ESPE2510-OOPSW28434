// js/utils/RentalManager.js - VERSIÓN CORREGIDA
class RentalManager {
    static #instance = null;
    static FILE_KEY = 'monthly_rent_value';
    #monthlyRent = 0;
    
    constructor() {
        this.#loadRentFromStorage();
    }
    
    static getInstance() {
        if (!RentalManager.#instance) {
            RentalManager.#instance = new RentalManager();
        }
        return RentalManager.#instance;
    }
    
    #loadRentFromStorage() {
        try {
            const savedValue = localStorage.getItem(RentalManager.FILE_KEY);
            if (savedValue) {
                this.#monthlyRent = parseFloat(savedValue);
                console.log(`Valor de renta cargado: $${this.#monthlyRent.toFixed(2)}`);
            } else {
                this.#monthlyRent = 20.0;
                this.#saveRentToStorage();
                console.log(`Valor por defecto establecido: $${this.#monthlyRent.toFixed(2)}`);
            }
        } catch (error) {
            console.error("Error cargando renta:", error);
            this.#monthlyRent = 20.0;
            this.#saveRentToStorage();
        }
    }
    
    #saveRentToStorage() {
        try {
            localStorage.setItem(RentalManager.FILE_KEY, this.#monthlyRent.toString());
            console.log(`💾 Valor de renta guardado: $${this.#monthlyRent.toFixed(2)}`);
        } catch (error) {
            console.error("❌ Error guardando renta:", error);
            throw error;
        }
    }
    
    getMonthlyRent() {
        return this.#monthlyRent;
    }
    
    updateMonthlyRent(newRent) {
        if (newRent <= 0) {
            throw new Error("El valor de la renta debe ser mayor a 0");
        }
        
        this.#monthlyRent = newRent;
        this.#saveRentToStorage();
        console.log(`Valor de renta actualizado: $${newRent.toFixed(2)}`);
        
        window.dispatchEvent(new CustomEvent('rentUpdated', { 
            detail: { newRent } 
        }));
        
        return true;
    }
    
    toString() {
        return `RentalManager(monthlyRent=$${this.#monthlyRent.toFixed(2)})`;
    }
    
    static resetInstance() {
        RentalManager.#instance = null;
        localStorage.removeItem(RentalManager.FILE_KEY);
        console.log("🔄 Singleton reseteado");
    }
}


window.RentalManager = RentalManager;  