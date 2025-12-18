package ec.edu.espe.navigation.controller;

import ec.edu.espe.navigation.model.User;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class UserController {

    public static boolean validateLogin(User user) {

        //TODO read information 
        return user.getUserName().equals("Mateo") && user.getPassword().equals("Aymacana");
    }
}
