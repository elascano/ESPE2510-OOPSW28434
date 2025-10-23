/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



import model.*;
import view.SimulatorDisplay;

public class SimulationController {
    private Simulator simulator;
    private SimulatorDisplay display;
    private boolean running;

    public SimulationController(Simulator simulator, SimulatorDisplay display) {
        this.simulator = simulator;
        this.display = display;
    }

    public void start(long ticksToRun) {
        running = true;
        long target = simulator.getCurrentTick() + ticksToRun;
        while (running && simulator.getCurrentTick() < target) {
            simulator.tick();
            display.printSummary(simulator);
            // sleep tickDurationMs
            try {
                Thread.sleep(simulator.getTickDurationMs());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
    

