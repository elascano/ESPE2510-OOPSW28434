package ec.edu.espe.contactsbook1.controller;

import ec.edu.espe.contactsbook1.model.User;

/**
 *
 * @author Joseph Medina, @ESPE
 */
public class UserController {
    
    public static boolean validateLogin(User user){
        
        return (user.getUserName().equals("Joseph") && user.getPassword().equals("Medina"));
        
        
        
    }
    
}
