package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.User;

/**
 *
 * @author Maryuri Quina, @ESPE
 */
public class UserController {
    
    public static boolean validateLogin(User user){
        
        //TODO read information to complete
        return user.getUserName().equals("Maryuri")&& user.getPassword().equals("Quina");
    }
}
