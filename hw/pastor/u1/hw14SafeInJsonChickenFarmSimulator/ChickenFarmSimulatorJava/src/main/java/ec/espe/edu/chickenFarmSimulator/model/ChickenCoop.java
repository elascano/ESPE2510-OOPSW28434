package ec.espe.edu.chickenFarmSimulator.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
/**
 *
 * @author Mathews Pastor
 */
public class ChickenCoop implements Serializable{
    private static final long serialVersionUID = 1L;
    private int chickenCoopNumber;
    private ArrayList<Chicken> chickens;
    private int chickenCapacity;
    private int totalEggs;
    
    public ChickenCoop(int chickenCoopNumber, int chickenCapacity){
        this.chickenCoopNumber = chickenCoopNumber;
        this.chickenCapacity = (chickenCapacity > 0) ? chickenCapacity : 100;
        this.chickens = new ArrayList<>();
        this.totalEggs = 0;
    }
    
    public boolean addChicken(Chicken chicken){
        if (chickens.size() < chickenCapacity){
            chickens.add(chicken);
            return true;
        }
        return false;
    }
    
    public boolean removeChicken(int chickenId){
        return chickens.removeIf(chicken -> chicken.getId() == chickenId);
    }
    
    public Optional<Chicken> findChicken(int chickenId){
        return chickens.stream().filter(chicken -> chicken.getId() == chickenId).findFirst();
    }
    
    public int simulateCoopDay(){
        int totalEggsLaid = 0;
        System.out.println("\n--- Simulate ChickenCoop Day - COOP ID " + getChickenCoopNumber() + "---");
        for (Chicken chicken : chickens){
            totalEggsLaid += chicken.doStuff();
        }
        this.totalEggs += totalEggsLaid;
        System.out.println("\n Today: A total of " + totalEggsLaid + " eggs were laid in the Chicken Coop " + getChickenCoopNumber() + ".");
        return totalEggsLaid;
    }
    
    @Override
    public String toString() {
        return "Chicken coop ID: " + chickenCoopNumber +
               ", Chickens: " + chickens.size() + "/" + chickenCapacity +
               ", Total Eggs: " + totalEggs;
    }

    public int getChickenCoopNumber() {
        return chickenCoopNumber;
    }

    public void setChickenCoopNumber(int chickenCoopNumber) {
        this.chickenCoopNumber = chickenCoopNumber;
    }

    public ArrayList<Chicken> getChickens() {
        return chickens;
    }

    public void setChickens(ArrayList<Chicken> chickens) {
        this.chickens = chickens;
    }

    public int getCapacity() {
        return chickenCapacity;
    }

    public void setChickenCapacity(int chickenCapacity) {
        this.chickenCapacity = chickenCapacity;
    }

    public int getTotalEggs() {
        return totalEggs;
    }

    public void setTotalEggs(int totalEggs) {
        this.totalEggs = totalEggs;
    }

    void setCapacity(int newCapacity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
