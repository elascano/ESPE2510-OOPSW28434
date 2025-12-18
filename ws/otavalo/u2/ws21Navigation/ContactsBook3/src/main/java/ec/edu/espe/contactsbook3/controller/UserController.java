package ec.edu.espe.contactsbook3.controller;
import ec.edu.espe.contactsbook3.model.User;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming,@espe
 */
public class UserController {
    
    public static boolean validateLogin(User user){
        //TODO read information 
        return (user.getUserName().equals("Arelys")  && user.getPassword().equals("1234"));
    }
}
