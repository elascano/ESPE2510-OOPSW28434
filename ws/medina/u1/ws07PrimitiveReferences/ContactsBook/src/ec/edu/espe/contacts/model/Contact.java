package ec.edu.espe.contacts.model;

/**
 *
 * @author Joseph Medina
 */
public class Contact {
    
    private int id;
    private String fullNmae;
    private String phoneNumber;
    // a better solution should be using ENUMS, other solution CONSTANT
    private int type; //1 = family, 2=friend, 3 = job mate, 4 = class mate, 5 unknown
    private String email;
    private boolean favorite;
    private String instagramId;

    @Override
    public String toString() {
        String type = "";
        String favorite;
        
        if (this.type == 1 ) type = "family";
        if (this.type == 1 ) type = "friend";
        if (this.type == 1 ) type = "jobmate";
        if (this.type == 1 ) type = "classmate";
        
        if( this.favorite == true)
            favorite = "Yes, you are";
        else
            favorite = "No, Sorry";
        
        
        return ( id + "  " + fullNmae + "  " + phoneNumber + "  " + type + "   " + email + "  " + favorite + "  " + instagramId );
    }

    public Contact(int id, String fullNmae, String phoneNumber, int type, String email, boolean favorite, String instagramId) {
        this.id = id;
        this.fullNmae = fullNmae;
        this.phoneNumber = phoneNumber;
        this.type = type;
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
     * @return the fullNmae
     */
    public String getFullNmae() {
        return fullNmae;
    }

    /**
     * @param fullNmae the fullNmae to set
     */
    public void setFullNmae(String fullNmae) {
        this.fullNmae = fullNmae;
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
