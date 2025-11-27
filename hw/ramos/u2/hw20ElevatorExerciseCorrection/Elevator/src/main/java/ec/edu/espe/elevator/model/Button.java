/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */
public abstract class Button {
    
    private String nameButton;

    public Button(String nameButton) {
        this.nameButton = nameButton;
    }

    
    /**
     * @return the nameButton
     */
    public String getNameButton() {
        return nameButton;
    }

    /**
     * @param nameButton the nameButton to set
     */
    public void setNameButton(String nameButton) {
        this.nameButton = nameButton;
    }
    
}

