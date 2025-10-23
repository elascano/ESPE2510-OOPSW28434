/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */

public class AntSimulation {
    public static void main(String[] args) {
        Area area = new Area(5, 5, 3, 100);
        area.setup();

        Nest nest = new Nest(area.getCell(2, 2));
        Colony colony = new Colony(nest);
        area.addColony(colony);

        AntEater antEater = new AntEater(area.getCell(4, 4));
        area.addAntEater(antEater);

        SimulatorDisplay display = new SimulatorDisplay(area);

        for (int tick = 0; tick < 10; tick++) {
            System.out.println("\n--- Tick " + tick + " ---");
            area.run();
            display.displayStatus();
        }
    }
}

