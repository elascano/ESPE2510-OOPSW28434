package ec.edu.espe.contactsbook.controller;
import ec.edu.espe.contactsbook.model.User;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class UserController {
    public static boolean validateLogin(User user){
        //T0D0 read information from User
        return user.getUserName().equals("Arelis") && user.getPassword().equals("Bonilla");
    }
}
