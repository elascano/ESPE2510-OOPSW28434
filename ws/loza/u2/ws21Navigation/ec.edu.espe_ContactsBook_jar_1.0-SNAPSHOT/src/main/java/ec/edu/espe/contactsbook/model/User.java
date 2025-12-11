package ec.edu.espe.contactsbook.model;

/**
 *
 * @author Steven Loza, @ESPE
 */
public class User {
    private String user;
    private String password;
    private String type;

    public User(String user, String password, String type) {
        this.user = user;
        this.password = password;
        this.type = type;
    }

    /**
     * @return the user
     */
    public String getUserName() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUserName(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}