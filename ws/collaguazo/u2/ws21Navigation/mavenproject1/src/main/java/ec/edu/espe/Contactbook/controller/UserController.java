/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.Contactbook.controller;

import ec.edu.espe.Contactbook.model.User;

/**
 *
 * @author LABS-ESPE
 */
public class UserController {
    public static boolean validateLogin(User user){
        return user.getUser().equals("Pablo")&& user.getPassword().equals("Jose");
    }
    
}
