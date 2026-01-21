/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.ws29singleton.model;

/**
 *
 * @author Mateo Cevallos
 */
public class USTax {
    private float taxPercentage;
    
    public static void getInstance(){
        USTax ustax = new USTax(0);   
    }
    
    public static float salesTotal(float taxPercentage){
        float salesTotal = 10;
        salesTotal = salesTotal*taxPercentage;
        return salesTotal;
    }
    
    public USTax(float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
    
    /**
     * @return the taxPercentage
     */
    public float getTaxPercentage() {
        return taxPercentage;
    }

    /**
     * @param taxPercentage the taxPercentage to set
     */
    public void setTaxPercentage(float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
    
    
    
}
