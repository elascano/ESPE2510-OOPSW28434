package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class Cage {
    private int id;
    private String description;
    private int type; //1 coop, 2 stable, 3 pens
    Location location;

    @Override
    public String toString() {
        return "[" + id + "] " + description + " (" + location + ")";
    }

    public Cage(int id, String description, int type, Location location) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    

    
    
}
