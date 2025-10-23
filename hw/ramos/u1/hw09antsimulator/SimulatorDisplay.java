/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */

class SimulatorDisplay {
    Area area;

    public SimulatorDisplay(Area area) {
        this.area = area;
    }

    public void displayStatus() {
        System.out.println("------ Simulation Status ------");
        for (Colony colony : area.colonies)
            System.out.println("Colony: " + colony.ants.size() + " ants, food in nest: " + colony.nest.stockPile + "mg");
        System.out.println("Food remaining: " + area.isAnyFoodRemaining());
        System.out.println("--------------------------------");
    }
}

