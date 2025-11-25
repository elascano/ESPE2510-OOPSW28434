package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Cage {

    private int id;
    private String descrption;
    private int type; //1 coop, 2 stables, 3 pens
    private Location location;

    @Override
    public String toString() {
        return "Cage{" + "id=" + id + ", descrption=" + descrption + ", type=" + type + ", location=" + location + '}';
    }

    public Cage(int id, String descrption, int type, Location location) {
        this.id = id;
        this.descrption = descrption;
        this.type = type;
        this.location = location;
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
     * @return the descrption
     */
    public String getDescrption() {
        return descrption;
    }

    /**
     * @param descrption the descrption to set
     */
    public void setDescrption(String descrption) {
        this.descrption = descrption;
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

}
