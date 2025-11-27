/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

import java.util.List;

/**
 *
 * @author Mateo Cevallos
 */
public class StatisticsCollector {
    private List<ElevatorUsage> usageData;

    public void recordUsage(ElevatorUsage usage) {
        System.out.println("Usage recorded...");
    }

    public String generateReport() {
        return "Statistics report generated...";
    }

}
