package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Thais Santórum
 */
public class Cage {
    private int id;
    private String description;
    private Location location;
    private int type; // 1 coop / 2 stable / 3 pens

    @Override
    public String toString() {
        return "Cage{" + "id=" + getId() +
                ", description=" + getDescription() +
                ", location=" + getLocation() +
                ", type=" + getType()
                + '}';
    }

    public Cage(int id, String description, int type, Location location) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.type = type;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    
    

 
    
    
}
