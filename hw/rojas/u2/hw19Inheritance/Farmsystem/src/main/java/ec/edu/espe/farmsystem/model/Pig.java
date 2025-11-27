/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class Pig extends FarmAnimal{

    public Pig(int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }

    @Override
    public String toString() {
        return "Pig{" + '}'+ super.toString();

    }

}
