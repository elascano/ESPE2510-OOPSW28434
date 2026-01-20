package ec.edu.espe.computer.controller;

import ec.edu.espe.computer.model.ILibrary;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Pablo Collaguazo
 */
public class ComputerController {

    private final ILibrary model;
    private final double fineRate = 0.03; 

    public ComputerController(ILibrary model) {
        this.model = model; 
    }

    public boolean saveRegistry(String title, double fee, int days) {
        try {
            double baseCost = fee * days;
            double fineAmount = Math.round((baseCost * fineRate) * 100.0) / 100.0;
            double totalPay = Math.round((baseCost + fineAmount) * 100.0) / 100.0;

            Document data = new Document("title", title)
                    .append("daily_fee", fee)
                    .append("delay_days", days)
                    .append("tax", fineAmount)
                    .append("total", totalPay);

            model.insert(data);
            return true;
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
            return false;
        }
    }

    public List<Document> listComputers() {
        return model.getAll();
    }

}
