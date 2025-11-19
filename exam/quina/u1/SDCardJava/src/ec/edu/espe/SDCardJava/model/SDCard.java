package ec.edu.espe.SDCardJava.model;

/**
 *
 * @author Maryuri Quina, @ESPE
 */
public class SDCard {
    private int id;
    private int capacityGB;
    private String type;
    private String writeSpeedMBs;

    public SDCard(int id, int capacityGB, String type, String writeSpeedMBs) {
        this.id = id;
        this.capacityGB = capacityGB;
        this.type = type;
        this.writeSpeedMBs = writeSpeedMBs;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(int capacityGB) {
        this.capacityGB = capacityGB;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWriteSpeedMBs() {
        return writeSpeedMBs;
    }

    public void setWriteSpeedMBs(String writeSpeedMBs) {
        this.writeSpeedMBs = writeSpeedMBs;
    }
    
    
}
