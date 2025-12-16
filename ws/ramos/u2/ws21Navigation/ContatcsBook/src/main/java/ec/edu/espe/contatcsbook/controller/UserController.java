package ec.edu.espe.contatcsbook.controller;

import ec.edu.espe.contatcsbook.model.User;

/**
 *
 * @author LABS-ESPE
 */
public class UserController {

    public static boolean validateLogin(User user) {
        
        //TODO read information
        return (user.getUserName().equals("Paulo") && user.getPassword().equals("Ramos"));
    }
}
