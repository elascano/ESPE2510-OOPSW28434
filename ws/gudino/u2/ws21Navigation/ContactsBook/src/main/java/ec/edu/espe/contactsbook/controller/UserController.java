package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.User;

/**
 *
 * @author Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE
 */
public class UserController {
     public static  boolean validateLogin(User user) {
      // read information from the database with the user and pasword
       return user.getUserName().equals("kevin")&& user.getPasword().equals("chalan");
    }
    
}
