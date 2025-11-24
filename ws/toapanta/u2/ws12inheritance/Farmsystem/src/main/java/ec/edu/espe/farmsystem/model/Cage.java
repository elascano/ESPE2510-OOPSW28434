
package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Adrian Toapanta, Student OOP, @ESPE
 */
public class Cage {
 int id;
 String description;
 int type;
 Location location;

    @Override
    public String toString() {
        return "Cage{" + "id=" + id + ", description=" + description + ", type=" + type + ", location=" + location + '}';
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

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}
