package ec.edu.espe.contactsbook.model;

/**
 *
 * @author LABS-ESPE
 */
public class User {
    private String userName;
    private String Password;
    private String Type;

    
    
    public User(String User, String Password, String Type) {
        this.userName = User;
        this.Password = Password;
        this.Type = Type;
    }

    
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param User the userName to set
     */
    public void setUser(String User) {
        this.userName = User;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }
    
    
    
}
