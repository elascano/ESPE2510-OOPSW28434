package ec.edu.espe.q71_100.model;

/**
 *
 * @author Emily Calle, @ESPE
 */
public class Countries {
    private String name;
    private String capital;
    private String ubication;
    private int numberOfProvinces;

    public Countries(String name, String capital, String ubication, int numberOfProvinces) {
        this.name = name;
        this.capital = capital;
        this.ubication = ubication;
        this.numberOfProvinces = numberOfProvinces;
    }
    
    
    public Countries(){
        
    }
    @Override
    public String toString() {
        return "Countries{" + "name=" + name + ", capital=" + capital + ", ubication=" + ubication + ", numberOfProvinces=" + numberOfProvinces + '}';
    }
    
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * @return the ubication
     */
    public String getUbication() {
        return ubication;
    }

    /**
     * @param ubication the ubication to set
     */
    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    /**
     * @return the numberOfProvinces
     */
    public int getNumberOfProvinces() {
        return numberOfProvinces;
    }

    /**
     * @param numberOfProvinces the numberOfProvinces to set
     */
    public void setNumberOfProvinces(int numberOfProvinces) {
        this.numberOfProvinces = numberOfProvinces;
    }
    
    
}