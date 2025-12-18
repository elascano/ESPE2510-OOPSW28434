package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.User;

/**
 *
 * @author LABS-ESPE
 */
public  class UserController {
    
    
   public static boolean validateLogin(User user){
       //TODO  read informationm
    return(user.getUserName().equals("Josue")&& user.getPassword().equals("Rojas"));
    
    }
    
    
    
}
