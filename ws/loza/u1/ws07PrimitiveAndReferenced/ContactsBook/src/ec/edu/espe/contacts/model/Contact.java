package ec.edu.espe.contacts.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class Contact {

    private int id;
    private String fullName;
    private String phoneNumber;
    //a better solution should be using ENUMS, other solution CONTANT
    private int type; //1 = family, 2= Friend, 3= job mate, 4= class mate, 5= unknown
    private String email;
    private boolean favorite;
    private String instagramId;

    @Override
    public String toString() {
        String type = "";
        String favorite;
        if (this.type == 1) type ="family";
        if (this.type == 2) type ="friend";
        if (this.type == 3) type ="job mate";
        if (this.type == 4) type ="class mate";
        
        if(this.favorite == true) 
            favorite ="YES you are";
        else
            favorite = "NO, sorry";
        
        return "Contact-->"  + id + ", " + fullName + ", " + phoneNumber + ", " + type + ", " + email + ", " + favorite + ", " + instagramId + '}';
    }

    public Contact(int id, String fullName, String phoneNumber, int Type, String email, boolean favorite, String instagramId) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.type = Type;
        this.email = email;
        this.favorite = favorite;
        this.instagramId = instagramId;
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
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the Type
     */
    public int getType() {
        return type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(int Type) {
        this.type = Type;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the favorite
     */
    public boolean isFavorite() {
        return favorite;
    }

    /**
     * @param favorite the favorite to set
     */
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * @return the instagramId
     */
    public String getInstagramId() {
        return instagramId;
    }

    /**
     * @param instagramId the instagramId to set
     */
    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

}
