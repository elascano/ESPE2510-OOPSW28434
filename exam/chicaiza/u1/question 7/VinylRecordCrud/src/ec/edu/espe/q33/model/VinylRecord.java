package ec.edu.espe.q33.model;

public class VinylRecord {
 
    private int id; 
    private String nameAlbum;
    private String genere;
    private double size;

    @Override
    public String toString() {
        return "VinylRecord{" + "id=" + id + ", nameAlbum=" + nameAlbum + ", genere=" + genere + ", size=" + size + '}';
    }

    public VinylRecord(int id, String nameAlbum, String genere, double size) {
        this.id = id;
        this.nameAlbum = nameAlbum;
        this.genere = genere;
        this.size = size;
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
     * @return the nameAlbum
     */
    public String getNameAlbum() {
        return nameAlbum;
    }

    /**
     * @param nameAlbum the nameAlbum to set
     */
    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    /**
     * @return the genere
     */
    public String getGenere() {
        return genere;
    }

    /**
     * @param genere the genere to set
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * @return the size
     */
    public double getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(double size) {
        this.size = size;
    }

    
    
    
    
}