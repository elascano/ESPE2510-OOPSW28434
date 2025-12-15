package ec.edu.espe.contactbook.controller;

import ec.edu.espe.contactbook.model.User;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class UserController {
    
    public static boolean validateLogin(User user){
        return user.getUserName().equals("Mathews") && user.getPassword().equals("Pastor");
    }
    
}
