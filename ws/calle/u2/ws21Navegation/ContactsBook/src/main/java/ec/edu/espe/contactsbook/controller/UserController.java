package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.User;
/**
 * 
 * @author Emily Calle, @ESPE
 */

public class UserController{
    public static boolean validateLogin(User user){
        return user.getUserName().equals("Emily")&& user.getPassword().equals("Calle");
        
    }
}