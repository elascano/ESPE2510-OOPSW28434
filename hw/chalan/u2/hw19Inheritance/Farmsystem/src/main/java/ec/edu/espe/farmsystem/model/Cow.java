
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */

    public class Cow extends FarmAnimal {
    
    private boolean isProducingMilk;
    private float litersPerDay; // Corregido de "littersADay" a "litersPerDay"

    public Cow(boolean isProducingMilk, float litersPerDay, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersPerDay = litersPerDay;
    }

    /**
     * Simula la extracción de leche
     * @return la cantidad de leche obtenida
     */
    public float milk() {
        // Aquí iría la lógica. Por ahora devolvemos la capacidad diaria.
        return this.litersPerDay;
    }

    @Override
    public String toString() {
        return "Cow{" + "isProducingMilk=" + isProducingMilk + ", litersPerDay=" + litersPerDay + ", " + super.toString() + '}';
    }

    // Getters y Setters
    public boolean isIsProducingMilk() {
        return isProducingMilk;
    }

    public void setIsProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    public float getLitersPerDay() {
        return litersPerDay;
    }

    public void setLitersPerDay(float litersPerDay) {
        this.litersPerDay = litersPerDay;
    }
}
    
    

