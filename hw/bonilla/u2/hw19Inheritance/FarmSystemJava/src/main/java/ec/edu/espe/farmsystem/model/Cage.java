package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class Cage {
    int id;
    String description;
    int type; //1:coop 2:stable 3:pens
    Location location;

    @Override
    public String toString() {
        return "{\n" +
            "    \"id\": " + id + ",\n" +
            "    \"description\": \"" + description + "\",\n" +
            "    \"type\": " + type + ",\n" +
            "    \"location\": " + location + "\n" +
            "}";
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
