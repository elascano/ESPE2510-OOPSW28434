package ec.edu.espe.contactbook.model;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class User {
    private String userName;
    private String password;
    private String type;

    public User(String user, String password, String type) {
        this.userName = user;
        this.password = password;
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "user=" + userName + ", password=" + password + ", type=" + type + '}';
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
