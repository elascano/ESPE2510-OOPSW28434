/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chickenfarmsimulator.model;

/**
 *
 * @author JOSUE
 */

public class Poop {
    private int chickenId;
    private int weightGrams;

    public Poop(int chickenId, int weightGrams) {
        this.chickenId = chickenId;
        this.weightGrams = weightGrams;
    }

    @Override
    public String toString() {
        return "Poop(from=" + chickenId + ", weight=" + weightGrams + "g)";
    }
}
