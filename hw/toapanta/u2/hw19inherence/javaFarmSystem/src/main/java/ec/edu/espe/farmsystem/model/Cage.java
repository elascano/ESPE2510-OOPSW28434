package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Cage {
    private int id;
    private String description;
    private int type; //estable, coop, pens
    Location location;

    @Override
    public String toString() {
        return "Cage\n" + 
           "id.: " + id + "\n" + 
           "description: " + description + "\n" + 
           "type: " + type; 
}

    public Cage(int id, String description, int type, Location location1) {
        this.id = id;
        this.description = description;
        this.type = type;
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
     
}
