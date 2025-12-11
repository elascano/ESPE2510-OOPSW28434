package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.User;

/**
 *
 * @author Steven Loza
 */
public class UserController {
    public static boolean validateLogin(User user){
        
        
        return user.getUserName().equals("Edison") && user.getPassword().equals("Lascano");
    }
}
