package ec.espe.edu.chickenFarmSimulator.model;
import java.util.ArrayList;
import java.util.Optional;
/**
 *
 * @author Mathews Pastor
 */
public class ChickenFarmer{
    private int id;
    private String name;
    private ArrayList<ChickenCoop> coops;
    private int nextCoopId;
    
    public ChickenFarmer(int id, String name){
        this.id = id;
        this.name = name;
        this.coops = new ArrayList<>();
        this.nextCoopId = 1;
    }
    
    public ChickenCoop addCoop(int chickenCapacity){
        ChickenCoop newCoop = new ChickenCoop(nextCoopId++, chickenCapacity);
        coops.add(newCoop);
        return newCoop;
    }
    
    public boolean removeCoop(int coopId){
        return coops.removeIf(coop -> coop.getChickenCoopNumber() == coopId);
    }
    
    public Optional<ChickenCoop> findCoop(int coopId){
        return coops.stream()
                .filter(coop -> coop.getChickenCoopNumber() == coopId)
                .findFirst();
    }
    
    public boolean updateCoop(int coopId, int newCapacity){
        Optional<ChickenCoop> coopOpt = findCoop(coopId);
        if (coopOpt.isPresent()){
            coopOpt.get().setCapacity(newCapacity);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Farmer: " + name + " (ID: " + id + "), Chicken coops: " + coops.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChickenCoop> getCoops() {
        return coops;
    }

    public void setCoops(ArrayList<ChickenCoop> coops) {
        this.coops = coops;
    }

    public int getNextCoopId() {
        return nextCoopId;
    }

    public void setNextCoopId(int nextCoopId) {
        this.nextCoopId = nextCoopId;
    }
    
    
    
    
}
