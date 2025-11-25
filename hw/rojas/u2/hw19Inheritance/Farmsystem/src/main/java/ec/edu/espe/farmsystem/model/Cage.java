package ec.edu.espe.farmsystem.model;
/**
 *
 * @author Josue Rojas
 */

public class Cage {
    private int id;
    private String description;
    private int type; //1 coop, 2 stable, 3 pens
    Location location;

    @Override
    public String toString() {
        return "Cage" + "\nid.: " + id + "\ndescription: " + description + "\ntype: " + type ;
    }

    public Cage(int id, String description, int type) {
        this.id = id;
        this.description = description;
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
