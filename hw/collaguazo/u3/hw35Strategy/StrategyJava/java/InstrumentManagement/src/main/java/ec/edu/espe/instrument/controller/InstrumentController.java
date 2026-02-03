package ec.edu.espe.instrument.controller;

import ec.edu.espe.instrument.model.Instrument;
import ec.edu.espe.instrument.utils.Persistence;
import java.util.List;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class InstrumentController {

    private Persistence strategy;
    private static final double IVA_RATE = 0.15;

    public InstrumentController(Persistence strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Persistence strategy) {
        this.strategy = strategy; //change the strategy in execution time if it is necesaru
    }

    public double calculateIva(double price) {
        double result = price * (1 + IVA_RATE);
        return Math.round(result * 100.0) / 100.0;
    }

    public boolean createSculpture(String id, String name, double price, List<String> materials) {
        double finalPrice = calculateIva(price);
        Instrument newTool = new Instrument(id, name, price, materials, finalPrice);
        return strategy.create(newTool);
    }

    public List<Instrument> getAllTools() {
        return strategy.read();
    }

    public Instrument findSculptureById(String id) {
        return strategy.find(id);
    }

    public boolean updateSculpture(String id, String name, double price, List<String> materials) {
        double newPriceWithIva = calculateIva(price);
        Instrument updatedTool = new Instrument(id, name, price, materials, newPriceWithIva);
        return strategy.update(id, updatedTool);
    }

    public boolean deleteSculpture(String id) {
        return strategy.delete(id);
    }
}