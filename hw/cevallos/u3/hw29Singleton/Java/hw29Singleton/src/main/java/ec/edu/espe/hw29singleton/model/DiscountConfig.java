/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.hw29singleton.model;

import util.JsonLoader;

/**
 *
 * @author Mateo Cevallos
 */
public class DiscountConfig {

    private static DiscountConfig instance;
    private double discount;

    private DiscountConfig() {
        discount = JsonLoader.loadDiscount("src/resources/discount.json");
    }

    public static DiscountConfig getInstance() {
        if (instance == null) {
            instance = new DiscountConfig();
        }
        return instance;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
