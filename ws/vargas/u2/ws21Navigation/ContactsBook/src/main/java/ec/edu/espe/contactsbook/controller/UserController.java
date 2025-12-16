package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.User;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class UserController {
    
    public static boolean validateLogin(User user){
      
        
        //TODO read information fom the Database to confirm user and password
        return user.getUserName().equals("Cesar") && user.getPassword().equals("Vargas");
        
    }
    
}
