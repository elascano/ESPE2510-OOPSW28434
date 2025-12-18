package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.User;

/**
 *
 * @author Thais Santórum
 */
public class UserController {

    public static boolean validateLogin(User user){
        //TODO read info from the DB to compare with user and password
        return user.getUserName().equals("Thais") && user.getPassword().equals("thais");
    }
    
    
    
}
