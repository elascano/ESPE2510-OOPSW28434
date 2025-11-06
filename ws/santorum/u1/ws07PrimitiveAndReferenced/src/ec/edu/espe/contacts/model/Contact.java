
package ec.edu.espe.contacts.model;

/**
 *
 * @author Thais Santórum Team 6 - Paradigm, @ESPE
 */
public class Contact {
    private int id;
    private String fullName;
    private String phoneNumber;
    // better solution using ENUMS // other CONSTANT 
    private int type; // 1 = family  2 = friend   3 = job mate   4 = class mate, 5 unknown
    private String email;
    private boolean favorite;
    private String instagramid;

    @Override
    public String toString() {
        
        String type="";
        String favorite;
        if(this.type == 1) type = "family";
        if(this.type == 1) type = "friend";
        if(this.type == 1) type = "jobmate";        
        if(this.type == 1) type = "classmate";
        
        
        if (this.favorite) {
            favorite = "Yes, you are";
        } else {
            favorite = "No, you are not";
        }
        
        
        return ". . . Contact Id = " + id +
                "\n     Full name = " + fullName +
                "\n     Phone number = " + phoneNumber +
                "\n     Type = " + type +
                "\n     Email=" + email +
                "\n     Favorite = " + favorite +
                "\n     Instagram Id = " + instagramid;
    }

    public Contact(int id, String fullName, String phoneNumber, int type, String email, boolean favorite, String instagramid) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.email = email;
        this.favorite = favorite;
        this.instagramid = instagramid;
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
     * @return the instagramid
     */
    public String getInstagramid() {
        return instagramid;
    }

    /**
     * @param instagramid the instagramid to set
     */
    public void setInstagramid(String instagramid) {
        this.instagramid = instagramid;
    }
    
    
}