package ec.edu.espe.tools.controller;

import ec.edu.espe.tools.model.Tool;
import ec.edu.espe.tools.utils.Persistence;
import java.util.List;

/**
 *
 * @author Mikael Hidalgo, Paradigm, @ESPE
 */
public class ToolController {

    private Persistence strategy;
    private static final double IVA_RATE = 0.15;

    public ToolController(Persistence strategy) {
        this.strategy = strategy;
    }

    // Permite cambiar la estrategia en tiempo de ejecución si fuera necesario
    public void setStrategy(Persistence strategy) {
        this.strategy = strategy;
    }

    public double calculateIva(double price) {
        double result = price * (1 + IVA_RATE);
        return Math.round(result * 100.0) / 100.0;
    }

    public boolean createSculpture(String id, String name, double price, List<String> materials) {
        double finalPrice = calculateIva(price);
        Tool newTool = new Tool(id, name, price, materials, finalPrice);
        return strategy.create(newTool);
    }

    public List<Tool> getAllTools() {
        return strategy.read();
    }

    public Tool findSculptureById(String id) {
        return strategy.find(id);
    }

    public boolean updateSculpture(String id, String name, double price, List<String> materials) {
        double newPriceWithIva = calculateIva(price);
        Tool updatedTool = new Tool(id, name, price, materials, newPriceWithIva);
        return strategy.update(id, updatedTool);
    }

    public boolean deleteSculpture(String id) {
        return strategy.delete(id);
    }
}