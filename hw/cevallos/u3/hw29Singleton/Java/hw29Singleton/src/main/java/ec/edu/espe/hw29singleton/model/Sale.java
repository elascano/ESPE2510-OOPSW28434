/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.hw29singleton.model;

/**
 *
 * @author Mateo Cevallos
 */
public class Sale {

    private final double originalPrice;
    private final double discountApplied;

    public Sale(double originalPrice, double discountApplied) {
        this.originalPrice = originalPrice;
        this.discountApplied = discountApplied;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }
}
