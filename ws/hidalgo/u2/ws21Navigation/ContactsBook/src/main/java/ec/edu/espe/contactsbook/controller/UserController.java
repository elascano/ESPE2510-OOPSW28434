package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.User;

/**
 *
 * @author Mikael Hidalgo, Object Masters, @ESPE
 */
public class UserController {
     public static  boolean validateLogin(User user) {
      // read information from the database with the user and pasword
       return user.getUserName().equals("Mikael")&& user.getPasword().equals("Hidalgo");
    }
    
}
