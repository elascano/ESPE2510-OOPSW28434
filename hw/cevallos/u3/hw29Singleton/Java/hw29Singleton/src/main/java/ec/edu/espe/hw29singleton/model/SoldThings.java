/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.hw29singleton.model;

/**
 *
 * @author Mateo Cevallos
 */
public class SoldThings {

    public Sale sell(double price) {
        DiscountConfig config = DiscountConfig.getInstance();
        double discount = config.getDiscount();
        return new Sale(price, discount);
    }
}
