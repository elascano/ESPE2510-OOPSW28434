package ec.edu.espe.contactbook.controller;

import ec.edu.espe.contactbook.model.User;

/**
 *
 * @author Mateo Cevallos Object Masters
 */
public class UserController {
    
    
    //TODO read information from Database to confirm user and password
    public static boolean validateLogin(User user){
        return user.getUserName().equals("Mateo") && user.getPassword().equals("Cevallos");   
    }
}
